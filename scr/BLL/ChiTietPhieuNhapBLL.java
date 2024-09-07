/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ChiTietPhieuNhapDAL;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author Hung
 */
public class ChiTietPhieuNhapBLL {
    ChiTietPhieuNhapDAL chiTietPhieuNhapDAL = new ChiTietPhieuNhapDAL();
    
    public ChiTietPhieuNhapBLL() {
        
    }
    
    public boolean insert(ChiTietPhieuNhapDTO ctpn) {
        return chiTietPhieuNhapDAL.insert(ctpn);
    }
    
    public boolean update(ChiTietPhieuNhapDTO ctpn, int maPhieuNhapCu, int maSachCu) {
        return chiTietPhieuNhapDAL.update(ctpn, maPhieuNhapCu, maSachCu);
    }
    
    public boolean delete(int maPhieuNhap, int maSach) {
        return chiTietPhieuNhapDAL.delete(maPhieuNhap, maSach);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getAll() {
        return chiTietPhieuNhapDAL.getAll();
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getByPNId(int id) {
        return chiTietPhieuNhapDAL.getByPNId(id);
    }
}
