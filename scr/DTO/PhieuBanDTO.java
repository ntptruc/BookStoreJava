package DTO;

import java.sql.Date;

public class PhieuBanDTO extends Phieu {
    private int maPhieuBan;
    private int maKhachHang;
    private int maKhuyenMai;
    
    public PhieuBanDTO(int maPhieuBan, int maKhachHang, int nhanVien, Date ngayLap, double tongTien, int maKhuyenMai) {
        super(nhanVien, ngayLap, tongTien);
        this.maPhieuBan = maPhieuBan;
        this.maKhachHang = maKhachHang;
        this.maKhuyenMai = maKhuyenMai;
    }

    public PhieuBanDTO() {
        super();
        this.maPhieuBan = 0;
        this.maKhachHang = 0;
    }
    
    public int getMaPhieuBan() {
        return this.maPhieuBan;
    }
    
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }
    
    
}
