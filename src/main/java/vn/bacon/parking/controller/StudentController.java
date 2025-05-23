
package vn.bacon.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String createStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm sinh viên " + student.getMaSV() + " thành công");
        return "redirect:/admin/student";
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
            model.addAttribute("student", student.get());
            return "admin/student/update";
        }
        return "redirect:/admin/student";
    }

    @PostMapping("/student/update")
    public String updateStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage",
                "Cập nhật sinh viên " + student.getMaSV() + " thành công");
        return "redirect:/admin/student";
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
