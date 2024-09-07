/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DTO.NhaXuatBanDTO;
import DAL.NhaXuatBanDAL;
import java.util.ArrayList;

        
        

/**
 *
 * @author Admin
 */
public class NhaXuatBanBLL {
     NhaXuatBanDAL nhaXuatBanDAL = new NhaXuatBanDAL();

    public NhaXuatBanBLL() {

    }

    public int insert(NhaXuatBanDTO nxb) {
        return nhaXuatBanDAL.insert(nxb.getTenNhaXuatBan(), nxb.getDiaChi(), nxb.getSoDienThoai());
    }

    public boolean update(NhaXuatBanDTO nxb) {
        return nhaXuatBanDAL.update(nxb);
    }

    public boolean delete(int manxb) {
        return nhaXuatBanDAL.delete(manxb);
    }

    public ArrayList<NhaXuatBanDTO> getAll() {
        return nhaXuatBanDAL.getAll();
    }

    public NhaXuatBanDTO getByKHid(int manxb) {
        return nhaXuatBanDAL.getById(manxb);
    }
    
    public ArrayList<NhaXuatBanDTO> getByCondition(String condition){
        return nhaXuatBanDAL.getByCondition(condition);
    }
}
