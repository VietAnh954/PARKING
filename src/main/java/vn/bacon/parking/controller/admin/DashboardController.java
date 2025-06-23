package vn.bacon.parking.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.bacon.parking.repository.ParkingLotRepository;
import vn.bacon.parking.service.StudentService;
import vn.bacon.parking.service.StaffService;
import vn.bacon.parking.service.RegisterMonthService;
import vn.bacon.parking.repository.EntryExitDetailRepository;
import vn.bacon.parking.domain.EntryExitDetail;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.domain.VehicleType;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    private final ParkingLotRepository parkingLotRepository;
    private final StudentService studentService;
    private final StaffService staffService;
    private final RegisterMonthService registerMonthService;
    private final EntryExitDetailRepository entryExitDetailRepository;

    public DashboardController(ParkingLotRepository parkingLotRepository,
            StudentService studentService,
            StaffService staffService,
            RegisterMonthService registerMonthService,
            EntryExitDetailRepository entryExitDetailRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.studentService = studentService;
        this.staffService = staffService;
        this.registerMonthService = registerMonthService;
        this.entryExitDetailRepository = entryExitDetailRepository;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {
        model.addAttribute("parkingLotList", parkingLotRepository.findAll());
        // Tổng số sinh viên
        int totalStudents = studentService.getAllStudents().size();
        // Tổng số nhân viên
        int totalStaffs = staffService.getAllStaffs().size();
        // Tổng số yêu cầu đăng ký tháng chờ duyệt
        long totalPendingMonthly = registerMonthService.getAllRegisterMonths().stream()
                .filter(r -> "Chờ duyệt".equals(r.getTrangThai())).count();
        // Lấy tất cả xe đang trong bãi (tgRa == null)
        List<EntryExitDetail> vehiclesInParking = entryExitDetailRepository.findAll().stream()
                .filter(e -> e.getTgRa() == null)
                .collect(Collectors.toList());
        // Đếm xe máy và ô tô
        long totalMotorbikes = vehiclesInParking.stream()
                .filter(e -> e.getBienSoXe() != null && e.getBienSoXe().getMaLoaiXe() != null &&
                        (e.getBienSoXe().getMaLoaiXe().getTenLoaiXe().equalsIgnoreCase("Xe máy") ||
                                e.getBienSoXe().getMaLoaiXe().getTenLoaiXe().toLowerCase().contains("máy")))
                .count();
        long totalCars = vehiclesInParking.stream()
                .filter(e -> e.getBienSoXe() != null && e.getBienSoXe().getMaLoaiXe() != null &&
                        (e.getBienSoXe().getMaLoaiXe().getTenLoaiXe().equalsIgnoreCase("Ô tô") ||
                                e.getBienSoXe().getMaLoaiXe().getTenLoaiXe().toLowerCase().contains("ô tô") ||
                                e.getBienSoXe().getMaLoaiXe().getTenLoaiXe().toLowerCase().contains("oto")))
                .count();
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("totalStaffs", totalStaffs);
        model.addAttribute("totalPendingMonthly", totalPendingMonthly);
        model.addAttribute("totalMotorbikes", totalMotorbikes);
        model.addAttribute("totalCars", totalCars);
        return "admin/dashboard/show";
    }
}