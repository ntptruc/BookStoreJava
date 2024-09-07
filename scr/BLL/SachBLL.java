/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.SachDAL;
import DTO.SachDTO;
import java.util.ArrayList;

/**
 *
 * @author Hung
 */
public class SachBLL {
    private SachDAL sachDAL = new SachDAL();
    
    public SachBLL() {
        
    }
    
    public ArrayList<SachDTO> getAllSach(){ 
        return sachDAL.getAll();
    }
    
    public SachDTO getById(int id){ 
        return sachDAL.getById(id);
    }
    
    public ArrayList<SachDTO> getByCondition(String condition){ 
        return sachDAL.getByCondition(condition);
    }
    
    public boolean update(SachDTO s) {
        return sachDAL.update(s);
    }
    
    public int insert(SachDTO s) {
        return sachDAL.insert(s.getTenSach(), s.getMaTheLoai(), s.getMaTacGia(), s.getMaNhaXuatBan(), s.getSoLuongConLai(), s.getGiaBan(), s.getGiaNhap(), s.getNamXuatBan());
    }
    
    public boolean delete(int maSach) {
        return sachDAL.delete(maSach);
    }
}
