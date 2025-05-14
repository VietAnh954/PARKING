package vn.bacon.parking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Xe")
public class Vehicle {
    @Id
    @Column(name = "BienSoXe", length = 10, columnDefinition = "nchar(10)")
    private String bienSoXe;

    // nhiều xe có thể thuộc về một loại xe
    @ManyToOne
    @JoinColumn(name = "MaLoaiXe", nullable = false)
    private VehicleType maLoaiXe;

    // nhiều xe có thể thuộc về một nhân viên
    @ManyToOne
    @JoinColumn(name = "MaNV")
    private Staff maNV;

    // nhiều xe có thể thuộc về một sinh viên
    @ManyToOne
    @JoinColumn(name = "MaSV")
    private Student maSV;

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public VehicleType getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(VehicleType maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
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

    @Override
    public String toString() {
        return "Vehicle [bienSoXe=" + bienSoXe + ", maLoaiXe=" + maLoaiXe + ", maNV=" + maNV + ", maSV=" + maSV + "]";
    }

}
