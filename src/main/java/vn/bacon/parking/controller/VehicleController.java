package vn.bacon.parking.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.service.VehicleService;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // @GetMapping("/admin/vehicle")
    // public String getVehiclePage(Model model) {
    // List<Vehicle> vehicles = this.vehicleService.getAllVehicles();
    // model.addAttribute("vehicleshow", vehicles);
    // return "admin/vehicle/show";
    // }

    @GetMapping("admin/vehicle/create")
    public String getCreateVehiclePage(Model model) {
        model.addAttribute("newVehicle", new Vehicle());
        return "admin/vehicle/create";
    }

    @PostMapping("admin/vehicle/create")
    public String createVehicle(@ModelAttribute("newVehicle") Vehicle vehicle1) {
        this.vehicleService.saveVehicle(vehicle1);
        return "redirect:/admin/vehicle";
    }

    // Update vehicle
    @GetMapping("admin/vehicle/update/{bienSoXe}")
    public String getUpdateVehiclePage(Model model, @PathVariable String bienSoXe) {
        Vehicle currentVehicle = this.vehicleService.getVehicleById(bienSoXe).get();
        model.addAttribute("newVehicle", currentVehicle);
        return "admin/vehicle/update";
    }

    @PostMapping("admin/vehicle/update")
    public String updateVehicle(Model model, @ModelAttribute("newVehicle") Vehicle vehicle1) {
        Vehicle currentVehicle = this.vehicleService.getVehicleById(vehicle1.getBienSoXe()).get();
        if (currentVehicle != null) {
            currentVehicle.setBienSoXe(vehicle1.getBienSoXe());
            currentVehicle.setMaLoaiXe(vehicle1.getMaLoaiXe());
            currentVehicle.setMaNV(vehicle1.getMaNV());
            currentVehicle.setMaSV(vehicle1.getMaSV());
            this.vehicleService.saveVehicle(currentVehicle);
        }
        return "redirect:/admin/vehicle";
    }

    @GetMapping("admin/vehicle/delete/{bienSoXe}")
    public String deleteVehicle(Model model, @PathVariable String bienSoXe) {
        model.addAttribute("newVehicle", new Vehicle());
        return "admin/vehicle/delete";
    }

    @PostMapping("admin/vehicle/delete")
    public String deleteVehiclePost(@ModelAttribute("newVehicle") Vehicle vehicle1) {
        this.vehicleService.deleteVehicleById(vehicle1.getBienSoXe());
        return "redirect:/admin/vehicle";
    }

    @GetMapping("/admin/vehicle")
    public String listVehicle(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vehicle> vehiclePage = vehicleService.getVehiclePage(pageable);
        model.addAttribute("vehiclePage", vehiclePage);
        model.addAttribute("vehicleList", vehiclePage.getContent());
        return "admin/vehicle/show";
    }
}
