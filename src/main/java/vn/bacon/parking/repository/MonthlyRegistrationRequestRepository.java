package vn.bacon.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.MonthlyRegistrationRequest;

@Repository
public interface MonthlyRegistrationRequestRepository extends JpaRepository<MonthlyRegistrationRequest, String> {

    boolean existsByVehicleBienSoXeAndStatusNot(String bienSoXe, String status);

    boolean existsByVehicleBienSoXeAndRequestIdNotAndStatusIn(
            @Param("bienSoXe") String bienSoXe,
            @Param("requestId") String requestId,
            @Param("statuses") List<String> statuses);

    List<MonthlyRegistrationRequest> findByStudentMaSV(String maSV);

    @Query("SELECT MAX(CAST(m.requestId AS integer)) FROM MonthlyRegistrationRequest m")
    Long findMaxRequestId();

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
            "FROM MonthlyRegistrationRequest m " +
            "WHERE m.vehicle.bienSoXe = :bienSoXe " +
            "AND m.status NOT IN ('Từ chối', 'Đã hủy')")
    boolean existsActiveRequestForVehicle(@Param("bienSoXe") String bienSoXe);
}