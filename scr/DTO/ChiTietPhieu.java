/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Hung
 */
public class ChiTietPhieu {
    private int maSach;
    private int soLuong;
    private long donGia;

    public ChiTietPhieu() {
    }

    public ChiTietPhieu(int maSach, int soLuong, long donGia) {
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }
}
