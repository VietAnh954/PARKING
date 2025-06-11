package vn.bacon.parking.controller.client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.bacon.parking.domain.MonthlyRegistrationRequest;
import vn.bacon.parking.domain.ParkingMode;
import vn.bacon.parking.domain.Price;
import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
import vn.bacon.parking.repository.ParkingModeRepository;
import vn.bacon.parking.repository.PriceRepository;
import vn.bacon.parking.service.MonthlyRegistrationRequestService;
import vn.bacon.parking.service.StudentService;
import vn.bacon.parking.service.VehicleService;

@Controller
@RequestMapping("/student")
public class MonthlyRegistrationRequestController {

    private static final Logger logger = LoggerFactory.getLogger(MonthlyRegistrationRequestController.class);

    private final MonthlyRegistrationRequestService requestService;
    private final VehicleService vehicleService;
    private final StudentService studentService;
    private final PriceRepository priceRepository;
    private final ParkingModeRepository parkingModeRepository;

    @Autowired
    public MonthlyRegistrationRequestController(
            MonthlyRegistrationRequestService requestService,
            VehicleService vehicleService,
            StudentService studentService,
            PriceRepository priceRepository,
            ParkingModeRepository parkingModeRepository) {
        this.requestService = requestService;
        this.vehicleService = vehicleService;
        this.studentService = studentService;
        this.priceRepository = priceRepository;
        this.parkingModeRepository = parkingModeRepository;
    }

