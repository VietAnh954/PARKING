package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.bacon.parking.domain.Staff;
import vn.bacon.parking.repository.StaffRepository;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff saveStaff(Staff staff) {
        return this.staffRepository.save(staff);
    }

    public List<Staff> getAllStaffs() {
        return this.staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(String maNV) {
        return this.staffRepository.findById(maNV);
    }

    public void deleteStaffById(String maNV) {
        this.staffRepository.deleteById(maNV);
    }

    public Page<Staff> getStaffPage(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }
}
