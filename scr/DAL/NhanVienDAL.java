    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Hung
 */

import DTO.NhanVienDTO;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAL implements DALInterface<NhanVienDTO>{
    
    public static NhanVienDAL getInstance() {
        return new NhanVienDAL();
    }

    public int insert(String tenNhanVien, int namSinh, String gioiTinh, String soDienThoai, long luong, int soNgayNghi, String vaiTro) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into nhanvien "
                        + "(tenNhanVien, namSinh, gioiTinh, soDienThoai, luong, soNgayNghi, vaiTro) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenNhanVien);
                stmt.setInt(2, namSinh);
                stmt.setString(3, gioiTinh);
                stmt.setString(4, soDienThoai);
                stmt.setLong(5, luong);
                stmt.setInt(6, soNgayNghi);
                stmt.setString(7, vaiTro);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(NhanVienDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE nhanvien SET "
                        + "tenNhanVien=?, namSinh=?, gioiTinh=?, soDienThoai=?, luong=?, soNgayNghi=?, vaiTro=? "
                        + "WHERE maNhanVien=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTen());
                stmt.setInt(2, t.getNamSinh());
                stmt.setString(3, t.getGioiTinh());
                stmt.setString(4, t.getSoDienThoai());
                stmt.setLong(5, t.getLuong());
                stmt.setInt(6, t.getSoNgayNghi());
                stmt.setString(7, t.getVaiTro());
                stmt.setInt(8, t.getMaNhanVien());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE nhanvien SET hienThi=0 "
                        + "WHERE maNhanVien=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> getAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM nhanvien WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    String tenNhanVien = rs.getString("tenNhanVien");
                    int namSinh = rs.getInt("namSinh");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    long luong = rs.getLong("luong");
                    int soNgayNghi = rs.getInt("soNgayNghi");
                    String vaiTro = rs.getString("vaiTro");
                    
                    NhanVienDTO nv = new NhanVienDTO(maNhanVien, tenNhanVien, namSinh, gioiTinh, soDienThoai, luong, soNgayNghi, vaiTro);
                 
                    result.add(nv);
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
    public NhanVienDTO getById(int id) {
        NhanVienDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM nhanvien WHERE hienThi=1 AND maNhanVien=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    String tenNhanVien = rs.getString("tenNhanVien");
                    int namSinh = rs.getInt("namSinh");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    long luong = rs.getLong("luong");
                    int soNgayNghi = rs.getInt("soNgayNghi");
                    String vaiTro = rs.getString("vaiTro");
                    
                    result = new NhanVienDTO(maNhanVien, tenNhanVien, namSinh, gioiTinh, soDienThoai, luong, soNgayNghi, vaiTro);
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
    public ArrayList<NhanVienDTO> getByCondition(String condition) {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM nhanvien WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    String tenNhanVien = rs.getString("tenNhanVien");
                    int namSinh = rs.getInt("namSinh");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    long luong = rs.getLong("luong");
                    int soNgayNghi = rs.getInt("soNgayNghi");
                    String vaiTro = rs.getString("vaiTro");
                    
                    NhanVienDTO nv = new NhanVienDTO(maNhanVien, tenNhanVien, namSinh, gioiTinh, soDienThoai, luong, soNgayNghi, vaiTro);
                 
                    result.add(nv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }
    
    public ArrayList<NhanVienDTO> getEmployeeUnAccount() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();

        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {

            try {
                String sql = "SELECT * FROM nhanvien "
                            + "WHERE maNhanVien NOT IN (SELECT maNhanVien FROM taikhoan)";

                // Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                // Bước 3: lấy dữ liệu
                while (rs.next()) {
                    int maNhanVien = rs.getInt("maNhanVien");
                    String tenNhanVien = rs.getString("tenNhanVien");
                    int namSinh = rs.getInt("namSinh");
                    String soDienThoai = rs.getString("soDienThoai");
                    String gioiTinh = rs.getString("gioiTinh");
                    long luong = rs.getLong("luong");
                    int soNgayNghi = rs.getInt("soNgayNghi");
                    String vaiTro = rs.getString("vaiTro");

                    NhanVienDTO s = new NhanVienDTO(maNhanVien, tenNhanVien, namSinh, gioiTinh, soDienThoai, luong,
                            soNgayNghi, vaiTro);

                    result.add(s);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }

        return result;
    };

    @Override
    public boolean insert(NhanVienDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
