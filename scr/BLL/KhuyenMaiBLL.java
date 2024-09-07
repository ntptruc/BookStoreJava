/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.KhuyenMaiDAL;
import DTO.KhuyenMaiDTO;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Hung
 */
public class KhuyenMaiBLL {
    private KhuyenMaiDAL khuyenMaiDAL = new KhuyenMaiDAL();
    
    public KhuyenMaiBLL() {
        
    }
    
    public ArrayList<KhuyenMaiDTO> getAll() {
        return khuyenMaiDAL.getAll();
    }
    
    public ArrayList<KhuyenMaiDTO> getByCondition(String condition) {
        return khuyenMaiDAL.getByCondition(condition);
    }
    
    public ArrayList<KhuyenMaiDTO> getSaleByDate(Date now) {
        return khuyenMaiDAL.getSaleByDate(now);
    }
    
    public int addSale(KhuyenMaiDTO km) {
        return khuyenMaiDAL.insert(km.getTenKhuyenMai(), km.getPhanTram(), km.getNgayBatDau(), km.getNgayKetThuc());
    }
    
    public boolean update(KhuyenMaiDTO km) {
        return khuyenMaiDAL.update(km);
    }
    
    public boolean delete(int id) {
        return khuyenMaiDAL.delete(id);
    }
    
    public KhuyenMaiDTO getById(int id) {
    return khuyenMaiDAL.getById(id);
    }
    
    
}
