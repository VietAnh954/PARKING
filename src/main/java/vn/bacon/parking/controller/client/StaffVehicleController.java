package vn.bacon.parking.controller.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.bacon.parking.domain.Staff;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.service.StaffService;
import vn.bacon.parking.service.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff/vehicle")
public class StaffVehicleController {
    private final VehicleService vehicleService;
    private final StaffService staffService;

    public StaffVehicleController(VehicleService vehicleService, StaffService staffService) {
        this.vehicleService = vehicleService;
        this.staffService = staffService;
    }

    @GetMapping("/list")
    public String listVehicles(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Staff> staffOpt = staffService.getStaffById(username);
        if (staffOpt.isPresent()) {
            Staff staff = staffOpt.get();
            List<Vehicle> vehicleList = vehicleService.getVehiclesByStaffId(staff.getMaNV());
            model.addAttribute("vehicleList", vehicleList);
            return "client/staff/vehicle/list";
        }
        return "redirect:/";
    }
}