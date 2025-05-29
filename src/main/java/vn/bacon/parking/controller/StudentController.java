
package vn.bacon.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import vn.bacon.parking.domain.Student;
import vn.bacon.parking.service.StudentService;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public String listStudents(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getStudentPage(pageable);
        model.addAttribute("studentPage", studentPage);
        return "admin/student/show";
    }

    @GetMapping("/student/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "admin/student/create";
    }

    @PostMapping("/student/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // Check for validation errors
        if (result.hasErrors()) {
            return "admin/student/create";
        }

        // Check for duplicate SDT
        if (studentService.existsBySdt(student.getSdt())) {
            result.rejectValue("sdt", "error.student", "Số điện thoại đã tồn tại!");
            return "admin/student/create";
        }

        // Check for duplicate Email
        if (studentService.existsByEmail(student.getEmail())) {
            result.rejectValue("email", "error.student", "Email đã tồn tại!");
            return "admin/student/create";
        }

        try {
            // Save student
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Thêm sinh viên " + student.getMaSV() + " thành công");
            return "redirect:/admin/student";
        } catch (DataIntegrityViolationException e) {
            // Fallback for unexpected constraint violations
            String message = e.getMostSpecificCause().getMessage();
            if (message.contains("SDT")) {
                result.rejectValue("sdt", "error.student", "Số điện thoại đã tồn tại!");
            } else if (message.contains("Email")) {
                result.rejectValue("email", "error.student", "Email đã tồn tại!");
            } else {
                result.rejectValue("maSV", "error.student", "Lỗi cơ sở dữ liệu: " + message);
            }
            return "admin/student/create";
        }
    }

    @GetMapping("/student/{maSV}")
    public String viewStudent(@PathVariable String maSV, Model model) {
        Optional<Student> student = studentService.getStudentById(maSV);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "admin/student/view";
        }
        return "redirect:/admin/student";
    }

    @GetMapping("/student/update/{maSV}")
    public String showUpdateForm(@PathVariable String maSV, Model model) {
        Optional<Student> student = studentService.getStudentById(maSV);
        if (student.isPresent()) {
            Student s = student.get();
            System.out.println("Loading student with maSV: " + s.getMaSV() + ", ngaySinh: " + s.getNgaySinh());
            model.addAttribute("student", s);
            return "admin/student/update";
        }
        return "redirect:/admin/student";
    }

    @PostMapping("/student/update")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // Check for validation errors
        if (result.hasErrors()) {
            return "admin/student/update";
        }

        // Check for duplicate SDT (excluding current student)
        if (studentService.existsBySdtAndNotMaSV(student.getSdt(), student.getMaSV())) {
            result.rejectValue("sdt", "error.student", "Số điện thoại đã tồn tại!");
            return "admin/student/update";
        }

        // Check for duplicate Email (excluding current student)
        if (studentService.existsByEmailAndNotMaSV(student.getEmail(), student.getMaSV())) {
            result.rejectValue("email", "error.student", "Email đã tồn tại!");
            return "admin/student/update";
        }

        try {
            // Save updated student
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Cập nhật sinh viên " + student.getMaSV() + " thành công");
            return "redirect:/admin/student";
        } catch (DataIntegrityViolationException e) {
            // Fallback for unexpected constraint violations
            String message = e.getMostSpecificCause().getMessage();
            if (message.contains("SDT")) {
                result.rejectValue("sdt", "error.student", "Số điện thoại đã tồn tại!");
            } else if (message.contains("Email")) {
                result.rejectValue("email", "error.student", "Email đã tồn tại!");
            } else {
                result.rejectValue("maSV", "error.student", "Lỗi cơ sở dữ liệu: " + message);
            }
            return "admin/student/update";
        }
    }

    @GetMapping("/student/delete/confirm/{maSV}")
    public String showDeleteConfirm(@PathVariable String maSV, Model model) {
        Optional<Student> student = studentService.getStudentById(maSV);
        if (student.isPresent()) {
            model.addAttribute("maSV", maSV);
            return "admin/student/delete";
        }
        return "redirect:/admin/student";
    }

    @GetMapping("/student/delete/{maSV}")
    public String deleteStudent(@PathVariable String maSV, RedirectAttributes redirectAttributes) {
        if (studentService.getStudentById(maSV).isPresent()) {
            studentService.deleteStudentById(maSV);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sinh viên " + maSV + " thành công");
        }
        return "redirect:/admin/student";
    }
}
