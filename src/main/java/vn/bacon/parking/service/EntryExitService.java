package vn.bacon.parking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.bacon.parking.domain.EntryExitDetail;
import vn.bacon.parking.domain.ParkingMode;
import vn.bacon.parking.domain.Price;
import vn.bacon.parking.domain.RegisterMonth;
import vn.bacon.parking.domain.Staff;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.repository.EntryExitDetailRepository;
import vn.bacon.parking.repository.ParkingModeRepository;
import vn.bacon.parking.repository.PriceRepository;
import vn.bacon.parking.repository.RegisterMonthRepository;
import vn.bacon.parking.repository.StaffRepository;
import vn.bacon.parking.repository.VehicleRepository;

@Service
public class EntryExitService {

    private static final Logger logger = LoggerFactory.getLogger(EntryExitService.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RegisterMonthRepository registerMonthRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ParkingModeRepository parkingModeRepository;

    @Autowired
    private EntryExitDetailRepository entryExitDetailRepository;

    public EntryExitDetail processVehicleEntry(String bienSoXe, String maNVVao) throws Exception {
        logger.debug("Processing vehicle entry: bienSoXe={}, maNVVao={}", bienSoXe, maNVVao);

        Vehicle vehicle = vehicleRepository.findById(bienSoXe)
                .orElseThrow(
                        () -> new Exception("Xe với biển số " + bienSoXe + " chưa được đăng ký. Vui lòng tạo mới."));

        Staff nvVao = staffRepository.findById(maNVVao)
                .orElseThrow(() -> new Exception("Nhân viên " + maNVVao + " không tồn tại."));

        boolean isLecturer = false;
        if (vehicle.getMaNV() != null) {
            Staff owner = staffRepository.findById(vehicle.getMaNV().getMaNV()).orElse(null);
            if (owner != null && "Giảng viên".equals(owner.getChucVu())) {
                isLecturer = true;
            }
        }

        EntryExitDetail entry = new EntryExitDetail();
        entry.setBienSoXe(vehicle);
        LocalDateTime tgVao = LocalDateTime.now();
        entry.setTgVao(tgVao);
        entry.setNvVao(nvVao);
        logger.debug("Set tgVao to: {}", tgVao);

        ParkingMode parkingMode;
        Integer gia = null;

        if (isLecturer) {
            parkingMode = parkingModeRepository.findById("HT001")
                    .orElseThrow(() -> new Exception("Hình thức gửi HT001 không tồn tại."));
            gia = 0;
        } else {
            RegisterMonth registration = registerMonthRepository.findByBienSoXeAndTGianHetHanAfter(
                    vehicle, LocalDate.now());
            if (registration != null) {
                parkingMode = parkingModeRepository.findById("HT002")
                        .orElseThrow(() -> new Exception("Hình thức gửi HT002 không tồn tại."));
                gia = 0;
            } else {
                parkingMode = parkingModeRepository.findById("HT001")
                        .orElseThrow(() -> new Exception("Hình thức gửi HT001 không tồn tại."));
                Price price = priceRepository.findByMaHinhThucAndMaLoaiXe(parkingMode, vehicle.getMaLoaiXe());
                if (price == null) {
                    throw new Exception("Không tìm thấy giá cho hình thức " + parkingMode.getTenHinhThuc()
                            + " và loại xe " + vehicle.getMaLoaiXe().getTenLoaiXe());
                }
                gia = price.getGia();
            }
        }

        entry.setHinhThuc(parkingMode);
        entry.setGia(gia);

        if (entry.getTgVao() == null) {
            logger.error("tgVao is null before saving entry: {}", entry);
            throw new IllegalStateException("tgVao cannot be null");
        }
        EntryExitDetail savedEntry = entryExitDetailRepository.save(entry);
        logger.debug("Saved entry: {}", savedEntry);
        return savedEntry;
    }

    public EntryExitDetail processVehicleExit(Integer maCTVaoRa, String maNVRa) throws Exception {
        logger.debug("Processing vehicle exit: maCTVaoRa={}, maNVRa={}", maCTVaoRa, maNVRa);

        EntryExitDetail entry = entryExitDetailRepository.findById(maCTVaoRa)
                .orElseThrow(() -> new Exception("Không tìm thấy bản ghi với mã " + maCTVaoRa));

        if (entry.getTgRa() != null) {
            throw new Exception("Xe đã được ghi nhận ra vào " + entry.getTgRaFormatted());
        }

        Staff nvRa = staffRepository.findById(maNVRa)
                .orElseThrow(() -> new Exception("Nhân viên " + maNVRa + " không tồn tại."));

        entry.setTgRa(LocalDateTime.now());
        entry.setNvRa(nvRa);
        logger.debug("Set tgRa to: {}", entry.getTgRa());

        Vehicle vehicle = entry.getBienSoXe();
        if (vehicle == null) {
            throw new Exception("Biển số xe không hợp lệ cho bản ghi " + maCTVaoRa);
        }

        boolean isLecturer = false;
        if (vehicle.getMaNV() != null) {
            Staff owner = staffRepository.findById(vehicle.getMaNV().getMaNV()).orElse(null);
            if (owner != null && "Giảng viên".equals(owner.getChucVu())) {
                isLecturer = true;
            }
        }

        Integer gia = null;
        if (isLecturer) {
            gia = 0;
        } else {
            RegisterMonth registration = registerMonthRepository.findByBienSoXeAndTGianHetHanAfter(
                    vehicle, LocalDate.now());
            if (registration != null) {
                gia = 0;
            } else {
                ParkingMode parkingMode = entry.getHinhThuc();
                if (!"HT001".equals(parkingMode.getMaHinhThuc())) {
                    parkingMode = parkingModeRepository.findById("HT001")
                            .orElseThrow(() -> new Exception("Hình thức gửi HT001 không tồn tại."));
                    entry.setHinhThuc(parkingMode);
                }
                Price price = priceRepository.findByMaHinhThucAndMaLoaiXe(parkingMode, vehicle.getMaLoaiXe());
                if (price == null) {
                    throw new Exception("Không tìm thấy giá cho hình thức " + parkingMode.getTenHinhThuc()
                            + " và loại xe " + vehicle.getMaLoaiXe().getTenLoaiXe());
                }
                gia = price.getGia();
            }
        }

        entry.setGia(gia);

        EntryExitDetail savedEntry = entryExitDetailRepository.save(entry);
        logger.debug("Saved exit entry: {}", savedEntry);
        return savedEntry;
    }

    public Page<EntryExitDetail> getAllEntries(Pageable pageable) {
        Page<EntryExitDetail> page = entryExitDetailRepository.findAll(pageable);
        logger.debug("Fetched {} entries for page: {}", page.getContent().size(), pageable);
        return page;
    }
}