package DAL;

import DAL.ConnectDatabase;
import DAL.DALInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.KhachHangDTO;

public class KhachHangDAL implements DALInterface<KhachHangDTO>{
    
    public static KhachHangDAL getInstance() {
        return new KhachHangDAL();
    }

    public int insert(String tenKhachHang, String soDienThoai, String gioiTinh, int namSinh) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into khachhang "
                        + "(tenKhachHang, soDienThoai, gioiTinh, namSinh) "
                        + "VALUES (?, ?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenKhachHang);
                stmt.setString(2, soDienThoai);
                stmt.setString(3, gioiTinh);
                stmt.setInt(4, namSinh);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(KhachHangDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE khachhang SET "
                        + "tenKhachHang=?, soDienThoai=?, gioiTinh=?, namSinh=? "
                        + "WHERE maKhachHang=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTen());
                stmt.setString(2, t.getSoDienThoai());
                stmt.setString(3, t.getGioiTinh());
                stmt.setInt(4, t.getNamSinh());
                stmt.setInt(5, t.getMaKhachHang());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE khachhang SET hienThi=0 "
                        + "WHERE maKhachHang=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> getAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM khachhang WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhachHang = rs.getInt("maKhachHang");
                    String tenKhachHang = rs.getString("tenKhachHang");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    int namSinh = rs.getInt("namSinh");
                    
                    KhachHangDTO kh = new KhachHangDTO(maKhachHang, tenKhachHang, gioiTinh, soDienThoai, namSinh);
                 
                    result.add(kh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public KhachHangDTO getById(int id) {
        KhachHangDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM khachhang WHERE hienThi=1 AND maKhachHang=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhachHang = rs.getInt("maKhachHang");
                    String tenKhachHang = rs.getString("tenKhachHang");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    int namSinh = rs.getInt("namSinh");
                    
                    result = new KhachHangDTO(maKhachHang, tenKhachHang, gioiTinh, soDienThoai, namSinh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<KhachHangDTO> getByCondition(String condition) {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM khachhang WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhachHang = rs.getInt("maKhachHang");
                    String tenKhachHang = rs.getString("tenKhachHang");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    int namSinh = rs.getInt("namSinh");
                    
                    KhachHangDTO kh = new KhachHangDTO(maKhachHang, tenKhachHang, gioiTinh, soDienThoai, namSinh);
                 
                    result.add(kh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(KhachHangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
