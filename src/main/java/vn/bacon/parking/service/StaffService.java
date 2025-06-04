package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.bacon.parking.domain.Staff;
import vn.bacon.parking.repository.StaffRepository;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final UploadService uploadService;

    public StaffService(StaffRepository staffRepository, UploadService uploadService) {
        this.uploadService = uploadService;
        this.staffRepository = staffRepository;
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
        // Sắp xếp theo maNV giảm dần
        Pageable sortedByMaNVDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("maNV").descending());
        return staffRepository.findAll(sortedByMaNVDesc);
    }

    public Staff saveStaff(Staff staff) {
        return this.staffRepository.save(staff);
    }

    public boolean existsBySdt(String sdt) {
        return staffRepository.existsBySdt(sdt);
    }

    public boolean existsByEmail(String email) {
        return email != null && !email.isEmpty() && staffRepository.existsByEmail(email);
    }

    public boolean existsByCccd(String cccd) {
        return cccd != null && !cccd.isEmpty() && staffRepository.existsByCccd(cccd);
    }

    public boolean existsBySdtAndNotMaNV(String sdt, String maNV) {
        return staffRepository.existsBySdtAndMaNVNot(sdt, maNV);
    }

    public boolean existsByEmailAndNotMaNV(String email, String maNV) {
        return email != null && !email.isEmpty() && staffRepository.existsByEmailAndMaNVNot(email, maNV);
    }

    public boolean existsByCccdAndNotMaNV(String cccd, String maNV) {
        return cccd != null && !cccd.isEmpty() && staffRepository.existsByCccdAndMaNVNot(cccd, maNV);
    }

    public String handleAvatarUpload(MultipartFile file, String targetFolder) {
        return uploadService.handleSaveUploadFile(file, targetFolder);
    }
}
