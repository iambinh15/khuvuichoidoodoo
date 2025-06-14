package org.example.khuvuichoidoodoo.Controller;

import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.example.khuvuichoidoodoo.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    // Giao diện chính gồm form + bảng
    @GetMapping("")
    public String hienThiGiaoDien(Model model) {
        model.addAttribute("dsSanPham", sanPhamRepository.findAll());
        model.addAttribute("sanPham", new SanPham()); // Thêm trống để form xử lý cả thêm/sửa
        return "them-san-pham";
    }

    // Xử lý thêm hoặc cập nhật sản phẩm
    @PostMapping("/luu")
    public String luuSanPham(@ModelAttribute SanPham sanPham) {
        sanPhamRepository.save(sanPham); // save sẽ tự động cập nhật nếu có ID
        return "redirect:/san-pham";
    }

    // Xử lý click nút "Sửa"
    @GetMapping("/sua/{id}")
    public String suaSanPham(@PathVariable Long id, Model model) {
        SanPham sp = sanPhamRepository.findById(id).orElse(null);
        model.addAttribute("sanPham", sp); // Gửi sản phẩm đang sửa về form
        model.addAttribute("dsSanPham", sanPhamRepository.findAll()); // Gửi danh sách cho bảng
        return "them-san-pham";
    }

    // Xoá sản phẩm
    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Long id) {
        sanPhamRepository.deleteById(id);
        return "redirect:/san-pham";
    }
}
