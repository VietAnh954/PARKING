package vn.bacon.parking.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "YeuCauDangKyThang")
public class MonthlyRegistrationRequest {
    @Id
    @Column(name = "MaYeuCau", length = 10, columnDefinition = "nchar(10)")
    private String requestId;

    @ManyToOne
    @JoinColumn(name = "MaSV", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "BienSoXe", nullable = false)
    private Vehicle vehicle;

    @Column(name = "NgayGuiYeuCau", nullable = false)
    private LocalDate requestDate;

    @Column(name = "NgayBatDau", nullable = false)
    private LocalDate startDate;

    @Column(name = "NgayHetHan", nullable = false)
    private LocalDate endDate;

    @Column(name = "TrangThai", nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "NVXuLy", nullable = true)
    private Staff processedBy;

    @Column(name = "GhiChu", nullable = true, length = 255)
    private String note;

    // Getters and Setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Staff getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(Staff processedBy) {
        this.processedBy = processedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}