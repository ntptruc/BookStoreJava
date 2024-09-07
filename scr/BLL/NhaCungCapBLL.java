
package BLL;

import DAL.NhaCungCapDAL;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

public class NhaCungCapBLL {
    
    private NhaCungCapDAL nhaCungCapDAL = new NhaCungCapDAL();
    
    public NhaCungCapBLL(){
    }
    
    public ArrayList<NhaCungCapDTO> getAll(){ 
        return nhaCungCapDAL.getAll();
    }
    
    public NhaCungCapDTO getById(int idNhaCungCap){ 
        return nhaCungCapDAL.getById(idNhaCungCap);
    }
    
    public ArrayList<NhaCungCapDTO> getByCondition(String condition){ 
        return nhaCungCapDAL.getByCondition(condition);
    }
    
    public boolean update(NhaCungCapDTO s) {
        return nhaCungCapDAL.update(s);
    }
    
    public int insert(NhaCungCapDTO s) {
        return nhaCungCapDAL.insert(s.getTenNhaCungCap(), s.getDiaChi(), s.getSoDienThoai());
    }
    
    public boolean delete(int maNhaCungCap) {
        return nhaCungCapDAL.delete(maNhaCungCap);
    }
}

