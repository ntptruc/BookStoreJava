/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.NhanVienDAL;
import DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanVienBLL {
    NhanVienDAL nhanVienDAL = new NhanVienDAL();

    public NhanVienBLL() {
    }

    public int insert(NhanVienDTO nv) {
        return nhanVienDAL.insert(nv.getTen(), nv.getNamSinh(), nv.getGioiTinh(), nv.getSoDienThoai(), nv.getLuong(), nv.getSoNgayNghi(), nv.getVaiTro());
    }

    public boolean update(NhanVienDTO nv) {
        return nhanVienDAL.update(nv);
    }

    public boolean delete(int manv) {
        return nhanVienDAL.delete(manv);
    }

    public ArrayList<NhanVienDTO> getAll() {
        return nhanVienDAL.getAll();
    }

    public NhanVienDTO getByNVid(int manv) {
        return nhanVienDAL.getById(manv);
    }

    public ArrayList<NhanVienDTO> getByCondition(String condition) {
        return nhanVienDAL.getByCondition(condition);
    }
    
    public ArrayList<NhanVienDTO> getEmployeeUnAccount() {
        return nhanVienDAL.getEmployeeUnAccount();
    }
}
