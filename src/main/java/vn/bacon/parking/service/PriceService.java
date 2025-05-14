package vn.bacon.parking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.bacon.parking.domain.Price;
import vn.bacon.parking.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // Lấy danh sách tất cả Bảng Giá
    public List<Price> findAll() {
        return this.priceRepository.findAll();
    }

    // Tìm Bảng Giá theo mã
    public Optional<Price> findById(String maBangGia) {
        return this.priceRepository.findById(maBangGia);
    }

    // Thêm hoặc cập nhật Bảng Giá
    public Price save(Price bangGia) {
        return this.priceRepository.save(bangGia);
    }

    // Xóa Bảng Giá theo mã
    public void deleteById(String maBangGia) {
        this.priceRepository.deleteById(maBangGia);
    }

    // Lấy danh sách Bảng Giá có phân trang
    public Page<Price> getBangGiaPage(Pageable pageable) {
        return this.priceRepository.findAll(pageable);
    }
}
