package vn.bacon.parking.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.bacon.parking.domain.MonthlyRegistrationRequest;
import vn.bacon.parking.domain.ParkingMode;
import vn.bacon.parking.domain.Price;
import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.repository.MonthlyRegistrationRequestRepository;
import vn.bacon.parking.repository.ParkingModeRepository;
import vn.bacon.parking.repository.PriceRepository;
import vn.bacon.parking.repository.StudentRepository;
import vn.bacon.parking.repository.VehicleRepository;

@Service
public class MonthlyRegistrationRequestService {

    @Autowired
    private MonthlyRegistrationRequestRepository requestRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ParkingModeRepository parkingModeRepository;

    public MonthlyRegistrationRequest createRequest(MonthlyRegistrationRequest request) {
        if (request.getStudent() == null || request.getVehicle() == null) {
            throw new IllegalArgumentException("Sinh viên và xe là bắt buộc.");
        }

        Vehicle vehicle = request.getVehicle();
        ParkingMode hinhThuc = parkingModeRepository.findById("HT002")
                .orElseThrow(() -> new IllegalArgumentException("Hình thức gửi tháng (HT002) không tồn tại."));
        Price price = priceRepository.findByMaHinhThucAndMaLoaiXe(hinhThuc, vehicle.getMaLoaiXe());
        if (price == null) {
            throw new IllegalArgumentException(
                    "Không tìm thấy giá cho xe " + vehicle.getBienSoXe() + " với hình thức gửi tháng.");
        }
        request.setBangGia(price);

        return requestRepository.save(request);
    }

    public boolean hasActiveRequestForVehicle(String bienSoXe) {
        return requestRepository.existsByVehicleBienSoXeAndTrangThaiInAndNgayHetHanGreaterThanEqual(
                bienSoXe, Arrays.asList("Chờ duyệt", "Đã duyệt"), LocalDate.now());
    }

    public boolean hasActiveRequestForVehicleExcludingRequestId(String bienSoXe, String maYeuCau) {
        return requestRepository.existsByVehicleBienSoXeAndMaYeuCauNotAndTrangThaiIn(
                bienSoXe, maYeuCau, Arrays.asList("Chờ duyệt", "Đã duyệt"));
    }

    public List<MonthlyRegistrationRequest> getRequestsByStudentId(String maSV) {
        return requestRepository.findByStudentMaSV(maSV);
    }

    public Optional<MonthlyRegistrationRequest> getRequestById(String maYeuCau) {
        return requestRepository.findById(maYeuCau);
    }

    public Long getMaxMaYeuCau() {
        return requestRepository.findMaxMaYeuCau();
    }

    @Transactional
    public MonthlyRegistrationRequest updateRequest(MonthlyRegistrationRequest request) {
        if (request.getMaYeuCau() == null) {
            throw new IllegalArgumentException("Mã yêu cầu không được để trống.");
        }

        Vehicle vehicle = request.getVehicle();
        ParkingMode hinhThuc = parkingModeRepository.findById("HT002")
                .orElseThrow(() -> new IllegalArgumentException("Hình thức gửi tháng (HT002) không tồn tại."));
        Price price = priceRepository.findByMaHinhThucAndMaLoaiXe(hinhThuc, vehicle.getMaLoaiXe());
        if (price == null) {
            throw new IllegalArgumentException(
                    "Không tìm thấy giá cho xe " + vehicle.getBienSoXe() + " với hình thức gửi tháng.");
        }
        request.setBangGia(price);

        return requestRepository.save(request);
    }

    public Page<MonthlyRegistrationRequest> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }
}