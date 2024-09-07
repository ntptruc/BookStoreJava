/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.TacGiaDAL;
import DTO.TacGiaDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TacGiaBLL {
     TacGiaDAL tacGiaDAL = new TacGiaDAL();

    public TacGiaBLL() {

    }

    public int insert(TacGiaDTO tg) {
        return tacGiaDAL.insert(tg.getTen(), tg.getGioiTinh(), tg.getNamSinh());
    }

    public boolean update(TacGiaDTO tg) {
        return tacGiaDAL.update(tg);
    }

    public boolean delete(int matg) {
        return tacGiaDAL.delete(matg);
    }

    public ArrayList<TacGiaDTO> getAll() {
        return tacGiaDAL.getAll();
    }

    public TacGiaDTO getByKHid(int matg) {
        return tacGiaDAL.getById(matg);
    }
    
    public ArrayList<TacGiaDTO> getByCondition(String condition){
        return tacGiaDAL.getByCondition(condition);
    }
}
