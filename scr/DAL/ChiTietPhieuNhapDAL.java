/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Hung
 */

import DTO.ChiTietPhieuNhapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietPhieuNhapDAL implements DALInterface<ChiTietPhieuNhapDTO>{
    
    public ChiTietPhieuNhapDAL() {
    }
    
    public static ChiTietPhieuNhapDAL getInstance() {
        return new ChiTietPhieuNhapDAL();
    }

    @Override
    public boolean insert(ChiTietPhieuNhapDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into chitietphieunhap "
                        + "(maPhieuNhap, maSach, soLuong, donGia) "
                        + "VALUES (?, ?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, t.getMaPhieuNhap());
                stmt.setInt(2, t.getMaSach());
                stmt.setInt(3, t.getSoLuong());
                stmt.setLong(4, t.getDonGia());
                
                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    public boolean update(ChiTietPhieuNhapDTO t, int maPhieuNhapCu, int maSachCu) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE chitietphieunhap SET "
                        + "maPhieuNhap=?, maSach=?, soLuong=?, donGia=? "
                        + "WHERE maPhieuNhap=? AND maSach=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, t.getMaPhieuNhap());
                stmt.setInt(2, t.getMaSach());
                stmt.setInt(3, t.getSoLuong());
                stmt.setLong(4, t.getDonGia());
                stmt.setInt(5, maPhieuNhapCu);
                stmt.setInt(6, maSachCu);
                
                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    public boolean delete(int maPhieuNhap, int maSach) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "UPDATE chitietphieunhap SET hienThi=0 "
                        + "WHERE maPhieuNhap=? AND maSach=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, maPhieuNhap);
                stmt.setInt(2, maSach);

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }
    
    public boolean deleteAll(int maPhieuNhap) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "UPDATE chitietphieunhap SET hienThi=0 "
                        + "WHERE maPhieuNhap=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, maPhieuNhap);

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> getAll() {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM chitietphieunhap WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maPhieuNhap = rs.getInt("maPhieuNhap");
                    int maSach = rs.getInt("maSach");
                    int soLuong = rs.getInt("soLuong");
                    long donGia = rs.getLong("donGia");
                    
                    ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(maPhieuNhap, maSach, soLuong, donGia);
                 
                    result.add(ctpn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getByPNId(int id) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM chitietphieunhap WHERE hienThi=1 AND maPhieuNhap=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maPhieuNhap = rs.getInt("maPhieuNhap");
                    int maSach = rs.getInt("maSach");
                    int soLuong = rs.getInt("soLuong");
                    long donGia = rs.getLong("donGia");
                    
                    ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(maPhieuNhap, maSach, soLuong, donGia);
                    
                    result.add(ctpn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> getByCondition(String condition) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM chitietphieunhap WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maPhieuNhap = rs.getInt("maPhieuNhap");
                    int maSach = rs.getInt("maSach");
                    int soLuong = rs.getInt("soLuong");
                    long donGia = rs.getLong("donGia");
                    
                    ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(maPhieuNhap, maSach, soLuong, donGia);
                 
                    result.add(ctpn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietPhieuNhapDTO getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ChiTietPhieuNhapDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
