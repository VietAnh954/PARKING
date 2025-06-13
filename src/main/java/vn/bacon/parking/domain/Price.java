package vn.bacon.parking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "BangGia")
public class Price {
    @Id
    @NotBlank(message = "Mã bảng giá không được để trống")
    @Pattern(regexp = "^BG\\d{3}$", message = "Mã bảng giá phải có định dạng BGxxx (x là số)")
    @Column(name = "MaBangGia", columnDefinition = "nchar(10)")
    private String maBangGia;

    @NotNull(message = "Vui lòng chọn loại xe")
    @ManyToOne
    @JoinColumn(name = "MaLoaiXe")
    private VehicleType maLoaiXe;

    @NotNull(message = "Vui lòng chọn hình thức gửi")
    @ManyToOne
    @JoinColumn(name = "MaHinhThuc")
    private ParkingMode maHinhThuc;

    @NotNull(message = "Giá không được để trống")
    @Column(name = "Gia")
    private Integer gia;

    public String getMaBangGia() {
        return maBangGia;
    }

    public void setMaBangGia(String maBangGia) {
        this.maBangGia = maBangGia;
    }

    public VehicleType getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(VehicleType maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public ParkingMode getMaHinhThuc() {
        return maHinhThuc;
    }

    public void setMaHinhThuc(ParkingMode maHinhThuc) {
        this.maHinhThuc = maHinhThuc;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "Price [maBangGia=" + maBangGia + ", maLoaiXe=" + maLoaiXe + ", maHinhThuc=" + maHinhThuc
                + ", gia=" + gia + "]";
    }

}