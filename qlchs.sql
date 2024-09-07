-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 25, 2023 lúc 07:09 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlchs`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieuban`
--

CREATE TABLE `chitietphieuban` (
  `maPhieuBan` int(11) NOT NULL,
  `maSach` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `donGia` bigint(20) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maPhieuNhap` int(11) NOT NULL,
  `maSach` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `donGia` bigint(20) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `maKhachHang` int(11) NOT NULL,
  `tenKhachHang` varchar(512) NOT NULL,
  `soDienThoai` varchar(11) NOT NULL,
  `gioiTinh` varchar(10) NOT NULL,
  `namSinh` int(11) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`maKhachHang`, `tenKhachHang`, `soDienThoai`, `gioiTinh`, `namSinh`, `hienThi`) VALUES
(1, 'Tân', '0811111111', 'Nam', 2000, 1),
(2, 'Lan', '0822222222', 'Nữ', 2001, 1),
(3, 'Hiếu', '0833333333', 'Nam', 2003, 1),
(4, 'Vy', '0844444444', 'Nữ', 1999, 1),
(5, 'Trúc', '0855555555', 'Nam', 1998, 1),
(6, 'Phong', '0866666666', 'Nam', 2005, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `maKhuyenMai` int(11) NOT NULL,
  `tenKhuyenMai` varchar(256) DEFAULT NULL,
  `phanTram` int(11) DEFAULT NULL,
  `ngayBatDau` date DEFAULT NULL,
  `ngayKetThuc` date DEFAULT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`maKhuyenMai`, `tenKhuyenMai`, `phanTram`, `ngayBatDau`, `ngayKetThuc`, `hienThi`) VALUES
(1, 'Ưu đãi 1', 10, '2023-01-01', '2023-01-01', 1),
(2, 'Ưu đãi 2', 20, '2023-04-01', '2023-04-01', 1),
(3, 'Ưu đãi 3', 30, '2023-04-01', '2023-04-01', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `maNhaCungCap` int(11) NOT NULL,
  `tenNhaCungCap` varchar(256) DEFAULT NULL,
  `diaChi` varchar(256) DEFAULT NULL,
  `soDienThoai` varchar(256) DEFAULT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`maNhaCungCap`, `tenNhaCungCap`, `diaChi`, `soDienThoai`, `hienThi`) VALUES
(1, 'ACB', '25 An Dương Vương', '0900000000', 1),
(2, 'Ánh Kim', '12 Hoàng Sa', '0911111111', 1),
(3, 'Văn Lang', '31 Võ Thị Sáu', '0922222222', 1),
(4, 'Phú Mỹ', '21 Lê Thị Hà', '0933333333', 1),
(5, 'Nhật Hạ', '11 Điện Biên Phủ', '0944444444', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `maNhanVien` int(11) NOT NULL,
  `tenNhanVien` varchar(512) NOT NULL,
  `namSinh` int(11) NOT NULL,
  `gioiTinh` varchar(10) NOT NULL,
  `soDienThoai` varchar(11) NOT NULL,
  `luong` bigint(20) NOT NULL,
  `soNgayNghi` int(11) NOT NULL,
  `vaiTro` varchar(256) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`maNhanVien`, `tenNhanVien`, `namSinh`, `gioiTinh`, `soDienThoai`, `luong`, `soNgayNghi`, `vaiTro`, `hienThi`) VALUES
(1, 'Hưng', 2003, 'Nam', '0911111111', 6000000, 3, 'Quản lý', 1),
(2, 'Nhân', 1999, 'Nam', '0922222222', 4000000, 1, 'Nhân viên bán hàng', 1),
(3, 'Đào', 2002, 'Nữ', '0933333333', 3500000, 0, 'Nhân viên nhập hàng', 1),
(4, 'Duy', 1999, 'Nam', '0944444444', 3700000, 2, 'Nhân viên bán hàng', 1),
(5, 'Vy', 2004, 'Nữ', '0955555555', 3500000, 1, 'Nhân viên nhập hàng', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `maNhaXuatBan` int(11) NOT NULL,
  `tenNhaXuatBan` varchar(512) NOT NULL,
  `diaChi` varchar(512) NOT NULL,
  `soDienThoai` varchar(11) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`maNhaXuatBan`, `tenNhaXuatBan`, `diaChi`, `soDienThoai`, `hienThi`) VALUES
(1, 'Kim Đồng', '10 Hồng Bàng', '0800000000', 1),
(2, 'Ánh Sao', '11 Xuân Diệu', '0811111111', 1),
(3, 'Hoài Nam', '12 CMT8', '0822222222', 1),
(4, 'Quang Dương', '13 Trường Chinh', '0833333333', 1),
(5, 'Kim Thành', '14 Cộng Hòa', '0844444444', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuban`
--

CREATE TABLE `phieuban` (
  `maPhieuBan` int(11) NOT NULL,
  `maKhachHang` int(11) DEFAULT NULL,
  `maNhanVien` int(11) DEFAULT NULL,
  `ngayLap` date NOT NULL,
  `tongTien` double DEFAULT NULL,
  `maKhuyenMai` int(11) DEFAULT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maPhieuNhap` int(11) NOT NULL,
  `maNhanVien` int(11) NOT NULL,
  `maNhaCungCap` int(11) DEFAULT NULL,
  `ngayLap` datetime NOT NULL,
  `tongTien` double NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `maSach` int(11) NOT NULL,
  `tenSach` varchar(512) NOT NULL,
  `maTheLoai` int(11) DEFAULT NULL,
  `maTacGia` int(11) DEFAULT NULL,
  `maNhaXuatBan` int(11) DEFAULT NULL,
  `soLuongConLai` int(11) NOT NULL,
  `giaBan` bigint(20) NOT NULL,
  `giaNhap` bigint(20) NOT NULL,
  `namXuatBan` int(11) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`maSach`, `tenSach`, `maTheLoai`, `maTacGia`, `maNhaXuatBan`, `soLuongConLai`, `giaBan`, `giaNhap`, `namXuatBan`, `hienThi`) VALUES
(1, 'Conan', 4, 1, 1, 1000, 20000, 10000, 2022, 1),
(2, 'Những giấc mơ', 6, 3, 3, 3000, 45000, 42000, 1996, 1),
(3, 'Gió thu', 8, 2, 4, 400, 19000, 10000, 1979, 1),
(4, 'Dậu', 7, 5, 3, 7000, 50000, 31000, 1989, 1),
(5, 'Sớm mai', 7, 4, 2, 3000, 60000, 36000, 1988, 1),
(6, 'Mẹ và bé', 8, 1, 4, 600, 15000, 8000, 2012, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tacgia`
--

CREATE TABLE `tacgia` (
  `maTacGia` int(11) NOT NULL,
  `tenTacGia` varchar(512) NOT NULL,
  `gioiTinh` varchar(10) NOT NULL,
  `namSinh` int(11) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tacgia`
--

INSERT INTO `tacgia` (`maTacGia`, `tenTacGia`, `gioiTinh`, `namSinh`, `hienThi`) VALUES
(1, 'Gosho Aoyama', 'Nam', 1963, 1),
(2, 'Nguyễn Nhật', 'Nam', 1965, 1),
(3, 'Xuân Anh', 'Nữ', 1986, 1),
(4, 'Phi Vũ', 'Nữ', 1977, 1),
(5, 'Đông Phương', 'Nữ', 1996, 1),
(6, 'Tony', 'Nam', 1990, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `maNhanVien` int(11) NOT NULL,
  `tenDangNhap` varchar(45) NOT NULL,
  `matKhau` varchar(20) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`maNhanVien`, `tenDangNhap`, `matKhau`, `hienThi`) VALUES
(3, 'dao', '321', 1),
(4, 'duy', '123', 1),
(1, 'hung', '123', 1),
(2, 'nhan', '123', 1),
(5, 'vy', '321', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `maTheLoai` int(11) NOT NULL,
  `tenTheLoai` varchar(512) NOT NULL,
  `hienThi` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`maTheLoai`, `tenTheLoai`, `hienThi`) VALUES
(4, 'Truyện tranh', 1),
(5, 'Tiểu thuyết', 1),
(6, 'Khoa học', 1),
(7, 'Tâm lý', 1),
(8, 'Thiếu nhi', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietphieuban`
--
ALTER TABLE `chitietphieuban`
  ADD PRIMARY KEY (`maPhieuBan`,`maSach`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`maPhieuNhap`,`maSach`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`maKhachHang`,`soDienThoai`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`maKhuyenMai`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`maNhaCungCap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`maNhanVien`);

--
-- Chỉ mục cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`maNhaXuatBan`);

--
-- Chỉ mục cho bảng `phieuban`
--
ALTER TABLE `phieuban`
  ADD PRIMARY KEY (`maPhieuBan`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maPhieuNhap`),
  ADD KEY `maNhanVien_idx` (`maNhanVien`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`maSach`),
  ADD KEY `maTheLoai_idx` (`maTheLoai`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`maTacGia`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`tenDangNhap`),
  ADD KEY `maNhanVien_taikhoan_idx` (`maNhanVien`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`maTheLoai`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietphieuban`
--
ALTER TABLE `chitietphieuban`
  MODIFY `maPhieuBan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  MODIFY `maPhieuNhap` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `maKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `maKhuyenMai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `maNhaCungCap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `maNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  MODIFY `maNhaXuatBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `phieuban`
--
ALTER TABLE `phieuban`
  MODIFY `maPhieuBan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maPhieuNhap` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `maSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  MODIFY `maTacGia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `theloai`
--
ALTER TABLE `theloai`
  MODIFY `maTheLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `pk_sach_tl` FOREIGN KEY (`maTheLoai`) REFERENCES `theloai` (`maTheLoai`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
