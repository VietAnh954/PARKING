package vn.bacon.parking.controller;

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

import vn.bacon.parking.domain.Student;
import vn.bacon.parking.service.StudentService;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // @GetMapping("/admin/student")
    // public String getStudentPage(Model model) {
    // List<Student> students = this.studentService.getAllStudents();
    // model.addAttribute("studentshow", students);
    // return "admin/student/show";
    // }

    @GetMapping("admin/student/create")
    public String getCreateStudentPage(Model model) {
        model.addAttribute("newStudent", new Student());
        return "admin/student/create";
    }

    @PostMapping("admin/student/create")
    public String createStudent(@ModelAttribute("newStudent") Student student1) {
        this.studentService.saveStudent(student1);
        return "redirect:/admin/student";
    }

    // Update student
    @GetMapping("admin/student/update/{maSV}")
    public String getUpdateStudentPage(Model model, @PathVariable String maSV) {
        Optional<Student> currentStudent = this.studentService.getStudentById(maSV);
        model.addAttribute("newStudent", currentStudent);
        return "admin/student/update";
    }

    @PostMapping("admin/student/update")
    public String updateStudent(Model model, @ModelAttribute("newStudent") Student student1) {
        Student currentStudent = this.studentService.getStudentById(student1.getMaSV()).get();
        if (currentStudent != null) {
            currentStudent.setMaSV(student1.getMaSV());
            currentStudent.setHoTen(student1.getHoTen());
            currentStudent.setSdt(student1.getSdt());
            currentStudent.setEmail(student1.getEmail());
            currentStudent.setDiaChi(student1.getDiaChi());
            currentStudent.setNgaySinh(student1.getNgaySinh());
            currentStudent.setQueQuan(student1.getQueQuan());
            this.studentService.saveStudent(currentStudent);
        }
        return "redirect:/admin/student";
    }

    // Delete student
    @GetMapping("admin/student/delete/{maSV}")
    public String getDeleteStudentPage(Model model, @PathVariable String maSV) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("maSV", maSV);

        return "admin/student/delete";
    }

    @PostMapping("admin/student/delete")
    public String deleteStudent(@ModelAttribute("newStudent") Student student1) {
        this.studentService.deleteStudentById(student1.getMaSV());
        return "redirect:/admin/student";
    }

    @GetMapping("/admin/student")
    public String listStudent(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getStudentPage(pageable);
        model.addAttribute("studentPage", studentPage);
        model.addAttribute("studentList", studentPage.getContent());
        return "admin/student/show";
    }
}
