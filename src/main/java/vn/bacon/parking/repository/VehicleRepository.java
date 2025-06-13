package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.Vehicle;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByMaSV_MaSV(String maSV);
    List<Vehicle> findByMaNV_MaNV(String maNV);
    boolean existsById(String bienSoXe);
}
