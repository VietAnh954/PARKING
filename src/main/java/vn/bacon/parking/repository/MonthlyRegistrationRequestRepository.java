package vn.bacon.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.MonthlyRegistrationRequest;

@Repository
public interface MonthlyRegistrationRequestRepository extends JpaRepository<MonthlyRegistrationRequest, String> {

        boolean existsByVehicleBienSoXeAndTrangThaiNot(String bienSoXe, String trangThai);

        boolean existsByVehicleBienSoXeAndMaYeuCauNotAndTrangThaiIn(
                        @Param("bienSoXe") String bienSoXe,
                        @Param("maYeuCau") String maYeuCau,
                        @Param("trangThaiList") List<String> trangThaiList);

        List<MonthlyRegistrationRequest> findByStudentMaSV(String maSV);

        @Query("SELECT MAX(CAST(m.maYeuCau AS integer)) FROM MonthlyRegistrationRequest m")
        Long findMaxMaYeuCau();

        @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
                        "FROM MonthlyRegistrationRequest m " +
                        "WHERE m.vehicle.bienSoXe = :bienSoXe " +
                        "AND m.trangThai NOT IN ('Từ chối', 'Đã hủy')")
        boolean existsActiveRequestForVehicle(@Param("bienSoXe") String bienSoXe);

        boolean existsByVehicleBienSoXeAndTrangThaiInAndNgayHetHanGreaterThanEqual(
                        String bienSoXe, List<String> trangThai, LocalDate ngayHetHan);

        boolean existsByVehicleBienSoXeAndMaYeuCauNotAndTrangThaiInAndNgayHetHanGreaterThanEqual(
                        String bienSoXe, String maYeuCau, List<String> trangThai, LocalDate ngayHetHan);
}