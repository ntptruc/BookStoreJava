/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author Admin
 */


import DAL.TheLoaiDAL;
import DTO.TheLoaiDTO;
import java.util.ArrayList;

public class TheLoaiBLL {
    private TheLoaiDAL TheLoaiDAL = new TheLoaiDAL();
    private ArrayList<TheLoaiDTO> list_TL = new ArrayList<>();
    
    public TheLoaiBLL() {
        
    }
    
    public ArrayList<TheLoaiDTO> getAllSach(){ 
        return TheLoaiDAL.getAll();
    }
    
    public TheLoaiDTO getById(int id){ 
        return TheLoaiDAL.getById(id);
    }
    
    public ArrayList<TheLoaiDTO> getByCondition(String condition, String value){ 
        return TheLoaiDAL.getByCondition(condition + " LIKE '%" + value + "%'");
    }
 
    public boolean update(TheLoaiDTO s) {
        return TheLoaiDAL.update(s);
    }
    
    public int insert(TheLoaiDTO s) {
        return TheLoaiDAL.insert(s.getTenTL());
    }
    
    public boolean delete(int maTL) {
        return TheLoaiDAL.delete(maTL);
    }    
    

}
