package vn.bacon.parking.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.domain.VehicleType;
import vn.bacon.parking.service.StudentService;
import vn.bacon.parking.service.VehicleService;
import vn.bacon.parking.service.VehicleTypeService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student/vehicle")
public class StudentVehicleController {
    private final VehicleService vehicleService;
    private final VehicleTypeService vehicleTypeService;
    private final StudentService studentService;

    
    public StudentVehicleController(VehicleService vehicleService, VehicleTypeService vehicleTypeService, StudentService studentService) {
        this.vehicleService = vehicleService;
        this.vehicleTypeService = vehicleTypeService;
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String listVehicles(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Student> studentOpt = studentService.getStudentById(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            List<Vehicle> vehicleList = vehicleService.getVehiclesByStudentId(student.getMaSV());
            model.addAttribute("vehicleList", vehicleList);
            return "client/student/vehicle/list";
        }
        return "redirect:/";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("newVehicle", new Vehicle());
        List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
        model.addAttribute("vehicleTypes", vehicleTypes);
        return "client/student/vehicle/create";
    }

    @PostMapping("/create")
    public String createVehicle(@Valid @ModelAttribute("newVehicle") Vehicle newVehicle, BindingResult result, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Student> studentOpt = studentService.getStudentById(username);
        if (!studentOpt.isPresent()) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
            model.addAttribute("vehicleTypes", vehicleTypes);
            return "client/student/vehicle/create";
        }
        if (vehicleService.existsByBienSoXe(newVehicle.getBienSoXe())) {
            result.rejectValue("bienSoXe", "error.vehicle", "Biển số xe đã tồn tại!");
            List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
            model.addAttribute("vehicleTypes", vehicleTypes);
            return "client/student/vehicle/create";
        }
        // Gán sinh viên hiện tại cho xe
        newVehicle.setMaSV(studentOpt.get());
        newVehicle.setMaChuXe(studentOpt.get().getMaSV());
        vehicleService.saveVehicle(newVehicle);
        return "redirect:/student/vehicle/list";
    }
} 