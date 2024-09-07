/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Hung
 */
public class ChiTietPhieuNhapDTO extends ChiTietPhieu {
    private int maPhieuNhap;

    public ChiTietPhieuNhapDTO(int maPhieuNhap, int maSach, int soLuong, long donGia) {
        super(maSach, soLuong, donGia);
        this.maPhieuNhap = maPhieuNhap;
    }

    public ChiTietPhieuNhapDTO() {
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }
}
