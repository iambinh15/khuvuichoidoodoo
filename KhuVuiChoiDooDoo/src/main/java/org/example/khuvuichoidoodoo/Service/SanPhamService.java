package org.example.khuvuichoidoodoo.Service;


import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.example.khuvuichoidoodoo.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SanPhamService {

    @Autowired
    private org.example.khuvuichoidoodoo.Repository.SanPhamRepository sanPhamRepository;

    public SanPham themSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }
}