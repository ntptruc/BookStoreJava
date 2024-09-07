package DTO;

public class TacGiaDTO extends ConNguoi {
    private int maTacGia;

    public TacGiaDTO(int maTacGia, String ten, String gioiTinh, int namSinh) {
        super(ten, namSinh, gioiTinh);
        this.maTacGia = maTacGia;
    }

    public TacGiaDTO() {
        super();
        this.maTacGia = 0;
    }
    
    public int getMaTacGia() {
        return this.maTacGia;
    }
}