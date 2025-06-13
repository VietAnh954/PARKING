package vn.bacon.parking.controller.admin;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.bacon.parking.domain.RegisterMonth;
import vn.bacon.parking.service.RegisterMonthService;

@Controller
@RequestMapping("/admin/registermonth")
public class RegisterMonthController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterMonthController.class);

    private final RegisterMonthService registerMonthService;

    public RegisterMonthController(RegisterMonthService registerMonthService) {
        this.registerMonthService = registerMonthService;
    }

    // Show all register months with pagination
    @GetMapping
    public String listRegisterMonth(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        logger.info("Fetching register months for page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<RegisterMonth> registerMonthPage = registerMonthService.getRegisterMonthPage(pageable);
        model.addAttribute("registerMonthPage", registerMonthPage);
        model.addAttribute("registerMonthList", registerMonthPage.getContent());
        return "admin/registermonth/show";
    }

    // Create new register month
    @GetMapping("/create")
    public String getCreateRegisterMonthPage(Model model) {
        model.addAttribute("newRegisterMonth", new RegisterMonth());
        return "admin/registermonth/create";
    }

    @PostMapping("/create")
    public String createRegisterMonth(@ModelAttribute("newRegisterMonth") RegisterMonth registerMonth,
            RedirectAttributes redirectAttributes) {
        logger.info("Creating new register month with maDangKy: {}", registerMonth.getMaDangKy());
        try {
            registerMonthService.saveRegisterMonth(registerMonth);
            redirectAttributes.addFlashAttribute("successMessage", "Đã tạo đăng ký tháng thành công!");
        } catch (IllegalArgumentException e) {
            logger.error("Failed to create register month: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi tạo đăng ký: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating register month: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi bất ngờ: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Update register month
    @GetMapping("/update/{maDangKy}")
    public String getUpdateRegisterMonthPage(@PathVariable String maDangKy, Model model,
            RedirectAttributes redirectAttributes) {
        logger.info("Fetching register month for update with maDangKy: {}", maDangKy);
        Optional<RegisterMonth> currentRegisterMonth = registerMonthService.getRegistrationById(maDangKy.trim());
        if (!currentRegisterMonth.isPresent()) {
            logger.warn("Register month not found with maDangKy: {}", maDangKy);
            redirectAttributes.addFlashAttribute("errorMessage", "Đăng ký không tồn tại!");
            return "redirect:/admin/registermonth";
        }
        model.addAttribute("newRegisterMonth", currentRegisterMonth.get());
        return "admin/registermonth/update";
    }

    @PostMapping("/update")
    public String updateRegisterMonth(@ModelAttribute("newRegisterMonth") RegisterMonth registerMonth,
            RedirectAttributes redirectAttributes) {
        logger.info("Updating register month with maDangKy: {}", registerMonth.getMaDangKy());
        try {
            Optional<RegisterMonth> existingRegisterMonth = registerMonthService
                    .getRegistrationById(registerMonth.getMaDangKy().trim());
            if (!existingRegisterMonth.isPresent()) {
                logger.warn("Register month not found with maDangKy: {}", registerMonth.getMaDangKy());
                redirectAttributes.addFlashAttribute("errorMessage", "Đăng ký không tồn tại!");
                return "redirect:/admin/registermonth";
            }
            RegisterMonth currentRegisterMonth = existingRegisterMonth.get();
            currentRegisterMonth.setBienSoXe(registerMonth.getBienSoXe());
            currentRegisterMonth.setMaNV(registerMonth.getMaNV());
            currentRegisterMonth.setNgayDangKy(registerMonth.getNgayDangKy());
            currentRegisterMonth.setNgayBatDau(registerMonth.getNgayBatDau());
            currentRegisterMonth.setNgayKetThuc(registerMonth.getNgayKetThuc());
            currentRegisterMonth.setTrangThai(registerMonth.getTrangThai());
            currentRegisterMonth.setGhiChu(registerMonth.getGhiChu());
            currentRegisterMonth.setBangGia(registerMonth.getBangGia());
            registerMonthService.saveRegisterMonth(currentRegisterMonth);
            redirectAttributes.addFlashAttribute("successMessage", "Đã cập nhật đăng ký tháng thành công!");
        } catch (IllegalArgumentException e) {
            logger.error("Failed to update register month: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật đăng ký: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating register month: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi bất ngờ: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Delete register month
    @GetMapping("/delete/{maDangKy}")
    public String getDeleteRegisterMonthPage(@PathVariable String maDangKy, Model model,
            RedirectAttributes redirectAttributes) {
        logger.info("Fetching register month for deletion with maDangKy: {}", maDangKy);
        Optional<RegisterMonth> registerMonth = registerMonthService.getRegistrationById(maDangKy.trim());
        if (!registerMonth.isPresent()) {
            logger.warn("Register month not found with maDangKy: {}", maDangKy);
            redirectAttributes.addFlashAttribute("errorMessage", "Đăng ký không tồn tại!");
            return "redirect:/admin/registermonth";
        }
        model.addAttribute("maDangKy", maDangKy);
        model.addAttribute("newRegisterMonth", registerMonth.get());
        return "admin/registermonth/delete";
    }

    @PostMapping("/delete")
    public String deleteRegisterMonth(@RequestParam String maDangKy, RedirectAttributes redirectAttributes) {
        logger.info("Deleting register month with maDangKy: {}", maDangKy);
        try {
            Optional<RegisterMonth> existingRegisterMonth = registerMonthService
                    .getRegistrationById(maDangKy.trim());
            if (!existingRegisterMonth.isPresent()) {
                logger.warn("Register month not found with maDangKy: {}", maDangKy);
                redirectAttributes.addFlashAttribute("errorMessage", "Đăng ký không tồn tại!");
            } else {
                registerMonthService.deleteRegisterMonthById(maDangKy.trim());
                redirectAttributes.addFlashAttribute("successMessage", "Đã xóa đăng ký tháng thành công!");
            }
        } catch (RuntimeException e) {
            logger.error("Failed to delete register month: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa đăng ký: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while deleting register month: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi bất ngờ: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Extend register month
    @GetMapping("/extend/{maDangKy}")
    public String getExtendRegisterMonthPage(@PathVariable String maDangKy, Model model,
            RedirectAttributes redirectAttributes) {
        logger.info("Fetching register month for extension with maDangKy: {}", maDangKy);
        Optional<RegisterMonth> registerMonth = registerMonthService.getRegistrationById(maDangKy.trim());
        if (!registerMonth.isPresent()) {
            logger.warn("Register month not found with maDangKy: {}", maDangKy);
            redirectAttributes.addFlashAttribute("errorMessage", "Đăng ký không tồn tại!");
            return "redirect:/admin/registermonth";
        }
        model.addAttribute("registerMonth", registerMonth.get());
        return "admin/registermonth/extend";
    }

    @PostMapping("/extend")
    public String extendRegisterMonth(@RequestParam String maDangKy, @RequestParam int soThang,
            RedirectAttributes redirectAttributes) {
        logger.info("Extending register month with maDangKy: {} for {} months", maDangKy, soThang);
        try {
            registerMonthService.giaHanDangKy(maDangKy.trim(), soThang);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Đã gia hạn đăng ký tháng thành công cho " + soThang + " tháng!");
        } catch (IllegalArgumentException e) {
            logger.error("Failed to extend register month: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi gia hạn: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while extending register month: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi bất ngờ: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Search by license plate
    @GetMapping("/search")
    public String searchRegisterMonth(@RequestParam("tuKhoa") String tuKhoa, Model model) {
        logger.info("Searching register months with tuKhoa: {}", tuKhoa);
        List<RegisterMonth> registerMonths = registerMonthService.timKiemTheoBienSoXe(tuKhoa);
        model.addAttribute("registerMonthList", registerMonths);
        model.addAttribute("tuKhoa", tuKhoa);
        return "admin/registermonth/show";
    }

    // Filter active register months
    @GetMapping("/active")
    public String getActiveRegisterMonths(Model model) {
        logger.info("Fetching active register months");
        List<RegisterMonth> registerMonths = registerMonthService.timDangKyConHieuLuc();
        model.addAttribute("registerMonthList", registerMonths);
        model.addAttribute("filter", "active");
        return "admin/registermonth/show";
    }

    // Filter expired register months
    @GetMapping("/expired")
    public String getExpiredRegisterMonths(Model model) {
        logger.info("Fetching expired register months");
        List<RegisterMonth> registerMonths = registerMonthService.timDangKyDaHetHan();
        model.addAttribute("registerMonthList", registerMonths);
        model.addAttribute("filter", "expired");
        return "admin/registermonth/show";
    }

    // Test email notification for a single registration
    @GetMapping("/test-email/{maDangKy}")
    public String testEmailNotification(@PathVariable String maDangKy, RedirectAttributes redirectAttributes) {
        logger.info("Testing email notification for maDangKy: {}", maDangKy);
        try {
            registerMonthService.testGuiEmailThongBao(maDangKy.trim());
            redirectAttributes.addFlashAttribute("successMessage", "Đã gửi email thông báo thành công!");
        } catch (Exception e) {
            logger.error("Failed to send email notification: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi gửi email: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Test sending all email notifications
    @GetMapping("/test-all-emails")
    public String testAllEmailNotifications(RedirectAttributes redirectAttributes) {
        logger.info("Testing all email notifications");
        try {
            registerMonthService.kiemTraVaGuiThongBao();
            redirectAttributes.addFlashAttribute("successMessage",
                    "Đã kiểm tra và gửi email thông báo cho tất cả đăng ký sắp hết hạn!");
        } catch (Exception e) {
            logger.error("Failed to send all email notifications: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi gửi email: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Show expiring soon register months (within 5 days)
    @GetMapping("/expiring-soon")
    public String getExpiringSoonRegisterMonths(Model model) {
        logger.info("Fetching register months expiring soon");
        List<RegisterMonth> expiringSoon = registerMonthService.getRegisterMonthsExpiringSoon();
        model.addAttribute("registerMonths", expiringSoon);
        return "admin/registermonth/expiring-soon";
    }

    // Send email for a single expiring soon registration
    @GetMapping("/expiring-soon/send-email/{maDangKy}")
    public String sendEmailExpiringSoon(@PathVariable String maDangKy, RedirectAttributes redirectAttributes) {
        logger.info("Sending email for expiring soon registration with maDangKy: {}", maDangKy);
        try {
            registerMonthService.testGuiEmailThongBao(maDangKy.trim());
            redirectAttributes.addFlashAttribute("successMessage", "Đã gửi email thông báo thành công!");
        } catch (Exception e) {
            logger.error("Failed to send email for expiring soon registration: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi gửi email: " + e.getMessage());
        }
        return "redirect:/admin/registermonth/expiring-soon";
    }

    // Send emails for all expiring soon registrations
    @GetMapping("/expiring-soon/send-all-emails")
    public String sendAllEmailsExpiringSoon(RedirectAttributes redirectAttributes) {
        logger.info("Sending emails for all expiring soon registrations");
        List<RegisterMonth> expiringSoon = registerMonthService.getRegisterMonthsExpiringSoon();
        int count = 0;
        for (RegisterMonth rm : expiringSoon) {
            try {
                registerMonthService.testGuiEmailThongBao(rm.getMaDangKy());
                count++;
            } catch (Exception e) {
                logger.warn("Failed to send email for maDangKy: {}", rm.getMaDangKy(), e);
            }
        }
        redirectAttributes.addFlashAttribute("successMessage", "Đã gửi email cho " + count + " đăng ký sắp hết hạn!");
        return "redirect:/admin/registermonth/expiring-soon";
    }
}