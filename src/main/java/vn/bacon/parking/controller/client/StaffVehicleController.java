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
<<<<<<< HEAD
import vn.bacon.parking.domain.EntryExitDetail;
import vn.bacon.parking.repository.EntryExitDetailRepository;
=======
<<<<<<< HEAD
=======
import vn.bacon.parking.domain.EntryExitDetail;
import vn.bacon.parking.repository.EntryExitDetailRepository;
>>>>>>> Thanh
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff/vehicle")
public class StaffVehicleController {
    private final VehicleService vehicleService;
    private final StaffService staffService;
<<<<<<< HEAD
    private final EntryExitDetailRepository entryExitDetailRepository;
=======
<<<<<<< HEAD
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5

    public StaffVehicleController(VehicleService vehicleService, StaffService staffService, EntryExitDetailRepository entryExitDetailRepository) {
        this.vehicleService = vehicleService;
        this.staffService = staffService;
<<<<<<< HEAD
        this.entryExitDetailRepository = entryExitDetailRepository;
=======
=======
    private final EntryExitDetailRepository entryExitDetailRepository;

    public StaffVehicleController(VehicleService vehicleService, StaffService staffService, EntryExitDetailRepository entryExitDetailRepository) {
        this.vehicleService = vehicleService;
        this.staffService = staffService;
        this.entryExitDetailRepository = entryExitDetailRepository;
>>>>>>> Thanh
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5

    @GetMapping("/history")
    public String vehicleHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Staff> staffOpt = staffService.getStaffById(username);
        if (staffOpt.isPresent()) {
            Staff staff = staffOpt.get();
            List<Vehicle> vehicleList = vehicleService.getVehiclesByStaffId(staff.getMaNV());
            List<EntryExitDetail> entryExitList = new java.util.ArrayList<>();
            for (Vehicle v : vehicleList) {
                entryExitList.addAll(entryExitDetailRepository.findAll().stream()
                    .filter(e -> e.getBienSoXe() != null && e.getBienSoXe().getBienSoXe().equals(v.getBienSoXe()))
                    .toList());
            }
            model.addAttribute("entryExitList", entryExitList);
            return "client/staff/vehicle/history";
        }
        return "redirect:/";
    }
<<<<<<< HEAD
=======
>>>>>>> Thanh
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5
}