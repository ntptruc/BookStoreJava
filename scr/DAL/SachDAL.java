/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Hung
 */

import DTO.SachDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SachDAL implements DALInterface<SachDTO>{
    
    public static SachDAL getInstance() {
        return new SachDAL();
    }

    public int insert(String tenSach, int maTheLoai, int maTacGia, int maNhaXuatBan, int soLuongConLai, long giaBan, long giaNhap, int namXuatBan) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into sach "
                        + "(tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenSach);
                stmt.setInt(2, maTheLoai);
                stmt.setInt(3, maTacGia);
                stmt.setInt(4,maNhaXuatBan);
                stmt.setInt(5, soLuongConLai);
                stmt.setLong(6, giaBan);
                stmt.setLong(7, giaNhap);
                stmt.setInt(8, namXuatBan);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }
    
    @Override
    public boolean update(SachDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE sach SET "
                        + "tenSach=?, maTheLoai=?, maTacGia=?, maNhaXuatBan=?, soLuongConLai=?, giaBan=?, giaNhap=?, namXuatBan=? "
                        + "WHERE maSach=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTenSach());
                stmt.setInt(2, t.getMaTheLoai());
                stmt.setInt(3, t.getMaTacGia());
                stmt.setInt(4, t.getMaNhaXuatBan());
                stmt.setInt(5, t.getSoLuongConLai());
                stmt.setLong(6, t.getGiaBan());
                stmt.setLong(7, t.getGiaNhap());
                stmt.setInt(8, t.getNamXuatBan());
                stmt.setInt(9, t.getMaSach());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE sach SET hienThi=0 "
                        + "WHERE maSach=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<SachDTO> getAll() {
        ArrayList<SachDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM sach WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maSach = rs.getInt("maSach");
                    String tenSach = rs.getString("tenSach");
                    int maTheLoai = rs.getInt("maTheLoai");
                    int maTacGia = rs.getInt("maTacGia");
                    int maNhaXuatBan = rs.getInt("maNhaXuatBan");
                    int soLuongConLai = rs.getInt("soLuongConLai");
                    long giaBan = rs.getLong("giaBan");
                    long giaNhap = rs.getLong("giaNhap");
                    int namXuatBan = rs.getInt("namXuatBan");
                    
                    SachDTO s = new SachDTO(maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public SachDTO getById(int id) {
        SachDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM sach WHERE hienThi=1 AND maSach=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maSach = rs.getInt("maSach");
                    String tenSach = rs.getString("tenSach");
                    int maTheLoai = rs.getInt("maTheLoai");
                    int maTacGia = rs.getInt("maTacGia");
                    int maNhaXuatBan = rs.getInt("maNhaXuatBan");
                    int soLuongConLai = rs.getInt("soLuongConLai");
                    long giaBan = rs.getLong("giaBan");
                    long giaNhap = rs.getLong("giaNhap");
                    int namXuatBan = rs.getInt("namXuatBan");
                    
                    result = new SachDTO(maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<SachDTO> getByCondition(String condition) {
        ArrayList<SachDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM sach WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maSach = rs.getInt("maSach");
                    String tenSach = rs.getString("tenSach");
                    int maTheLoai = rs.getInt("maTheLoai");
                    int maTacGia = rs.getInt("maTacGia");
                    int maNhaXuatBan = rs.getInt("maNhaXuatBan");
                    int soLuongConLai = rs.getInt("soLuongConLai");
                    long giaBan = rs.getLong("giaBan");
                    long giaNhap = rs.getLong("giaNhap");
                    int namXuatBan = rs.getInt("namXuatBan");
                    
                    SachDTO s = new SachDTO(maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan);
                 
                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SachDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(SachDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
