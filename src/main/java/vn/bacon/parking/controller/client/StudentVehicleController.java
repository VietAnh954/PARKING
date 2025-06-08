package vn.bacon.parking.controller.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.service.StudentService;
import vn.bacon.parking.service.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student/vehicle")
public class StudentVehicleController {
    private final VehicleService vehicleService;
    private final StudentService studentService;

    public StudentVehicleController(VehicleService vehicleService, StudentService studentService) {
        this.vehicleService = vehicleService;
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
} 