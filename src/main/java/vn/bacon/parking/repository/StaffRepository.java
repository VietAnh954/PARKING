package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

}
