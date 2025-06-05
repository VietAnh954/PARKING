package vn.bacon.parking.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.bacon.parking.domain.RegisterMonth;
import vn.bacon.parking.service.RegisterMonthService;

@Controller
public class RegisterMonthController {

    private final RegisterMonthService registerMonthService;

    public RegisterMonthController(RegisterMonthService registerMonthService) {
        this.registerMonthService = registerMonthService;
    }

    // Show all register months
    // @GetMapping("admin/registermonth")
    // public String getRegisterMonthsPage(Model model) {
    // List<RegisterMonth> registerMonths =
    // registerMonthService.getAllRegisterMonths();
    // model.addAttribute("registerMonths", registerMonths);
    // return "admin/registermonth/show";
    // }

    @GetMapping("/admin/registermonth")
    public String listRegisterMonth(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RegisterMonth> registerMonthPage = registerMonthService.getRegisterMonthPage(pageable);
        model.addAttribute("registerMonthPage", registerMonthPage);
        model.addAttribute("registerMonthList", registerMonthPage.getContent());
        return "admin/registermonth/show";
    }

    // Create new register month
    @GetMapping("admin/registermonth/create")
    public String getCreateRegisterMonthPage(Model model) {
        model.addAttribute("newRegisterMonth", new RegisterMonth());
        return "admin/registermonth/create";
    }

    @PostMapping("admin/registermonth/create")
    public String createRegisterMonth(@ModelAttribute("newRegisterMonth") RegisterMonth registerMonth) {
        registerMonthService.saveRegisterMonth(registerMonth);
        return "redirect:/admin/registermonth";
    }

    // Update register month
    @GetMapping("admin/registermonth/update/{maDangKy}")
    public String getUpdateRegisterMonthPage(Model model, @PathVariable String maDangKy) {
        Optional<RegisterMonth> currentRegisterMonth = registerMonthService.getRegisterMonthById(maDangKy);
        model.addAttribute("newRegisterMonth", currentRegisterMonth);
        return "admin/registermonth/update";
    }

    @PostMapping("admin/registermonth/update")
    public String updateRegisterMonth(Model model, @ModelAttribute("newRegisterMonth") RegisterMonth registerMonth1) {
        RegisterMonth currentRegisterMonth = this.registerMonthService
                .getRegisterMonthById(registerMonth1.getMaDangKy()).get();
        if (currentRegisterMonth != null) {
            currentRegisterMonth.setMaDangKy(registerMonth1.getMaDangKy());
            currentRegisterMonth.setBienSoXe(registerMonth1.getBienSoXe());
            currentRegisterMonth.settGianDangKy(registerMonth1.gettGianDangKy());
            currentRegisterMonth.settGianHetHan(registerMonth1.gettGianHetHan());
            currentRegisterMonth.setNvGhiNhan(registerMonth1.getNvGhiNhan());
            this.registerMonthService.saveRegisterMonth(currentRegisterMonth);
        }
        return "redirect:/admin/registermonth";
    }

    // Delete register month
    @GetMapping("admin/registermonth/delete/{maDangKy}")
    public String getDeleteRegisterMonthPage(Model model, @PathVariable String maDangKy) {
        model.addAttribute("maDangKy", maDangKy);
        model.addAttribute("newRegisterMonth", new RegisterMonth());
        return "admin/registermonth/delete";
    }

    @PostMapping("admin/registermonth/delete")
    public String deleteRegisterMonth(@ModelAttribute("newRegisterMonth") RegisterMonth registerMonth) {
        if (registerMonth.getMaDangKy() != null) {
            Optional<RegisterMonth> existingRegisterMonth = registerMonthService
                    .getRegisterMonthById(registerMonth.getMaDangKy());
            if (existingRegisterMonth.isPresent()) {
                this.registerMonthService.deleteRegisterMonthById(registerMonth.getMaDangKy());
            }
        }
        return "redirect:/admin/registermonth";
    }

    // Gia hạn đăng ký
    @GetMapping("admin/registermonth/extend/{maDangKy}")
    public String getExtendRegisterMonthPage(Model model, @PathVariable String maDangKy) {
        Optional<RegisterMonth> registerMonth = registerMonthService.getRegisterMonthById(maDangKy);
        if (registerMonth.isPresent()) {
            model.addAttribute("registerMonth", registerMonth.get());
            return "admin/registermonth/extend";
        }
        return "redirect:/admin/registermonth";
    }

