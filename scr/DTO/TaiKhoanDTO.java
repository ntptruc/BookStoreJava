package DTO;

public class TaiKhoanDTO {
    private int maNhanVien;
    private String tenDangNhap;
    private String matKhau;

    public TaiKhoanDTO() {
        this.maNhanVien = 0;
        this.tenDangNhap = null;
        this.matKhau = null;
    }


    public TaiKhoanDTO(int maNhanVien, String tenDangNhap, String matKhau) {
        this.maNhanVien = maNhanVien;
        this.matKhau = matKhau;
        this.tenDangNhap = tenDangNhap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenDangNhap(){
        return tenDangNhap;
    }

    public void settenDangNhap(String tenDangNhap){
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
