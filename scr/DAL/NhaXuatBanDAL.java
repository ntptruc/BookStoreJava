/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Hung
 */

import DTO.NhaXuatBanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhaXuatBanDAL implements DALInterface<NhaXuatBanDTO>{
    
    public static NhaXuatBanDAL getInstance() {
        return new NhaXuatBanDAL();
    }

    public int insert(String tenNhaXuatBan, String diaChi, String soDienThoai) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into nhaxuatban "
                        + "(tenNhaXuatBan, diaChi, soDienThoai) "
                        + "VALUES (?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenNhaXuatBan);
                stmt.setString(2, diaChi);
                stmt.setString(3, soDienThoai);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaXuatBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(NhaXuatBanDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE nhaxuatban SET "
                        + "tenNhaXuatBan=?, diaChi=?, soDienThoai=? "
                        + "WHERE maNhaXuatBan=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTenNhaXuatBan());
                stmt.setString(2, t.getDiaChi());
                stmt.setString(3, t.getSoDienThoai());
                stmt.setInt(4, t.getMaNhaXuatBan());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(NhaXuatBanDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE nhaxuatban SET hienThi=0"
                        + "WHERE maNhaXuatBan=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(NhaXuatBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<NhaXuatBanDTO> getAll() {
        ArrayList<NhaXuatBanDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM nhaxuatban WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhaXuatBan = rs.getInt("maNhaXuatBan");
                    String tenNhaXuatBan = rs.getString("tenNhaXuatBan");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    
                    NhaXuatBanDTO nxb = new NhaXuatBanDTO(maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai);
                 
                    result.add(nxb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaXuatBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public NhaXuatBanDTO getById(int id) {
        NhaXuatBanDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM nhaxuatban WHERE hienThi=1 AND maNhaXuatBan=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhaXuatBan = rs.getInt("maNhaXuatBan");
                    String tenNhaXuatBan = rs.getString("tenNhaXuatBan");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    
                    result = new NhaXuatBanDTO(maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaXuatBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<NhaXuatBanDTO> getByCondition(String condition) {
        ArrayList<NhaXuatBanDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM nhaxuatban WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhaXuatBan = rs.getInt("maNhaXuatBan");
                    String tenNhaXuatBan = rs.getString("tenNhaXuatBan");
                    String diaChi = rs.getString("diaChi");
                    String soDienThoai = rs.getString("soDienThoai");
                    
                    NhaXuatBanDTO nxb = new NhaXuatBanDTO(maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai);
                 
                    result.add(nxb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhaXuatBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(NhaXuatBanDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
