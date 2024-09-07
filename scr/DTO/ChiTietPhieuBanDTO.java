/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Scanner;

/**
 *
 * @author Hung
 */
public class ChiTietPhieuBanDTO extends ChiTietPhieu {
    Scanner sc = new Scanner(System.in);
    private int maPhieuBan;

    public ChiTietPhieuBanDTO() {
    }

    public ChiTietPhieuBanDTO(int maPhieuBan, int maSach, int soLuong, long donGia) {
        super(maSach, soLuong, donGia);
        this.maPhieuBan = maPhieuBan;
    }

    public int getMaPhieuBan() {
        return maPhieuBan;
    }

    public void setMaPhieuBan(int maPhieuBan) {
        this.maPhieuBan = maPhieuBan;
    }
}
