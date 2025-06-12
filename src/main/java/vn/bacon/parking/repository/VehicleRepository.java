package vn.bacon.parking.repository;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findByMaSV_MaSV(String maSV);
<<<<<<< HEAD
    List<Vehicle> findByMaNV_MaNV(String maNV);
=======

    boolean existsById(String bienSoXe);

    List<Vehicle> findByMaNV_MaNV(String maNV);

>>>>>>> Thanh
}
