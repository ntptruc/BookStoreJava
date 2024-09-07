/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Hung
 */

import DTO.PhieuBanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuBanDAL implements DALInterface<PhieuBanDTO>{
    
    public static PhieuBanDAL getInstance() {
        return new PhieuBanDAL();
    }

    public int insert(int maKhachHang, int maNhanVien, Date ngayLap, double tongTien, int maKhuyenMai) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into phieuban "
                        + "(maKhachHang, maNhanVien, ngayLap, tongTien, maKhuyenMai) "
                        + "VALUES (?, ?, ?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setInt(1, maKhachHang);
                stmt.setInt(2, maNhanVien);
                stmt.setDate(3, ngayLap);
                stmt.setDouble(4, tongTien);
                stmt.setDouble(5, maKhuyenMai);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(PhieuBanDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE phieuban SET "
                        + "maKhachHang=?, maNhanVien=?, ngayLap=?, tongTien=?, maKhuyenMai=? "
                        + "WHERE maPhieuBan=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, t.getMaKhachHang());
                stmt.setInt(2, t.getMaNhanVien());
                stmt.setDate(3, t.getNgayLap());
                stmt.setDouble(4, t.getTongTien());
                stmt.setInt(5, t.getMaKhuyenMai());
                stmt.setInt(6, t.getMaPhieuBan());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(PhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE phieuban SET hienThi=0 "
                        + "WHERE maPhieuBan=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    sql = "UPDATE chitietphieuban SET hienThi=0 "
                          + "WHERE maPhieuBan=?";

                    //Bước 2: tạo đối tượng preparedStatement
                    stmt = connect.prepareStatement(sql); 
                    stmt.setInt(1, id);
                    
                    result = stmt.executeUpdate()>=1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<PhieuBanDTO> getAll() {
        ArrayList<PhieuBanDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM phieuban WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maPhieuBan = rs.getInt("maPhieuBan");
                    int maKhachHang = rs.getInt("maKhachHang");
                    int maNhanVien = rs.getInt("maNhanVien");
                    Date ngayLap = rs.getDate("ngayLap");
                    double tongTien = rs.getDouble("tongTien");
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    
                    PhieuBanDTO pb = new PhieuBanDTO(maPhieuBan, maKhachHang, maNhanVien, ngayLap, tongTien, maKhuyenMai);
                 
                    result.add(pb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public PhieuBanDTO getById(int id) {
        PhieuBanDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM phieuban WHERE hienThi=1 AND maPhieuBan=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maPhieuBan = rs.getInt("maPhieuBan");
                    int maKhachHang = rs.getInt("maKhachHang");
                    int maNhanVien = rs.getInt("maNhanVien");
                    Date ngayLap = rs.getDate("ngayLap");
                    double tongTien = rs.getDouble("tongTien");
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    
                    result = new PhieuBanDTO(maPhieuBan, maKhachHang, maNhanVien, ngayLap, tongTien, maKhuyenMai);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<PhieuBanDTO> getByCondition(String condition) {
        ArrayList<PhieuBanDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM phieuban WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maPhieuBan = rs.getInt("maPhieuBan");
                    int maKhachHang = rs.getInt("maKhachHang");
                    int maNhanVien = rs.getInt("maNhanVien");
                    Date ngayLap = rs.getDate("ngayLap");
                    double tongTien = rs.getDouble("tongTien");
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    
                    PhieuBanDTO pb = new PhieuBanDTO(maPhieuBan, maKhachHang, maNhanVien, ngayLap, tongTien, maKhuyenMai);
                 
                    result.add(pb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PhieuBanDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }


    @Override
    public boolean insert(PhieuBanDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
