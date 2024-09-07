package DTO;

public class NhanVienDTO extends ConNguoi {
    private int maNhanVien;
    private long luong;
    private String soDienThoai;
    private int soNgayNghi;
    private String vaiTro;

    public NhanVienDTO(int maNhanVien, String ten, int namSinh, String gioiTinh, String soDienThoai, long luong, int soNgayNghi, String vaiTro) {
        super(ten, namSinh, gioiTinh);
        this.maNhanVien = maNhanVien;
        this.luong = luong;
        this.soNgayNghi = soNgayNghi;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
    }

    public NhanVienDTO() {
        super();
        this.maNhanVien = 0;
        this.luong = 0;
        this.soNgayNghi = 0;
        this.vaiTro = "";
    }
    

    public int getMaNhanVien() {
        return this.maNhanVien;
    }
    
    public long getLuong() {
        return this.luong;
    }

    public void setLuong(long luong) {
        if (luong < 0) {
            return;
        }
        this.luong = luong;
    }

    public int getSoNgayNghi() {
        return this.soNgayNghi;
    }

    public void setSoNgayNghi(int soNgayNghi) {
        if (soNgayNghi < 0) {
            return;
        }
        this.soNgayNghi = soNgayNghi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
}
