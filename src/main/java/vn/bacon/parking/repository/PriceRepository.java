package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.bacon.parking.domain.Price;

public interface PriceRepository extends JpaRepository<Price, String> {

    boolean existsByMaLoaiXeAndMaHinhThuc(VehicleType maLoaiXe, ParkingMode maHinhThuc);
}
