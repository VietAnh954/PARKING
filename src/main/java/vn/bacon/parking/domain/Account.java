package vn.bacon.parking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class Account {
    @Id
    @Column(name = "MaTK", length = 10, columnDefinition = "nchar(10)")
    private String maTK;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "LoaiTK", nullable = false, length = 20)
    private String loaiTK;

    @ManyToOne
    @JoinColumn(name = "MaNV")
    private Staff maNV;

    @ManyToOne
    @JoinColumn(name = "MaSV")
    private Student maSV;

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    public Staff getMaNV() {
        return maNV;
    }

    public void setMaNV(Staff maNV) {
        this.maNV = maNV;
    }

    public Student getMaSV() {
        return maSV;
    }

    public void setMaSV(Student maSV) {
        this.maSV = maSV;
    }

}
