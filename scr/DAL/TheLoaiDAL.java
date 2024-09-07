/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Admin
 */

import DTO.TheLoaiDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheLoaiDAL implements DALInterface<TheLoaiDTO>{
    
    public static TheLoaiDAL getInstance() {
        return new TheLoaiDAL();
    }

    public int insert(String tenTL) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into theloai "
                        + "(tenTheLoai) "
                        + "VALUES (?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                
                stmt.setString(1, tenTL);
                

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    public boolean update(TheLoaiDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE theloai SET "
                        + "tenTheLoai=?"
                        + "WHERE maTheLoai=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                
                stmt.setString(1, t.getTenTL());
                stmt.setInt(2, t.getMaTL());
                

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "UPDATE theloai SET hienThi = 0 "
                        + "WHERE maTheLoai=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<TheLoaiDTO> getAll() {
        ArrayList<TheLoaiDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM theloai WHERE hienThi = 1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maTL = rs.getInt("maTheLoai");
                    String tenTL = rs.getString("tenTheLoai");
                    
                    
                    TheLoaiDTO s = new TheLoaiDTO(maTL, tenTL);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public TheLoaiDTO getById(int id) {
        TheLoaiDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM theloai WHERE hienThi = 1 AND maTheLoai= " + id  ;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maTL = rs.getInt("maTheLoai");
                    String tenTL = rs.getString("tenTheLoai");
                    
                    
                    result = new TheLoaiDTO(maTL, tenTL);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<TheLoaiDTO> getByCondition(String condition) {
        ArrayList<TheLoaiDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM theloai WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maTL = rs.getInt("maTheLoai");
                    String tenTL = rs.getString("tenTheLoai");
                    
                    
                    TheLoaiDTO s = new TheLoaiDTO(maTL, tenTL);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TheLoaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }
    
    @Override
    public boolean insert(TheLoaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}

