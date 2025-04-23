package vn.bacon.parking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.bacon.parking.domain.Account;
import vn.bacon.parking.domain.Staff;
import vn.bacon.parking.service.StaffService;

@Controller
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/admin/staff")
    public String getStaffPage(Model model) {
        List<Staff> staffs = this.staffService.getAllStaffs();
        model.addAttribute("staffshow", staffs);
        return "admin/staff/show";
    }

    @GetMapping("admin/staff/create")
    public String getCreateStaffPage(Model model) {
        model.addAttribute("newStaff", new Staff());
        return "admin/staff/create";
    }

    @PostMapping("admin/staff/create")
    public String createStaff(@ModelAttribute("newStaff") Staff staff1) {
        this.staffService.saveStaff(staff1);
        return "redirect:/admin/staff";
    }

    // Update staff
    @GetMapping("admin/staff/update")
    public String getUpdateStaffPage(Model model, @PathVariable String maNV) {
        Optional<Staff> currentStaff = this.staffService.getStaffById(maNV);
        model.addAttribute("newStaff", currentStaff);
        return "admin/staff/update";
    }

    @PostMapping("admin/staff/update")
    public String updateStaff(Model model, @ModelAttribute("newStaff") Staff staff1) {
        Staff currentStaff = this.staffService.getStaffById(staff1.getMaNV()).get();
        if (currentStaff != null) {
            currentStaff.setMaNV(staff1.getMaNV());
            currentStaff.setHoTen(staff1.getHoTen());
            currentStaff.setSdt(staff1.getSdt());
            currentStaff.setEmail(staff1.getEmail());
            currentStaff.setCccd(staff1.getCccd());
            currentStaff.setChucVu(staff1.getChucVu());
            currentStaff.setNgayVaoLam(staff1.getNgayVaoLam());
            this.staffService.saveStaff(currentStaff);
        }

        return "redirect:/admin/staff";
    }

    // Delete account
    @GetMapping("admin/staff/delete/{maNV}")
    public String getDeleteStaffPage(Model model, @PathVariable String maNV) {
        model.addAttribute("maNV", maNV);

        model.addAttribute("newAccount", new Account());
        return "admin/staff/delete";
    }

    @PostMapping("admin/staff/delete")
    public String deleteStaff(@ModelAttribute("newAccount") Staff staff1) {
        this.staffService.deleteStaffById(staff1.getMaNV());
        return "redirect:/admin/staff";
    }
}