    @PostMapping("admin/registermonth/extend")
    public String extendRegisterMonth(@RequestParam String maDangKy, @RequestParam int soThang) {
        try {
            registerMonthService.giaHanDangKy(maDangKy, soThang);
        } catch (Exception e) {
            // Xử lý lỗi nếu cần
        }
        return "redirect:/admin/registermonth";
    }

    // Tìm kiếm theo biển số xe
    @GetMapping("admin/registermonth/search")
    public String searchRegisterMonth(@RequestParam("tuKhoa") String tuKhoa, Model model) {
        List<RegisterMonth> registerMonths = registerMonthService.timKiemTheoBienSoXe(tuKhoa);
        model.addAttribute("registerMonthList", registerMonths);
        model.addAttribute("tuKhoa", tuKhoa);
        return "admin/registermonth/show";
    }

    // Lọc đăng ký còn hiệu lực
    @GetMapping("admin/registermonth/active")
    public String getActiveRegisterMonths(Model model) {
        List<RegisterMonth> registerMonths = registerMonthService.timDangKyConHieuLuc();
        model.addAttribute("registerMonthList", registerMonths);
        model.addAttribute("filter", "active");
        return "admin/registermonth/show";
    }

    // Lọc đăng ký đã hết hạn
    @GetMapping("admin/registermonth/expired")
    public String getExpiredRegisterMonths(Model model) {
        List<RegisterMonth> registerMonths = registerMonthService.timDangKyDaHetHan();
        model.addAttribute("registerMonthList", registerMonths);
        model.addAttribute("filter", "expired");
        return "admin/registermonth/show";
    }

    // Test gửi email thông báo
    @GetMapping("admin/registermonth/test-email/{maDangKy}")
    public String testEmailNotification(@PathVariable String maDangKy, Model model) {
        try {
            registerMonthService.testGuiEmailThongBao(maDangKy);
            model.addAttribute("successMessage", "Đã gửi email thông báo thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi gửi email: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Test kiểm tra và gửi tất cả email thông báo
    @GetMapping("admin/registermonth/test-all-emails")
    public String testAllEmailNotifications(Model model) {
        try {
            registerMonthService.kiemTraVaGuiThongBao();
            model.addAttribute("successMessage", "Đã kiểm tra và gửi email thông báo cho tất cả đăng ký sắp hết hạn!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi gửi email: " + e.getMessage());
        }
        return "redirect:/admin/registermonth";
    }

    // Hiển thị danh sách đăng ký sắp hết hạn (trước 5 ngày)
    @GetMapping("admin/registermonth/expiring-soon")
    public String getExpiringSoonRegisterMonths(Model model) {
        List<RegisterMonth> expiringSoon = registerMonthService.getRegisterMonthsExpiringSoon();
        model.addAttribute("registerMonths", expiringSoon);
        return "admin/registermonth/expiring-soon";
    }

    // Gửi email cho một đăng ký sắp hết hạn
    @GetMapping("admin/registermonth/expiring-soon/send-email/{maDangKy}")
    public String sendEmailExpiringSoon(@PathVariable String maDangKy, Model model) {
        try {
            registerMonthService.testGuiEmailThongBao(maDangKy);
            model.addAttribute("successMessage", "Đã gửi email thông báo thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi gửi email: " + e.getMessage());
        }
        return "redirect:/admin/registermonth/expiring-soon";
    }

    // Gửi email cho tất cả đăng ký sắp hết hạn
    @GetMapping("admin/registermonth/expiring-soon/send-all-emails")
    public String sendAllEmailsExpiringSoon(Model model) {
        List<RegisterMonth> expiringSoon = registerMonthService.getRegisterMonthsExpiringSoon();
        int count = 0;
        for (RegisterMonth rm : expiringSoon) {
            try {
                registerMonthService.testGuiEmailThongBao(rm.getMaDangKy());
                count++;
            } catch (Exception ignored) {
            }
        }
        model.addAttribute("successMessage", "Đã gửi email cho " + count + " đăng ký sắp hết hạn!");
        return "redirect:/admin/registermonth/expiring-soon";
    }

}
