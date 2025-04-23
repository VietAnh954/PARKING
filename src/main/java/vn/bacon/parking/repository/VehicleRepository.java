package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}
