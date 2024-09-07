package DTO;

import java.sql.Date;

public class PhieuNhapDTO extends Phieu {
    private int maPhieuNhap;
    private int maNhaCungCap;
    
    public PhieuNhapDTO(int maPhieuNhap, int maNhanVien, int maNhaCungCap, Date ngayLap, double tongTien) {
        super(maNhanVien, ngayLap, tongTien);
        this.maPhieuNhap = maPhieuNhap;
        this.maNhaCungCap = maNhaCungCap;
    }

    public PhieuNhapDTO() {
        super();
        this.maPhieuNhap = 0;
        this.maNhaCungCap = 0;
    }

    public int getMaPhieuNhap() {
        return this.maPhieuNhap;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
    
    
}