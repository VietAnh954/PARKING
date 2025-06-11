package vn.bacon.parking.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "YeuCauDangKyThang")
public class MonthlyRegistrationRequest {
    @Id
    @Column(name = "MaYeuCau", length = 10, columnDefinition = "nchar(10)")
    private String maYeuCau;

    @ManyToOne
    @JoinColumn(name = "MaSV", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "BienSoXe", nullable = false)
    private Vehicle vehicle;

    @Column(name = "NgayGuiYeuCau", nullable = false)
    private LocalDate ngayGuiYeuCau;

    @Column(name = "NgayBatDau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "NgayHetHan", nullable = false)
    private LocalDate ngayHetHan;

    @Column(name = "TrangThai", nullable = false, length = 20)
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "NVXuLy", nullable = true)
    private Staff nhanVienXuLy;

    @Column(name = "GhiChu", nullable = true, length = 255)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "MaBangGia", nullable = true)
    private Price bangGia;

    // Getters and Setters
    public String getMaYeuCau() {
        return maYeuCau;
    }

    public void setMaYeuCau(String maYeuCau) {
        this.maYeuCau = maYeuCau;
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

    public LocalDate getNgayGuiYeuCau() {
        return ngayGuiYeuCau;
    }

    public void setNgayGuiYeuCau(LocalDate ngayGuiYeuCau) {
        this.ngayGuiYeuCau = ngayGuiYeuCau;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Staff getNhanVienXuLy() {
        return nhanVienXuLy;
    }

    public void setNhanVienXuLy(Staff nhanVienXuLy) {
        this.nhanVienXuLy = nhanVienXuLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Price getBangGia() {
        return bangGia;
    }

    public void setBangGia(Price bangGia) {
        this.bangGia = bangGia;
    }

    public Integer getGia() {
        return bangGia != null ? bangGia.getGia() : null;
    }

    // Date conversion methods for JSP
    public Date getNgayGuiYeuCauAsDate() {
        return ngayGuiYeuCau != null ? Date.from(ngayGuiYeuCau.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    public Date getNgayBatDauAsDate() {
        return ngayBatDau != null ? Date.from(ngayBatDau.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    public Date getNgayHetHanAsDate() {
        return ngayHetHan != null ? Date.from(ngayHetHan.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }
}