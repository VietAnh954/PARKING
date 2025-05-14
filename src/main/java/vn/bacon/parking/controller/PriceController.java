package vn.bacon.parking.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.bacon.parking.domain.Price;
import vn.bacon.parking.service.PriceService;

@Controller
@RequestMapping("/admin/price")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    // Hiển thị danh sách Bảng Giá
    @GetMapping
    public String listPrice(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Price> BangGiaPage = priceService.getBangGiaPage(pageable);
        model.addAttribute("bangGiaPage", BangGiaPage);
        model.addAttribute("bangGiaList", BangGiaPage.getContent());
        return "admin/price/show";
    }

    // Hiển thị form Thêm Bảng Giá
    @GetMapping("/create")
    public String getCreatePricePage(Model model) {
        // model.addAttribute("loaiXeList", loaiXeService.findAll());
        // model.addAttribute("hinhThucGuiXeList", hinhThucGuiXeService.findAll());
        model.addAttribute("newBangGia", new Price());
        return "admin/price/create";
    }

    // Xử lý Thêm Bảng Giá
    @PostMapping("/create")
    public String createBangGia(@ModelAttribute("newBangGia") Price BangGia) {
        this.priceService.save(BangGia);
        return "redirect:/admin/price";
    }

    // Hiển thị form Cập Nhật Bảng Giá
    @GetMapping("/update/{maBangGia}")
    public String getUpdatePricePage(Model model, @PathVariable String maBangGia) {
        Optional<Price> currentBangGia = this.priceService.findById(maBangGia);
        model.addAttribute("newBangGia", currentBangGia.get());
        return "admin/price/update";
    }

    // Xử lý Cập Nhật Bảng Giá
    @PostMapping("/update")
    public String updateBangGia(Model model, @ModelAttribute("newBangGia") Price BangGia) {
        Price currentBangGia = this.priceService.findById(BangGia.getMaBangGia()).get();
        if (currentBangGia != null) {
            currentBangGia.setMaBangGia(BangGia.getMaBangGia());
            currentBangGia.setMaLoaiXe(BangGia.getMaLoaiXe());
            currentBangGia.setMaHinhThuc(BangGia.getMaHinhThuc());
            currentBangGia.setGia(BangGia.getGia());

            priceService.save(currentBangGia);
        }
        return "redirect:/admin/price";
    }

    // Hiển thị form Xóa Bảng Giá
    @GetMapping("/delete/{maBangGia}")
    public String getDeletePage(Model model, @PathVariable String maBangGia) {
        model.addAttribute("maBangGia", maBangGia);
        model.addAttribute("newPrice", new Price());
        return "admin/price/delete";
    }

    // Xử lý Xóa Bảng Giá
    @PostMapping("/delete")
    public String deleteBangGia(@ModelAttribute("newPrice") Price BangGia) {
        this.priceService.deleteById(BangGia.getMaBangGia());
        return "redirect:/admin/price";
    }
}
