package BLL;

import java.util.ArrayList;
import DAL.KhachHangDAL;
import DTO.KhachHangDTO;

public class KhachHangBLL {
    KhachHangDAL khachHangDAL = new KhachHangDAL();

    public KhachHangBLL() {

    }
    
    public KhachHangDTO getCustomerByPhone(String soDienThoai) {
        ArrayList<KhachHangDTO> list = this.getAll();
        for (KhachHangDTO kh : list) {
            if (soDienThoai.equals(kh.getSoDienThoai())) {
                return kh;
            }
        }
        
        return null;
    }

    public int insert(KhachHangDTO kh) {
        return khachHangDAL.insert(kh.getTen(), kh.getSoDienThoai(), kh.getGioiTinh(), kh.getNamSinh());
    }

    public boolean update(KhachHangDTO kh) {
        return khachHangDAL.update(kh);
    }

    public boolean delete(int id) {
        return khachHangDAL.delete(id);
    }

    public ArrayList<KhachHangDTO> getAll() {
        return khachHangDAL.getAll();
    }

    public KhachHangDTO getByKHid(int id) {
        return khachHangDAL.getById(id);
    }
    
    public ArrayList<KhachHangDTO> getByCondition(String condition){
        return khachHangDAL.getByCondition(condition);
    }
}
