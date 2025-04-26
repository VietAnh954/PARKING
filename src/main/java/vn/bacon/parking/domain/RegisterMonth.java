package vn.bacon.parking.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DangKiThang")
public class RegisterMonth {
    @Id
    @Column(name = "MaDangKy", length = 10, columnDefinition = "nchar(10)")
    private String maDangKy;

    @ManyToOne
    @JoinColumn(name = "BienSoXe", nullable = false)
    private Vehicle bienSoXe;

    @Column(name = "TGianDangKy", nullable = false)
    private LocalDate tGianDangKy;

    @Column(name = "TGianHetHan", nullable = false)
    private LocalDate tGianHetHan;

    @ManyToOne
    @JoinColumn(name = "NVGhiNhan", nullable = false)
    private Staff nvGhiNhan;

    public String getMaDangKy() {
        return maDangKy;
    }

    public void setMaDangKy(String maDangKy) {
        this.maDangKy = maDangKy;
    }

    public Vehicle getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(Vehicle bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public LocalDate gettGianDangKy() {
        return tGianDangKy;
    }

    public void settGianDangKy(LocalDate tGianDangKy) {
        this.tGianDangKy = tGianDangKy;
    }

    public LocalDate gettGianHetHan() {
        return tGianHetHan;
    }

    public void settGianHetHan(LocalDate tGianHetHan) {
        this.tGianHetHan = tGianHetHan;
    }

    public Staff getNvGhiNhan() {
        return nvGhiNhan;
    }

    public void setNvGhiNhan(Staff nvGhiNhan) {
        this.nvGhiNhan = nvGhiNhan;
    }

}
