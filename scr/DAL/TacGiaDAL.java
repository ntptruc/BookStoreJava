/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Hung
 */

import DTO.TacGiaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TacGiaDAL implements DALInterface<TacGiaDTO>{
    
    public static TacGiaDAL getInstance() {
        return new TacGiaDAL();
    }

    public int insert(String tenTacGia, String gioiTinh, int namSinh) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into tacgia "
                        + "(tenTacGia, gioiTinh, namSinh) "
                        + "VALUES (?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenTacGia);
                stmt.setString(2, gioiTinh);
                stmt.setInt(3, namSinh);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(TacGiaDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE tacgia SET "
                        + "tenTacGia=?, gioiTinh=?, namSinh=? "
                        + "WHERE maTacGia=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTen());
                stmt.setString(2, t.getGioiTinh());
                stmt.setInt(3, t.getNamSinh());
                stmt.setInt(4, t.getMaTacGia());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE tacgia SET hienThi=0 "
                        + "WHERE maTacGia=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<TacGiaDTO> getAll() {
        ArrayList<TacGiaDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM tacgia WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maTacGia = rs.getInt("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String gioiTinh = rs.getString("gioiTinh");
                    int namSinh = rs.getInt("namSinh");
                    
                    TacGiaDTO nxb = new TacGiaDTO(maTacGia, tenTacGia, gioiTinh, namSinh);
                 
                    result.add(nxb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public TacGiaDTO getById(int id) {
        TacGiaDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM tacgia WHERE hienThi=1 AND maTacGia=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maTacGia = rs.getInt("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String gioiTinh = rs.getString("gioiTinh");
                    int namSinh = rs.getInt("namSinh");
                    
                    result = new TacGiaDTO(maTacGia, tenTacGia, gioiTinh, namSinh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<TacGiaDTO> getByCondition(String condition) {
        ArrayList<TacGiaDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM tacgia WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maTacGia = rs.getInt("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String gioiTinh = rs.getString("gioiTinh");
                    int namSinh = rs.getInt("namSinh");
                    
                    TacGiaDTO nxb = new TacGiaDTO(maTacGia, tenTacGia, gioiTinh, namSinh);
                 
                    result.add(nxb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TacGiaDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(TacGiaDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
