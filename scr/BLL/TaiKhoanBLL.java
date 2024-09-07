/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author Admin
 */


import DAL.TaiKhoanDAL;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;

public class TaiKhoanBLL {
    private TaiKhoanDAL TaiKhoanDAL = new TaiKhoanDAL();
    
    public TaiKhoanBLL() {
        
    }
    
    public ArrayList<TaiKhoanDTO> getAllSach(){ 
        return TaiKhoanDAL.getAll();
    }
    
    public TaiKhoanDTO getById(int id){ 
        return TaiKhoanDAL.getById(id);
    }
    
    public TaiKhoanDTO getTaiKhoan(String tentk) {
        for (TaiKhoanDTO tk : this.getAllSach()) {
            if (tk.getTenDangNhap().equals(tentk)) {
                return tk;
            }
        }
        return null;
    }
    
    public ArrayList<TaiKhoanDTO> getByCondition(String condition, String value){ 
        return TaiKhoanDAL.getByCondition(condition + " LIKE '%" + value + "%'");
    }
    
    public boolean update(TaiKhoanDTO s) {
        return TaiKhoanDAL.update(s);
    }
    
    public boolean insert(TaiKhoanDTO s) {
        return TaiKhoanDAL.insert(s.getMaNhanVien(), s.getTenDangNhap(), s.getMatKhau());
    }
    
    public boolean delete(int manv) {
        return TaiKhoanDAL.delete(manv);
    }
}
