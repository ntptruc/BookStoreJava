
package DTO;

public class NhaCungCapDTO {
    private int maNhaCungCap ;
    private String tenNhaCungCap , diaChi ,soDienThoai ;

    public NhaCungCapDTO(int maNhaCungCap, String tenNhaCungCap, String diaChi, String soDienThoai) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }
    public NhaCungCapDTO() {
        this.maNhaCungCap = 0;
        this.tenNhaCungCap = "";
        this.diaChi = "";
        this.soDienThoai = "";
    }
    
    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    
}
