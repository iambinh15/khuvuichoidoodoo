package org.example.khuvuichoidoodoo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "san_pham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_san_pham", nullable = false)
    private String tenSanPham;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    @Column(name = "loai_san_pham", nullable = false)
    private String loaiSanPham;  // Có thể dùng Enum nếu thích

    @Column(name = "so_luong_ton", nullable = false)
    private Integer soLuongTon = 0;
}
