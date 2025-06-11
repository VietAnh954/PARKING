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
import vn.bacon.parking.domain.Student;
import vn.bacon.parking.domain.Vehicle;
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

    @Autowired
    public MonthlyRegistrationRequestController(
            MonthlyRegistrationRequestService requestService,
            VehicleService vehicleService,
            StudentService studentService) {
        this.requestService = requestService;
        this.vehicleService = vehicleService;
        this.studentService = studentService;
    }

    @GetMapping("/request-monthly-registration")
    public String showForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String studentId = auth.getName();
        List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(studentId);
        model.addAttribute("request", new MonthlyRegistrationRequest());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("studentId", studentId);
        return "client/student/request-monthly-registration/request";
    }

    @PostMapping("/request-monthly-registration")
    public String submitRequest(
            @RequestParam String vehicleId,
            @RequestParam("startDate") String startDate,
            Model model) {
        logger.info("Processing POST request for /student/request-monthly-registration");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String studentId = auth.getName();

        try {
            String requestId = getNextRequestId();
            LocalDate requestDate = LocalDate.now();
            LocalDate parsedStartDate = LocalDate.parse(startDate);
            LocalDate endDate = parsedStartDate.plusMonths(1);

            if (requestService.hasActiveRequestForVehicle(vehicleId)) {
                model.addAttribute("error", "An active request already exists for this vehicle.");
                List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(studentId);
                model.addAttribute("vehicles", vehicles);
                model.addAttribute("studentId", studentId);
                return "client/student/request-monthly-registration/request";
            }

            MonthlyRegistrationRequest request = new MonthlyRegistrationRequest();
            request.setRequestId(requestId);
            Student student = studentService.getStudentById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
            request.setStudent(student);
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId)
                    .orElseThrow(
                            () -> new IllegalArgumentException("Vehicle not found with license plate: " + vehicleId));
            request.setVehicle(vehicle);
            request.setRequestDate(requestDate);
            request.setStartDate(parsedStartDate);
            request.setEndDate(endDate);
            request.setStatus("Chờ duyệt");

            requestService.createRequest(request);
            model.addAttribute("message", "Request submitted successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
        }
        List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(studentId);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("studentId", studentId);
        return "client/student/request-monthly-registration/request";
    }

    @GetMapping("/request-monthly-registration/edit")
    public String showEditForm(@RequestParam String requestId, Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String studentId = auth.getName().trim();
        String trimmedRequestId = requestId.trim();
        logger.info("Attempting to edit request with ID: '{}', trimmed to: '{}', for student: '{}'", requestId,
                trimmedRequestId, studentId);

        Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
        if (!requestOpt.isPresent()) {
            logger.warn("Request not found for ID: '{}'", trimmedRequestId);
            redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
            return "redirect:/student/request-history";
        }

        MonthlyRegistrationRequest request = requestOpt.get();
        String requestMaSV = request.getStudent().getMaSV().trim();
        logger.debug("Comparing studentId: '{}' with request MaSV: '{}'", studentId, requestMaSV);
        if (!requestMaSV.equals(studentId)) {
            logger.warn("Student '{}' does not own request '{}'. Request MaSV: '{}'", studentId, trimmedRequestId,
                    requestMaSV);
            redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền chỉnh sửa yêu cầu này!");
            return "redirect:/student/request-history";
        }

        if (!"Chờ duyệt".equals(request.getStatus())) {
            logger.warn("Request {} has status '{}', not 'Chờ duyệt'", trimmedRequestId, request.getStatus());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Chỉ có thể chỉnh sửa yêu cầu đang ở trạng thái 'Chờ duyệt'!");
            return "redirect:/student/request-history";
        }

        List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(studentId);
        model.addAttribute("request", request);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("studentId", studentId);
        return "client/student/request-monthly-registration/edit";
    }

    @PostMapping("/request-monthly-registration/edit")
    public String updateRequest(
            @RequestParam String requestId,
            @RequestParam String vehicleId,
            @RequestParam("startDate") String startDate,
            Model model,
            RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }
        String studentId = auth.getName().trim();
        String trimmedRequestId = requestId.trim();
        logger.info("Updating request with ID: '{}', trimmed to: '{}', for student: '{}'", requestId, trimmedRequestId,
                studentId);

        try {
            Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
            if (!requestOpt.isPresent()) {
                logger.warn("Request not found for ID: '{}'", trimmedRequestId);
                redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
                return "redirect:/student/request-history";
            }

            MonthlyRegistrationRequest existingRequest = requestOpt.get();
            String requestMaSV = existingRequest.getStudent().getMaSV().trim();
            logger.debug("Comparing studentId: '{}' with request MaSV: '{}'", studentId, requestMaSV);
            if (!requestMaSV.equals(studentId)) {
                logger.warn("Student '{}' does not own request '{}'. Request MaSV: '{}'", studentId, trimmedRequestId,
                        requestMaSV);
                redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền chỉnh sửa yêu cầu này!");
                return "redirect:/student/request-history";
            }

            if (!"Chờ duyệt".equals(existingRequest.getStatus())) {
                logger.warn("Request {} has status '{}', not 'Chờ duyệt'", trimmedRequestId,
                        existingRequest.getStatus());
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Chỉ có thể chỉnh sửa yêu cầu đang ở trạng thái 'Chờ duyệt'!");
                return "redirect:/student/request-history";
            }

            LocalDate parsedStartDate = LocalDate.parse(startDate);
            LocalDate endDate = parsedStartDate.plusMonths(1);

            // Check if the new vehicle has an active request (excluding the current
            // request)
            if (!vehicleId.equals(existingRequest.getVehicle().getBienSoXe()) &&
                    requestService.hasActiveRequestForVehicleExcludingRequestId(vehicleId, trimmedRequestId)) {
                model.addAttribute("error", "Xe này đã có yêu cầu đang hoạt động.");
                List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(studentId);
                model.addAttribute("request", existingRequest);
                model.addAttribute("vehicles", vehicles);
                model.addAttribute("studentId", studentId);
                return "client/student/request-monthly-registration/edit";
            }

            // Update the request
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId)
                    .orElseThrow(() -> new IllegalArgumentException("Xe không tồn tại với biển số: " + vehicleId));
            existingRequest.setVehicle(vehicle);
            existingRequest.setStartDate(parsedStartDate);
            existingRequest.setEndDate(endDate);
            existingRequest.setRequestDate(LocalDate.now());

            requestService.updateRequest(existingRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật yêu cầu thành công!");
            return "redirect:/student/request-history";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            List<Vehicle> vehicles = vehicleService.getVehiclesByStudentId(studentId);
            Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
            model.addAttribute("request", requestOpt.orElse(new MonthlyRegistrationRequest()));
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("studentId", studentId);
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
        String studentId = auth.getName().trim();
        List<MonthlyRegistrationRequest> requests = requestService.getRequestsByStudentId(studentId);
        model.addAttribute("requests", requests);
        model.addAttribute("studentId", studentId);
        return "client/student/request-monthly-registration/history";
    }

    private String getNextRequestId() {
        Long maxId = requestService.getMaxRequestId();
        return String.valueOf(maxId != null ? maxId + 1 : 1);
    }
}