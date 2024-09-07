
package DAL;

import DTO.NhaCungCapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NhaCungCapDAL implements DALInterface<NhaCungCapDTO>{
    
    public static NhaCungCapDAL getInstance() {
        return new NhaCungCapDAL();
    }

    public int insert(String tenNhaCungCap,String diaChi,String soDienThoai) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into nhacungcap "+ "(tenNhaCungCap,diaChi,soDienThoai) "+ "VALUES (?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenNhaCungCap);
                stmt.setString(2, diaChi);
                stmt.setString(3, soDienThoai);
                
                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaCungCapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(NhaCungCapDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
 
        if (connect != null) {
            try {
                String sql = "UPDATE nhacungcap SET "
                        + "tenNhaCungCap=? , diaChi=? ,soDienThoai = ? "
                        + "WHERE maNhaCungCap=?";
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTenNhaCungCap());
                stmt.setString(2, t.getDiaChi());
                stmt.setString(3, t.getSoDienThoai());
                stmt.setInt(4, t.getMaNhaCungCap());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(NhaCungCapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "UPDATE nhacungcap SET hienThi=0 "
                        + "WHERE maNhaCungCap=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(NhaCungCapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> getAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM nhacungcap WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhaCungCap = rs.getInt("maNhaCungCap");
                    String tenNhaCungCap = rs.getString("tenNhaCungCap");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    NhaCungCapDTO s = new NhaCungCapDTO(maNhaCungCap,tenNhaCungCap,diaChi,soDienThoai);
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaCungCapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public NhaCungCapDTO getById(int id) {
        NhaCungCapDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM nhacungcap WHERE hienThi=1 AND maNhaCungCap=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhaCungCap = rs.getInt("maNhaCungCap");
                    String tenNhaCungCap = rs.getString("tenNhaCungCap");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    
                    result = new NhaCungCapDTO(maNhaCungCap,tenNhaCungCap,diaChi,soDienThoai);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaCungCapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> getByCondition(String condition) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM nhacungcap WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhaCungCap = rs.getInt("maNhaCungCap");
                    String tenNhaCungCap = rs.getString("tenNhaCungCap");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    
                    NhaCungCapDTO s = new NhaCungCapDTO(maNhaCungCap,tenNhaCungCap,diaChi,soDienThoai);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaCungCapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(NhaCungCapDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
