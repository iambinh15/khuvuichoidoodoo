package org.example.khuvuichoidoodoo.Repository;

import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<org.example.khuvuichoidoodoo.Entity.SanPham, Long> {
}