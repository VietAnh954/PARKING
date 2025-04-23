package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
