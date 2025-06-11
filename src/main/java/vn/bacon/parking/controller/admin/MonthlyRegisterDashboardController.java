package vn.bacon.parking.controller.admin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.bacon.parking.domain.MonthlyRegistrationRequest;
import vn.bacon.parking.domain.RegisterMonth;
import vn.bacon.parking.service.MonthlyRegistrationRequestService;
import vn.bacon.parking.service.RegisterMonthService;
import vn.bacon.parking.service.StaffService;
import vn.bacon.parking.service.StudentService;

@Controller
@RequestMapping("/admin/request")
public class MonthlyRegisterDashboardController {

    private static final Logger logger = LoggerFactory.getLogger(MonthlyRegisterDashboardController.class);

    private final MonthlyRegistrationRequestService requestService;
    private final RegisterMonthService registerMonthService;
    private final StaffService staffService;
    private final StudentService studentService;

    @Autowired
    public MonthlyRegisterDashboardController(MonthlyRegistrationRequestService requestService,
            RegisterMonthService registerMonthService,
            StaffService staffService,
            StudentService studentService) {
        this.requestService = requestService;
        this.registerMonthService = registerMonthService;
        this.staffService = staffService;
        this.studentService = studentService;
    }

    @GetMapping
    public String listRequests(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortByStatus,
            Model model) {
        Sort sort = Sort.by("status").ascending();
        if ("desc".equalsIgnoreCase(sortByStatus)) {
            sort = Sort.by("status").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<MonthlyRegistrationRequest> requestPage = requestService.getAllRequests(pageable);
        model.addAttribute("requestPage", requestPage);
        model.addAttribute("currentSort", sortByStatus);
        return "admin/request-register/show";
    }

    @GetMapping("/approve/{requestId}")
    public String showApproveForm(@PathVariable String requestId, Model model, RedirectAttributes redirectAttributes) {
        String trimmedRequestId = requestId.trim();
        logger.info("Attempting to approve request with ID: '{}', trimmed to: '{}'", requestId, trimmedRequestId);

        Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
        if (!requestOpt.isPresent()) {
            logger.warn("Request not found for ID: '{}'", trimmedRequestId);
            redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
            return "redirect:/admin/request";
        }

        MonthlyRegistrationRequest request = requestOpt.get();
        if (!"Chờ duyệt".equals(request.getStatus())) {
            logger.warn("Request {} has status '{}', not 'Chờ duyệt'", trimmedRequestId, request.getStatus());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Chỉ có thể duyệt yêu cầu đang ở trạng thái 'Chờ duyệt'!");
            return "redirect:/admin/request";
        }

        model.addAttribute("request", request);
        return "admin/request-register/approve";
    }

    @PostMapping("/approve")
    public String approveRequest(@RequestParam String requestId,
            @RequestParam(required = false) String note,
            RedirectAttributes redirectAttributes) {
        String trimmedRequestId = requestId.trim();
        logger.info("Processing approve request with ID: '{}', trimmed to: '{}'", requestId, trimmedRequestId);

        Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
        if (!requestOpt.isPresent()) {
            logger.warn("Request not found for ID: '{}'", trimmedRequestId);
            redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
            return "redirect:/admin/request";
        }

        MonthlyRegistrationRequest request = requestOpt.get();
        if (!"Chờ duyệt".equals(request.getStatus())) {
            logger.warn("Request {} has status '{}', not 'Chờ duyệt'", trimmedRequestId, request.getStatus());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Chỉ có thể duyệt yêu cầu đang ở trạng thái 'Chờ duyệt'!");
            return "redirect:/admin/request";
        }

        request.setProcessedBy(staffService.getCurrentStaff());
        request.setNote(note);
        request.setStatus("Đã duyệt");

        RegisterMonth registerMonth = new RegisterMonth();
        registerMonth.setMaDangKy(registerMonthService.getNextMaDangKy());
        registerMonth.setBienSoXe(request.getVehicle());
        registerMonth.settGianDangKy(LocalDate.now()); // Current date: 2025-06-07
        registerMonth.settGianHetHan(request.getEndDate());
        registerMonth.setNvGhiNhan(staffService.getCurrentStaff());
        registerMonthService.saveRegisterMonth(registerMonth);

        requestService.updateRequest(request);
        redirectAttributes.addFlashAttribute("successMessage", "Yêu cầu " + trimmedRequestId + " đã được duyệt!");
        return "redirect:/admin/request";
    }

    @PostMapping("/reject")
    public String rejectRequest(@RequestParam String requestId,
            @RequestParam String note,
            RedirectAttributes redirectAttributes) {
        String trimmedRequestId = requestId.trim();
        logger.info("Processing reject request with ID: '{}', trimmed to: '{}'", requestId, trimmedRequestId);

        Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
        if (!requestOpt.isPresent()) {
            logger.warn("Request not found for ID: '{}'", trimmedRequestId);
            redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
            return "redirect:/admin/request";
        }

        MonthlyRegistrationRequest request = requestOpt.get();
        if (!"Chờ duyệt".equals(request.getStatus())) {
            logger.warn("Request {} has status '{}', not 'Chờ duyệt'", trimmedRequestId, request.getStatus());
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Chỉ có thể từ chối yêu cầu đang ở trạng thái 'Chờ duyệt'!");
            return "redirect:/admin/request";
        }

        request.setProcessedBy(staffService.getCurrentStaff());
        request.setNote(note);
        request.setStatus("Từ chối");
        requestService.updateRequest(request);
        redirectAttributes.addFlashAttribute("successMessage", "Yêu cầu " + trimmedRequestId + " đã bị từ chối!");
        return "redirect:/admin/request";
    }

    @GetMapping("/view/{requestId}")
    public String viewRequest(@PathVariable String requestId, Model model, RedirectAttributes redirectAttributes) {
        String trimmedRequestId = requestId.trim();
        logger.info("Viewing request with ID: '{}', trimmed to: '{}'", requestId, trimmedRequestId);

        Optional<MonthlyRegistrationRequest> requestOpt = requestService.getRequestById(trimmedRequestId);
        if (!requestOpt.isPresent()) {
            logger.warn("Request not found for ID: '{}'", trimmedRequestId);
            redirectAttributes.addFlashAttribute("errorMessage", "Yêu cầu không tồn tại!");
            return "redirect:/admin/request";
        }

        model.addAttribute("request", requestOpt.get());
        return "admin/request-register/view";
    }
}