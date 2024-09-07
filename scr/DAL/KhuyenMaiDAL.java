/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import DTO.KhuyenMaiDTO;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hung
 */
public class KhuyenMaiDAL implements DALInterface<KhuyenMaiDTO>{
    public static KhuyenMaiDAL getInstance() {
        return new KhuyenMaiDAL();
    }

    public int insert(String tenKhuyenMai, int phanTram, Date ngayBatDau, Date ngayKetThuc) {
        boolean result = false;
        int auto_id = -1;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "INSERT into khuyenmai "
                        + "(tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc) "
                        + "VALUES (?, ?, ?, ?)";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, tenKhuyenMai);
                stmt.setInt(2, phanTram);
                stmt.setDate(3, ngayBatDau);
                stmt.setDate(4, ngayKetThuc);

                result = stmt.executeUpdate()>=1;
                
                if (result) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    auto_id = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return auto_id;
    }

    @Override
    public boolean update(KhuyenMaiDTO t) {
        boolean result = false;
        //Bước 1: tạo kết nối với sql
        Connection connect = ConnectDatabase.openConnection();
        
        if (connect != null) {
            try {
                String sql = "UPDATE khuyenmai SET "
                        + "tenKhuyenMai=?, phanTram=?, ngayBatDau=?, ngayKetThuc=? "
                        + "WHERE maKhuyenMai=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setString(1, t.getTenKhuyenMai());
                stmt.setInt(2, t.getPhanTram());
                stmt.setDate(3, t.getNgayBatDau());
                stmt.setDate(4, t.getNgayKetThuc());
                stmt.setInt(5, t.getMaKhuyenMai());

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "UPDATE khuyenmai SET hienThi=0 "
                        + "WHERE maKhuyenMai=?";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 
                stmt.setInt(1, id); 

                result = stmt.executeUpdate()>=1;
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> getAll() {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM khuyenmai WHERE hienThi=1";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    String tenKhuyenMai = rs.getString("tenKhuyenMai");
                    int phanTram = rs.getInt("phanTram");
                    Date ngayBatDau = rs.getDate("ngayBatDau");
                    Date ngayKetThuc = rs.getDate("ngayKetThuc");
                    
                    KhuyenMaiDTO kh = new KhuyenMaiDTO(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc);
                 
                    result.add(kh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public KhuyenMaiDTO getById(int id) {
        KhuyenMaiDTO result = null;
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            try {
                String sql = "SELECT * FROM khuyenmai WHERE hienThi=1 AND maKhuyenMai=" + id;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql); 

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    String tenKhuyenMai = rs.getString("tenKhuyenMai");
                    int phanTram = rs.getInt("phanTram");
                    Date ngayBatDau = rs.getDate("ngayBatDau");
                    Date ngayKetThuc = rs.getDate("ngayKetThuc");
                    
                    result = new KhuyenMaiDTO(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<KhuyenMaiDTO> getByCondition(String condition) {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM khuyenmai WHERE hienThi=1 AND " + condition;

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    String tenKhuyenMai = rs.getString("tenKhuyenMai");
                    int phanTram = rs.getInt("phanTram");
                    Date ngayBatDau = rs.getDate("ngayBatDau");
                    Date ngayKetThuc = rs.getDate("ngayKetThuc");
                    
                    KhuyenMaiDTO kh = new KhuyenMaiDTO(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc);
                 
                    result.add(kh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }

    @Override
    public boolean insert(KhuyenMaiDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<KhuyenMaiDTO> getSaleByDate(Date now) {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        
        Connection connect = ConnectDatabase.openConnection();
        if (connect != null) {
            
            try {
                String sql = "SELECT * FROM khuyenmai "
                        + "WHERE ? BETWEEN ngayBatDau AND ngayKetThuc";

                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connect.prepareStatement(sql);  
                stmt.setDate(1, now);
                
                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    int maKhuyenMai = rs.getInt("maKhuyenMai");
                    String tenKhuyenMai = rs.getString("tenKhuyenMai");
                    int phanTram = rs.getInt("phanTram");
                    Date ngayBatDau = rs.getDate("ngayBatDau");
                    Date ngayKetThuc = rs.getDate("ngayKetThuc");
                    
                    KhuyenMaiDTO kh = new KhuyenMaiDTO(maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDau, ngayKetThuc);
                 
                    result.add(kh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMaiDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ConnectDatabase.closeConnection(connect);
            }
        }
        
        return result;
    }
}
