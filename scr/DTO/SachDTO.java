/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Hung
 */
public class SachDTO {
    private int maSach;
    private String tenSach;
    private int maTacGia;
    private int maTheLoai;
    private int maNhaXuatBan;
    private int namXuatBan;
    private int soLuongConLai;
    private long giaBan;
    private long giaNhap;

    public SachDTO(int maSach, String tenSach, int maTheLoai, int maTacGia,
            int maNhaXuatBan, int soLuongConLai, long giaBan, long giaNhap, int namXuatBan) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.maTheLoai = maTheLoai;
        this.maNhaXuatBan = maNhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.soLuongConLai = soLuongConLai;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
    }

    public SachDTO() {
        this.maSach = 0;
        this.tenSach = "";
        this.maTacGia = 0;
        this.maTheLoai = 0;
        this.maNhaXuatBan = 0;
        this.namXuatBan = 0;
        this.soLuongConLai = 0;
        this.giaBan = 0;
        this.giaNhap = 0;
    }

    public int getMaSach() {
        return this.maSach;
    }

    public String getTenSach() {
        return this.tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public int getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(int maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(long giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Override
    public String toString() {
        return "Sach{" + "maSach=" + maSach + ", tenSach=" + tenSach + ", maTacGia=" + maTacGia + ", maTheLoai=" + maTheLoai + ", maNhaXuatBan=" + maNhaXuatBan + ", namXuatBan=" + namXuatBan + ", soLuongConLai=" + soLuongConLai + ", giaBan=" + giaBan + ", giaNhap=" + giaNhap + '}';
    }

    public int getNamXuatBan() {
        return this.namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }
    
    public int getSoLuongConLai() {
    	return this.soLuongConLai;
    }
    
    public void setSoLuongConLai(int soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }
}
