package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.bacon.parking.domain.Student;
import vn.bacon.parking.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Optional<Student> getStudentById(String maSV) {
        return this.studentRepository.findById(maSV);
    }

    public void deleteStudentById(String maSV) {
        this.studentRepository.deleteById(maSV);
    }

    public Page<Student> getStudentPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // Check if phone number exists
    public boolean existsBySdt(String sdt) {
        return studentRepository.existsBySdt(sdt);
    }

    // Check if email exists
    public boolean existsByEmail(String email) {
        return email != null && !email.isEmpty() && studentRepository.existsByEmail(email);
    }

    // Check if phone number exists for another student (used in update)
    public boolean existsBySdtAndNotMaSV(String sdt, String maSV) {
        return studentRepository.existsBySdtAndMaSVNot(sdt, maSV);
    }

    // Check if email exists for another student (used in update)
    public boolean existsByEmailAndNotMaSV(String email, String maSV) {
        return email != null && !email.isEmpty() && studentRepository.existsByEmailAndMaSVNot(email, maSV);
    }
}
