package vn.bacon.parking.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "NhanVien")

public class Staff {
    @Id
    @Column(name = "MaNV", length = 10, columnDefinition = "nchar(10)")
    // @Column(name = "MaNV")
    private String maNV;

    @Column(name = "HoTen", nullable = false, length = 50, columnDefinition = "nvarchar(50)")
    // @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "SDT", nullable = false, length = 15, columnDefinition = "nvarchar(15)", unique = true)
    // @Column(name = "SDT")
    private String sdt;

    @Column(name = "Email", nullable = false, length = 100, columnDefinition = "nvarchar(100)", unique = true)
    // @Column(name = "Email")
    private String email;

    @Column(name = "CCCD", nullable = false, length = 20, columnDefinition = "nvarchar(20)", unique = true)
    // @Column(name = "CCCD")
    private String cccd;

    @Column(name = "ChucVu", nullable = false, columnDefinition = "nvarchar(50)", length = 50)
    // @Column(name = "ChucVu")
    private String chucVu;

    @Column(name = "NgayVaoLam", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate ngayVaoLam;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

}