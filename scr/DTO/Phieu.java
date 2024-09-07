package DTO;

import java.sql.Date;


public abstract class Phieu {
    private Date ngayLap;
    private int maNhanVien;
    private double tongTien;

    public Phieu(int maNhanVien, Date ngayLap, double tongTien) {
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public Phieu() {
        this.ngayLap = null;
        this.maNhanVien = 0;
        this.tongTien = 0;
    }

    public Date getNgayLap() {
        return this.ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
