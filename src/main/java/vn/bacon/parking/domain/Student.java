package vn.bacon.parking.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SinhVien")

public class Student {
    @Id
    @Column(name = "MaSV", length = 10, columnDefinition = "nchar(10)")
    private String maSV;

    @Column(name = "HoTen", nullable = false, length = 40, columnDefinition = "nvarchar(40)")
    private String hoTen;

    @Column(name = "DiaChi", nullable = false, length = 100, columnDefinition = "nvarchar(100)")
    private String diaChi;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "QueQuan")
    private String queQuan;

    @Column(name = "SDT", nullable = false, length = 15, unique = true, columnDefinition = "nvarchar(15)")
    private String sdt;

    @Column(name = "Email", length = 100, unique = true, columnDefinition = "nvarchar(100)")
    private String email;

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}