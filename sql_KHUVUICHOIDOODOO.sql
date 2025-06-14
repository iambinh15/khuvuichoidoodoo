CREATE DATABASE quan_cafe_vuichoi
USE quan_cafe_vuichoi
-- Bảng sản phẩm
CREATE TABLE san_pham (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten_san_pham NVARCHAR(100) NOT NULL,
    don_gia DECIMAL(10,2) NOT NULL,
    loai_san_pham NVARCHAR(50) NOT NULL, -- VD: 'DO_UONG', 'DO_AN', 'VE_VUI_CHOI'
    so_luong_ton INT NOT NULL DEFAULT 0
);

-- Bảng hóa đơn
CREATE TABLE hoa_don (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ngay_lap DATE NOT NULL DEFAULT GETDATE(),
    tong_tien DECIMAL(10,2) DEFAULT 0
);

-- Bảng chi tiết hóa đơn
CREATE TABLE chi_tiet_hoa_don (
    hoa_don_id BIGINT NOT NULL,
    san_pham_id BIGINT NOT NULL,
    so_luong INT NOT NULL,
    thanh_tien DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (hoa_don_id, san_pham_id),
    FOREIGN KEY (hoa_don_id) REFERENCES hoa_don(id) ON DELETE CASCADE,
    FOREIGN KEY (san_pham_id) REFERENCES san_pham(id)
);
-- Thêm sản phẩm (có tồn kho)
INSERT INTO san_pham (ten_san_pham, don_gia, loai_san_pham, so_luong_ton) VALUES
(N'Cà phê sữa', 25000, 'DO_UONG', 100),
(N'Trà đào', 30000, 'DO_UONG', 80),
(N'Bánh mì nướng', 20000, 'DO_AN', 50),
(N'Vé khu vui chơi', 60000, 'VE_VUI_CHOI', 1000);

-- Thêm 1 hóa đơn mới
INSERT INTO hoa_don DEFAULT VALUES;

-- Giả sử hóa đơn vừa tạo có id = 1, thêm chi tiết hóa đơn
INSERT INTO chi_tiet_hoa_don (hoa_don_id, san_pham_id, so_luong, thanh_tien) VALUES
(1, 1, 2, 50000),     -- 2 ly cà phê sữa
(1, 3, 1, 20000),     -- 1 bánh mì nướng
(1, 4, 3, 180000);    -- 3 vé vui chơi

-- Cập nhật tổng tiền trong hóa đơn
UPDATE hoa_don
SET tong_tien = (
    SELECT SUM(thanh_tien)
    FROM chi_tiet_hoa_don
    WHERE hoa_don_id = hoa_don.id
)
WHERE id = 1;

-- Trừ tồn kho theo hóa đơn
UPDATE sp
SET sp.so_luong_ton = sp.so_luong_ton - cthd.so_luong
FROM san_pham sp
JOIN chi_tiet_hoa_don cthd ON sp.id = cthd.san_pham_id
WHERE cthd.hoa_don_id = 1;
