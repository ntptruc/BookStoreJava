package DTO;

public class ConNguoi {
    private String ten;
    private int namSinh;
    private String gioiTinh;

    public ConNguoi() {

    }

    public ConNguoi(String ten, int namSinh, String gioiTinh) {
        this.ten = ten;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;

    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamSinh() {
        return this.namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "ConNguoi{" + "ten=" + ten + ", namSinh=" + namSinh + ", gioiTinh=" + gioiTinh + '}';
    }

    
}
