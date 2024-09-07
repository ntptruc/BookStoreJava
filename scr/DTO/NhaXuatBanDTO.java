package DTO;

import java.util.Scanner;

public class NhaXuatBanDTO {
    private int maNhaXuatBan;
    private String tenNhaXuatBan;
    private String diaChi;
    private String soDienThoai;

    public NhaXuatBanDTO(int maNhaXuatBan, String tenNhaXuatBan, String diaChi, String soDienThoai) {
        this.maNhaXuatBan = maNhaXuatBan;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public NhaXuatBanDTO() {
        this.maNhaXuatBan = 0;
        this.tenNhaXuatBan = "";
        this.soDienThoai = "";
        this.diaChi = "";
    }

    public int getMaNhaXuatBan() {
        return this.maNhaXuatBan;
    }

    public String getTenNhaXuatBan() {
        return this.tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
