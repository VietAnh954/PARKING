package vn.bacon.parking.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SinhVien")
public class Student {
    @Id
    @Column(name = "MaSV", length = 20, columnDefinition = "nchar(20)")
    private String maSV;

    @Column(name = "HoTen", nullable = false, length = 80, columnDefinition = "nvarchar(80)")
    private String hoTen;

    @Column(name = "DiaChi", nullable = false, length = 200, columnDefinition = "nvarchar(200)")
    private String diaChi;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "QueQuan", columnDefinition = "text")
    private String queQuan;

    @Column(name = "SDT", nullable = false, length = 30, unique = true, columnDefinition = "nvarchar(30)")
    private String sdt;

    @Column(name = "Email", length = 200, unique = true, columnDefinition = "nvarchar(200)")
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

    @Override
    public String toString() {
        return "Student [maSV=" + maSV + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh
                + ", queQuan=" + queQuan + ", sdt=" + sdt + ", email=" + email + "]";
    }
}