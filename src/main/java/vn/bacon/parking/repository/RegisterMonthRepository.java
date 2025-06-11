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
    List<RegisterMonth> findByBienSoXe_BienSoXe(String bienSoXe);

    @Query("SELECT r FROM RegisterMonth r WHERE r.bienSoXe.bienSoXe LIKE %:tuKhoa%")
    List<RegisterMonth> timKiemTheoBienSoXe(@Param("tuKhoa") String tuKhoa);

    @Query("SELECT r FROM RegisterMonth r WHERE r.ngayKetThuc >= :ngayHienTai")
    List<RegisterMonth> timDangKyConHieuLuc(@Param("ngayHienTai") LocalDate ngayHienTai);

    @Query("SELECT r FROM RegisterMonth r WHERE r.ngayKetThuc < :ngayHienTai")
    List<RegisterMonth> timDangKyDaHetHan(@Param("ngayHienTai") LocalDate ngayHienTai);

    @Query("SELECT COALESCE(MAX(CAST(maDangKy AS integer)), 0) FROM RegisterMonth")
    Long findMaxMaDangKy();

    boolean existsByBienSoXe_BienSoXeAndTrangThaiInAndNgayKetThucGreaterThanEqual(
            String bienSoXe, List<String> trangThai, LocalDate ngayKetThuc);

    boolean existsByBienSoXe_BienSoXeAndMaDangKyNotAndTrangThaiInAndNgayKetThucGreaterThanEqual(
            String bienSoXe, String maDangKy, List<String> trangThai, LocalDate ngayKetThuc);

    RegisterMonth findByBienSoXeAndNgayKetThucAfter(Vehicle bienSoXe, LocalDate currentDate);
}