package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

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

}
