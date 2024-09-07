/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class TheLoaiDTO {
    int MaTL;
    String TenTL;

    public TheLoaiDTO() {
    }

    public TheLoaiDTO(int MaTL, String TenTL) {
        this.MaTL = MaTL;
        this.TenTL = TenTL;       
    }

    public String getTenTL() {
        return TenTL;
    }

    public void setTenTL(String TenTL) {
        this.TenTL = TenTL;
    }

    public int getMaTL() {
        return MaTL;
    }

    public void setMaTL(int MaTL) {
        this.MaTL = MaTL;
    }

}
