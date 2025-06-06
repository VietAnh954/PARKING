package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.repository.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(String bienSoXe) {
        return this.vehicleRepository.findById(bienSoXe);
    }

    public void deleteVehicleById(String bienSoXe) {
        this.vehicleRepository.deleteById(bienSoXe);
    }

    public Page<Vehicle> getVehiclePage(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    public List<Vehicle> getVehiclesByStudentId(String maSV) {
        return vehicleRepository.findByMaSV_MaSV(maSV);
    }

    public boolean existsByBienSoXe(String bienSoXe) {
        return vehicleRepository.existsById(bienSoXe);
    }
}
