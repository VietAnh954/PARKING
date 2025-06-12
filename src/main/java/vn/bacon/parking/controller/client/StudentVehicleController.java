<<<<<<< HEAD
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5
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
=======
package vn.bacon.parking.controller.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.domain.EntryExitDetail;
import vn.bacon.parking.repository.EntryExitDetailRepository;
import vn.bacon.parking.service.StudentService;
import vn.bacon.parking.service.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student/vehicle")
public class StudentVehicleController {
    private final VehicleService vehicleService;
    private final StudentService studentService;
    private final EntryExitDetailRepository entryExitDetailRepository;

    public StudentVehicleController(VehicleService vehicleService, StudentService studentService, EntryExitDetailRepository entryExitDetailRepository) {
        this.vehicleService = vehicleService;
        this.studentService = studentService;
        this.entryExitDetailRepository = entryExitDetailRepository;
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

    @GetMapping("/history")
    public String vehicleHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Student> studentOpt = studentService.getStudentById(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            List<Vehicle> vehicleList = vehicleService.getVehiclesByStudentId(student.getMaSV());
            List<EntryExitDetail> entryExitList = new java.util.ArrayList<>();
            for (Vehicle v : vehicleList) {
                entryExitList.addAll(entryExitDetailRepository.findAll().stream()
                    .filter(e -> e.getBienSoXe() != null && e.getBienSoXe().getBienSoXe().equals(v.getBienSoXe()))
                    .toList());
            }
            model.addAttribute("entryExitList", entryExitList);
            return "client/student/vehicle/history";
        }
        return "redirect:/";
    }
}
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> Thanh
>>>>>>> e7d97f61338e2fd66a6d7fa1ee760a392083ebe5