    @GetMapping("/request-monthly-registration")
    public String showForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String maSV = auth.getName();
        List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(maSV);
        model.addAttribute("request", new MonthlyRegistrationRequest());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("maSV", maSV);
        return "client/student/request-monthly-registration/request";
    }

    @PostMapping("/request-monthly-registration")
    public String submitRequest(
            @RequestParam String bienSoXe,
            @RequestParam("ngayBatDau") String ngayBatDau,
            Model model) {
        logger.info("Đang xử lý yêu cầu POST cho /student/request-monthly-registration");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String maSV = auth.getName();

        try {
            String maYeuCau = getNextMaYeuCau();
            LocalDate ngayGuiYeuCau = LocalDate.now();
            LocalDate parsedNgayBatDau = LocalDate.parse(ngayBatDau);
            LocalDate ngayHetHan = parsedNgayBatDau.plusMonths(1);

            if (requestService.hasActiveRequestForVehicle(bienSoXe)) {
                model.addAttribute("error", "Xe này đã có yêu cầu đang hoạt động.");
                List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(maSV);
                model.addAttribute("vehicles", vehicles);
                model.addAttribute("maSV", maSV);
                return "client/student/request-monthly-registration/request";
            }

            MonthlyRegistrationRequest request = new MonthlyRegistrationRequest();
            request.setMaYeuCau(maYeuCau);
            Student student = studentService.getStudentById(maSV)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên với Mã SV: " + maSV));
            request.setStudent(student);
            Vehicle vehicle = vehicleService.getVehicleById(bienSoXe)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy xe với biển số: " + bienSoXe));
            request.setVehicle(vehicle);
            request.setNgayGuiYeuCau(ngayGuiYeuCau);
            request.setNgayBatDau(parsedNgayBatDau);
            request.setNgayHetHan(ngayHetHan);
            request.setTrangThai("Chờ duyệt");

            ParkingMode hinhThuc = parkingModeRepository.findById("HT002")
                    .orElseThrow(() -> new IllegalArgumentException("Hình thức gửi tháng (HT002) không tồn tại."));
            Price price = priceRepository.findByMaHinhThucAndMaLoaiXe(hinhThuc, vehicle.getMaLoaiXe());
            if (price == null) {
                throw new IllegalArgumentException(
                        "Không tìm thấy giá cho xe " + bienSoXe + " với hình thức gửi tháng.");
            }
            request.setBangGia(price);

            requestService.createRequest(request);
            model.addAttribute("message", "Yêu cầu đã được gửi thành công!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi bất ngờ: " + e.getMessage());
        }
        List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(maSV);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("maSV", maSV);
        return "client/student/request-monthly-registration/request";
    }

    @GetMapping("/request-monthly-registration/edit")
    public String showEditForm(@RequestParam String maYeuCau, Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String maSV = auth.getName().trim();
        String trimmedMaYeuCau = maYeuCau.trim();
        logger.info("Đang cố gắng chỉnh sửa yêu cầu với Mã: '{}', đã trim: '{}', cho sinh viên: '{}'", maYeuCau,
                trimmedMaYeuCau, maSV);

        Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedMaYeuCau);
        if (!requestOpt.isPresent()) {
            logger.warn("Không tìm thấy yêu cầu với Mã: '{}'", trimmedMaYeuCau);
            redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
            return "redirect:/student/request-history";
        }

        MonthlyRegistrationRequest request = requestOpt.get();
        String requestMaSV = request.getStudent().getMaSV().trim();
        logger.debug("So sánh maSV: '{}' với MaSV của yêu cầu: '{}'", maSV, requestMaSV);
        if (!requestMaSV.equals(maSV)) {
            logger.warn("Sinh viên '{}' không sở hữu yêu cầu '{}'. MaSV của yêu cầu: '{}'", maSV, trimmedMaYeuCau,
                    requestMaSV);
            redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền chỉnh sửa yêu cầu này!");
            return "redirect:/student/request-history";
        }

        if (!"Chờ duyệt".equals(request.getTrangThai())) {
            logger.warn("Yêu cầu {} có trạng thái '{}', không phải 'Chờ duyệt'", trimmedMaYeuCau,
                    request.getTrangThai());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Chỉ có thể chỉnh sửa yêu cầu đang ở trạng thái 'Chờ duyệt'!");
            return "redirect:/student/request-history";
        }

        List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(maSV);
        model.addAttribute("request", request);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("maSV", maSV);
        return "client/student/request-monthly-registration/edit";
    }

    @PostMapping("/request-monthly-registration/edit")
    public String updateRequest(
            @RequestParam String maYeuCau,
            @RequestParam String bienSoXe,
            @RequestParam("ngayBatDau") String ngayBatDau,
            Model model,
            RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String maSV = auth.getName().trim();
        String trimmedMaYeuCau = maYeuCau.trim();
        logger.info("Đang cập nhật yêu cầu với Mã: '{}', đã trim: '{}', cho sinh viên: '{}'", maYeuCau, trimmedMaYeuCau,
                maSV);

        try {
            Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedMaYeuCau);
            if (!requestOpt.isPresent()) {
                logger.warn("Không tìm thấy yêu cầu với Mã: '{}'", trimmedMaYeuCau);
                redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
                return "redirect:/student/request-history";
            }

            MonthlyRegistrationRequest existingRequest = requestOpt.get();
            String requestMaSV = existingRequest.getStudent().getMaSV().trim();
            logger.debug("So sánh maSV: '{}' với MaSV của yêu cầu: '{}'", maSV, requestMaSV);
            if (!requestMaSV.equals(maSV)) {
                logger.warn("Sinh viên '{}' không sở hữu yêu cầu '{}'. MaSV của yêu cầu: '{}'", maSV, trimmedMaYeuCau,
                        requestMaSV);
                redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền chỉnh sửa yêu cầu này!");
                return "redirect:/student/request-history";
            }

            if (!"Chờ duyệt".equals(existingRequest.getTrangThai())) {
                logger.warn("Yêu cầu {} có trạng thái '{}', không phải 'Chờ duyệt'", trimmedMaYeuCau,
                        existingRequest.getTrangThai());
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Chỉ có thể chỉnh sửa yêu cầu đang ở trạng thái 'Chờ duyệt'!");
                return "redirect:/student/request-history";
            }

            LocalDate parsedNgayBatDau = LocalDate.parse(ngayBatDau);
            LocalDate ngayHetHan = parsedNgayBatDau.plusMonths(1);

            if (!bienSoXe.equals(existingRequest.getVehicle().getBienSoXe()) &&
                    requestService.hasActiveRequestForVehicleExcludingRequestId(bienSoXe, trimmedMaYeuCau)) {
                model.addAttribute("error", "Xe này đã có yêu cầu đang hoạt động.");
                List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(maSV);
                model.addAttribute("request", existingRequest);
                model.addAttribute("vehicles", vehicles);
                model.addAttribute("maSV", maSV);
                return "client/student/request-monthly-registration/edit";
            }

            Vehicle vehicle = vehicleService.getVehicleById(bienSoXe)
                    .orElseThrow(() -> new IllegalArgumentException("Xe không tồn tại với biển số: " + bienSoXe));
            existingRequest.setVehicle(vehicle);
            existingRequest.setNgayBatDau(parsedNgayBatDau);
            existingRequest.setNgayHetHan(ngayHetHan);
            existingRequest.setNgayGuiYeuCau(LocalDate.now());

            ParkingMode hinhThuc = parkingModeRepository.findById("HT002")
                    .orElseThrow(() -> new IllegalArgumentException("Hình thức gửi tháng (HT002) không tồn tại."));
            Price price = priceRepository.findByMaHinhThucAndMaLoaiXe(hinhThuc, vehicle.getMaLoaiXe());
            if (price == null) {
                throw new IllegalArgumentException(
                        "Không tìm thấy giá cho xe " + bienSoXe + " với hình thức gửi tháng.");
            }
            existingRequest.setBangGia(price);

            requestService.updateRequest(existingRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật yêu cầu thành công!");
            return "redirect:/student/request-history";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(maSV);
            Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedMaYeuCau);
            model.addAttribute("request", requestOpt.orElse(new MonthlyRegistrationRequest()));
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("maSV", maSV);
            return "client/student/request-monthly-registration/edit";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật yêu cầu: " + e.getMessage());
            return "redirect:/student/request-history";
        }
    }

    @GetMapping("/request-history")
    public String viewRequestHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String maSV = auth.getName().trim();
        List<MonthlyRegistrationRequest> requests = requestService.getRequestsByStudentId(maSV);
        model.addAttribute("requests", requests);
        model.addAttribute("maSV", maSV);
        return "client/student/request-monthly-registration/history";
    }

    private String getNextMaYeuCau() {
        Long maxId = requestService.getMaxMaYeuCau();
        return String.valueOf(maxId != null ? maxId + 1 : 1);
    }
}