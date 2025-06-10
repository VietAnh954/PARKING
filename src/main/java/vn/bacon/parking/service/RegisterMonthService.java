package vn.bacon.parking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.bacon.parking.domain.RegisterMonth;
import vn.bacon.parking.repository.RegisterMonthRepository;

@Service
public class RegisterMonthService {
    private final RegisterMonthRepository registerMonthRepository;
    private final JavaMailSender mailSender;

    public RegisterMonthService(RegisterMonthRepository registerMonthRepository, JavaMailSender mailSender) {
        this.registerMonthRepository = registerMonthRepository;
        this.mailSender = mailSender;
    }

    // public RegisterMonth saveRegisterMonth(RegisterMonth registerMonth) {
    // return this.registerMonthRepository.save(registerMonth);
    // }

    public List<RegisterMonth> getAllRegisterMonths() {
        return this.registerMonthRepository.findAll();
    }

    public Optional<RegisterMonth> getRegisterMonthById(String maDangKy) {
        return this.registerMonthRepository.findById(maDangKy);
    }

    public void deleteRegisterMonthById(String maDangKy) {
        try {
            registerMonthRepository.deleteById(maDangKy);
        } catch (Exception e) {
            throw new RuntimeException("Không thể xóa đăng ký tháng. Có thể do ràng buộc dữ liệu.", e);
        }
    }

    // Tìm kiếm đăng ký theo biển số xe
    public List<RegisterMonth> timKiemTheoBienSoXe(String tuKhoa) {
        return registerMonthRepository.timKiemTheoBienSoXe(tuKhoa);
    }

    // Lọc đăng ký còn hiệu lực
    public List<RegisterMonth> timDangKyConHieuLuc() {
        return registerMonthRepository.timDangKyConHieuLuc(LocalDate.now());
    }

    // Lọc đăng ký đã hết hạn
    public List<RegisterMonth> timDangKyDaHetHan() {
        return registerMonthRepository.timDangKyDaHetHan(LocalDate.now());
    }

    // Lấy danh sách đăng ký sắp hết hạn (trước 5 ngày ngày hết hạn)
    public List<RegisterMonth> getRegisterMonthsExpiringSoon() {
        LocalDate now = LocalDate.now();
        LocalDate soon = now.plusDays(5);
        return registerMonthRepository.findAll().stream()
                .filter(dk -> !dk.gettGianHetHan().isBefore(now) && !dk.gettGianHetHan().isAfter(soon))
                .toList();
    }

    // Lưu đăng ký với xác thực
    public RegisterMonth saveRegisterMonth(RegisterMonth registerMonth) {
        // RegisterMonth registerMonth2 =
        // this.registerMonthRepository.save(registerMonth)
        // Xác thực: Ngày đăng ký phải trước ngày hết hạn
        if (registerMonth.gettGianDangKy().isAfter(registerMonth.gettGianHetHan())) {
            throw new IllegalArgumentException("Ngày đăng ký phải trước ngày hết hạn.");
        }
        // Xác thực: Biển số xe và mã nhân viên không được để trống
        if (registerMonth.getBienSoXe() == null
                || registerMonth.getBienSoXe().getBienSoXe() == null
                || registerMonth.getBienSoXe().getBienSoXe().trim().isEmpty()) {
            throw new IllegalArgumentException("Biển số xe không được để trống.");
        }
        if (registerMonth.getNvGhiNhan() == null
                || registerMonth.getNvGhiNhan().getMaNV() == null
                || registerMonth.getNvGhiNhan().getMaNV().trim().isEmpty()) {

            throw new IllegalArgumentException("Mã nhân viên không được để trống.");
        }

        return registerMonthRepository.save(registerMonth);
    }

    // Gia hạn đăng ký
    public RegisterMonth giaHanDangKy(String maDangKy, int soThang) {
        if (soThang != 1 && soThang != 6 && soThang != 12) {
            throw new IllegalArgumentException("Thời gian gia hạn phải là 1, 6 hoặc 12 tháng");
        }

        RegisterMonth registerMonth = registerMonthRepository.findById(maDangKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký tháng"));

        LocalDate ngayHetHanMoi = registerMonth.gettGianHetHan().plusMonths(soThang);
        registerMonth.settGianHetHan(ngayHetHanMoi);

        return registerMonthRepository.save(registerMonth);
    }

    // Test gửi email cho một đăng ký cụ thể
    public void testGuiEmailThongBao(String maDangKy) {
        RegisterMonth registerMonth = registerMonthRepository.findById(maDangKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký tháng"));
        guiEmailThongBao(registerMonth);
    }

    // Gửi email thông báo
    private void guiEmailThongBao(RegisterMonth registerMonth) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = "";
        String ten = "";
        if (registerMonth.getBienSoXe().getMaNV() != null) {
            email = registerMonth.getBienSoXe().getMaNV().getEmail();
            ten = registerMonth.getBienSoXe().getMaNV().getHoTen();
        } else if (registerMonth.getBienSoXe().getMaSV() != null) {
            email = registerMonth.getBienSoXe().getMaSV().getEmail();
            ten = registerMonth.getBienSoXe().getMaSV().getHoTen();
        }
        message.setTo(email);
        message.setSubject("Thông báo sắp hết hạn đăng ký tháng");
        message.setText(String.format(
                "Kính gửi %s,\n\n" +
                        "Đăng ký tháng của xe %s sẽ hết hạn vào ngày %s.\n" +
                        "Vui lòng gia hạn trước ngày hết hạn để tránh gián đoạn dịch vụ.\n\n" +
                        "Trân trọng,\n" +
                        "Hệ thống quản lý bãi đỗ xe",
                ten,
                registerMonth.getBienSoXe().getBienSoXe(),
                registerMonth.gettGianHetHan().toString()));

        mailSender.send(message);
    }

    // Kiểm tra và gửi thông báo hàng ngày
    @Scheduled(cron = "0 0 9 * * ?") // Chạy vào 9h sáng mỗi ngày
    public void kiemTraVaGuiThongBao() {
        LocalDate ngayHienTai = LocalDate.now();
        LocalDate ngayCanhBao = ngayHienTai.plusDays(5);

        List<RegisterMonth> dangKySapHetHan = registerMonthRepository.findAll().stream()
                .filter(dk -> dk.gettGianHetHan().equals(ngayCanhBao))
                .toList();

        dangKySapHetHan.forEach(this::guiEmailThongBao);
    }

    public Page<RegisterMonth> getRegisterMonthPage(Pageable pageable) {
        return registerMonthRepository.findAll(pageable);
    }

    public String getNextMaDangKy() {
        Long maxId = registerMonthRepository.findMaxMaDangKy();
        return String.valueOf(maxId != null ? maxId + 1 : 1);
    }
}
