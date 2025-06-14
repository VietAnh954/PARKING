package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import vn.bacon.parking.domain.ParkingMode;
import vn.bacon.parking.domain.Price;
import vn.bacon.parking.domain.VehicleType;

import vn.bacon.parking.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // Lấy danh sách tất cả Bảng Giá
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    // Tìm Bảng Giá theo mã
    public Optional<Price> findById(String maBangGia) {
        return this.priceRepository.findById(maBangGia);
    }

    // Thêm hoặc cập nhật Bảng Giá
    public Price savePrice(Price price) {

        return priceRepository.save(price);
    }

    // Xóa Bảng Giá theo mã
    public void deletePrice(String maBangGia) {
        priceRepository.deleteById(maBangGia);
    }

    // Lấy danh sách Bảng Giá có phân trang
    public Page<Price> getBangGiaPage(Pageable pageable) {
        return priceRepository.findAll(pageable);
    }

    public boolean existsById(String maBangGia) {
        return priceRepository.existsById(maBangGia);
    }

    public boolean existsByMaLoaiXeAndMaHinhThuc(VehicleType maLoaiXe, ParkingMode maHinhThuc) {
        return priceRepository.existsByMaLoaiXeAndMaHinhThuc(maLoaiXe, maHinhThuc);
    }

}
