package DTO;

public class KhachHangDTO extends ConNguoi {
    private String soDienThoai;
    private int maKhachHang;
   
    public KhachHangDTO(int maKhachHang, String ten, String gioiTinh, String soDienThoai, int namSinh) {
        super(ten, namSinh, gioiTinh);
        this.soDienThoai = soDienThoai;
        this.maKhachHang = maKhachHang;
    }

    public KhachHangDTO() {
        super();
        this.soDienThoai = "";
        this.maKhachHang = 0;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }
}