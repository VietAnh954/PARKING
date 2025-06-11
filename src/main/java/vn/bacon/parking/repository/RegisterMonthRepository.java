package vn.bacon.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.RegisterMonth;
import vn.bacon.parking.domain.Vehicle;

@Repository
public interface RegisterMonthRepository extends JpaRepository<RegisterMonth, String> {
    // Tìm kiếm theo biển số xe
    @Query("SELECT d FROM RegisterMonth d WHERE d.bienSoXe.bienSoXe LIKE %:tuKhoa%")
    List<RegisterMonth> timKiemTheoBienSoXe(@Param("tuKhoa") String tuKhoa);

    // Lọc đăng ký còn hiệu lực (ngày hết hạn >= ngày hiện tại)
    @Query("SELECT d FROM RegisterMonth d WHERE d.tGianHetHan >= :ngayHienTai")
    List<RegisterMonth> timDangKyConHieuLuc(@Param("ngayHienTai") LocalDate ngayHienTai);

    // Lọc đăng ký đã hết hạn (ngày hết hạn < ngày hiện tại)
    @Query("SELECT d FROM RegisterMonth d WHERE d.tGianHetHan < :ngayHienTai")
    List<RegisterMonth> timDangKyDaHetHan(@Param("ngayHienTai") LocalDate ngayHienTai);

    // Lấy giá trị lớn nhất của MaDangKy
    @Query("SELECT COALESCE(MAX(CAST(maDangKy AS integer)), 0) FROM RegisterMonth")
    Long findMaxMaDangKy();

    RegisterMonth findByBienSoXeAndTGianHetHanAfter(Vehicle bienSoXe, LocalDate currentDate);
}
