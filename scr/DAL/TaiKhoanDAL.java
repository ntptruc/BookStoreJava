/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Admin
 */

import DTO.TaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAL implements DALInterface<TaiKhoanDTO>{
    
    public static TaiKhoanDAL getInstance() {
        return new TaiKhoanDAL();
    }

    
    public boolean insert(int maNhanVien, String tenDangNhap, String matKhau) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into taikhoan "
                        + "(maNhanVien, tenDangNhap, matKhau) "
                        + "VALUES (?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, maNhanVien);
                stmt.setString(2, tenDangNhap);
                stmt.setString(3, matKhau);
                
               

                result = stmt.executeUpdate()>=1;
                
               
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean update(TaiKhoanDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE taikhoan SET "
                        + "tenDangNhap=?, matKhau=?"
                        + "WHERE maNhanVien=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                
                stmt.setString(1, t.getTenDangNhap());
                stmt.setString(2, t.getMatKhau());
                stmt.setInt(3, t.getMaNhanVien());

                

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE taikhoan SET hienThi = 0 "
                        + "WHERE maNhanVien=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> getAll() {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM taikhoan WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    String tenDangNhap = rs.getString("tenDangNhap");
                    String matKhau = rs.getString("matKhau");
                    
                    
                    TaiKhoanDTO s = new TaiKhoanDTO(maNhanVien,tenDangNhap, matKhau);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    
    @Override
    public TaiKhoanDTO getById(int id) {
        TaiKhoanDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM taikhoan WHERE hienThi = 1 and maNhanVien=" + id ;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    String tenDangNhap = rs.getString("tenDangNhap");
                  
                    String matKhau = rs.getString("matKhau");
                    
                    
                    result = new TaiKhoanDTO(maNhanVien, tenDangNhap, matKhau);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<TaiKhoanDTO> getByCondition(String condition) {
        ArrayList<TaiKhoanDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM taikhoan WHERE hienThi = 1 and " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    
                    String tenDangNhap = rs.getString("tenDangNhap");
                   
                    String matKhau = rs.getString("matKhau");
                    
                    
                    TaiKhoanDTO s = new TaiKhoanDTO(maNhanVien, tenDangNhap, matKhau);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TaiKhoanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(TaiKhoanDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
}
