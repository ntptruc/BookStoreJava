/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DTO.ChiTietPhieuBanDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.KhuyenMaiDTO;
import DTO.PhieuBanDTO;
import DTO.PhieuNhapDTO;
import GUI.PriceFormatter;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
/**
 *
 * @author Hung
 */
public class PrintPDF {
    
    Document document;
    FileOutputStream file;
    Font fontData;
    Font fontTitle;
    Font fontHeader;
    
    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    public PrintPDF() {
        try {
            fontData = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
            fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 13, Font.NORMAL);
            
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(PrintPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private String getFile(int name) {
        fd.setFile(name + ".pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    

    public void writeHoaDon(int maPhieuBan) {
        String url = "";
        try {
            fd.setTitle("In hóa đơn");
            url = getFile(maPhieuBan);
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            document.open();
            
            setTitle("Thông tin hóa đơn");
            //Hien thong tin cua hoa don hien tai
            PhieuBanBLL phieuBanBLL = new PhieuBanBLL();
            KhachHangBLL khachHangBLL = new KhachHangBLL();
            NhanVienBLL nhanVienBLL = new NhanVienBLL();
            KhuyenMaiBLL khuyenMaiBLL = new KhuyenMaiBLL();
            SachBLL sachBLL = new SachBLL();
            ChiTietPhieuBanBLL chiTietPhieuBanBLL = new ChiTietPhieuBanBLL();

            PhieuBanDTO pb = phieuBanBLL.getById(maPhieuBan);
            
            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Hóa đơn: " + String.valueOf(pb.getMaPhieuBan()), fontData));

            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Khách hàng: " + khachHangBLL.getByKHid(pb.getMaKhachHang()).getTen() + "  -  " + pb.getMaKhachHang()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(pb.getNgayLap()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + nhanVienBLL.getByNVid(pb.getMaNhanVien()).getTen() + "  -  " + pb.getMaNhanVien()));
            para2.add(glue);
            para2.add("Khuyến mãi: " + String.valueOf(khuyenMaiBLL.getById(pb.getMaKhuyenMai()).getPhanTram() + "%"));


            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);

            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(6);
            double tongKhuyenMai = 0;
            double tongThanhTien = 0;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("STT", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Thành tiền", fontHeader)));

            for (int i = 0; i < 6; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }
            
            int i = 1;
            //Truyen thong tin tung chi tiet vao table
            for (ChiTietPhieuBanDTO ctpb : chiTietPhieuBanBLL.getByPBId(maPhieuBan)) {
                
                String ma = String.valueOf(ctpb.getMaSach());
                String ten = sachBLL.getById(ctpb.getMaSach()).getTenSach();
                String donGia = PriceFormatter.format(ctpb.getDonGia());
                String soLuong = String.valueOf(ctpb.getSoLuong());
                String thanhTien = PriceFormatter.format(ctpb.getDonGia() * ctpb.getSoLuong());

                pdfTable.addCell(new PdfPCell(new Phrase("" + i, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ma, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(donGia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soLuong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhTien, fontData)));

                tongThanhTien += ctpb.getDonGia() * ctpb.getSoLuong();
                i++;
            }
            

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            tongKhuyenMai = tongThanhTien - pb.getTongTien();
                    
            Paragraph paraTongThanhTien = new Paragraph(new Phrase("Tổng thành tiền: " + PriceFormatter.format(tongThanhTien), fontData));
            paraTongThanhTien.setIndentationLeft(300);
            document.add(paraTongThanhTien);
            Paragraph paraTongKhuyenMai = new Paragraph(new Phrase("Tổng khuyến mãi: " + PriceFormatter.format(tongKhuyenMai), fontData));
            paraTongKhuyenMai.setIndentationLeft(300);
            document.add(paraTongKhuyenMai);
            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format(pb.getTongTien()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }

    public void writePhieuNhap(int maPhieuNhap) {
        String url = "";
        try {
            fd.setTitle("In phiếu nhập");
            url = getFile(maPhieuNhap);
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            document.open();
            
            setTitle("Thông tin phiếu nhập");

            PhieuNhapBLL phieuNhapBLL = new PhieuNhapBLL();
            NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
            NhanVienBLL nhanVienBLL = new NhanVienBLL();
            SachBLL sachBLL = new SachBLL();
            ChiTietPhieuNhapBLL chiTietPhieuNhapBLL = new ChiTietPhieuNhapBLL();

            PhieuNhapDTO pn = phieuNhapBLL.getById(maPhieuNhap);

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Phiếu nhập: " + String.valueOf(pn.getMaPhieuNhap()), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Nhà cung cấp: " + nhaCungCapBLL.getById(pn.getMaNhaCungCap()).getTenNhaCungCap()+ "  -  " + pn.getMaNhaCungCap()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(pn.getNgayLap()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + nhanVienBLL.getByNVid(pn.getMaNhanVien()).getTen() + "  -  " + pn.getMaNhanVien()));
            para2.add(glue);

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(6);
            PdfPCell cell;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("STT", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Thành tiền", fontHeader)));

            for (int i = 0; i < 6; i++) {
                cell = new PdfPCell(new Phrase(""));
                pdfTable.addCell(cell);
            }

            int i = 1;
            //Truyen thong tin tung chi tiet vao table
            for (ChiTietPhieuNhapDTO ctpn : chiTietPhieuNhapBLL.getByPNId(maPhieuNhap)) {
                String ma = String.valueOf(ctpn.getMaSach());
                String ten = sachBLL.getById(ctpn.getMaSach()).getTenSach();
                String donGia = PriceFormatter.format(ctpn.getDonGia());
                String soLuong = String.valueOf(ctpn.getSoLuong());
                String thanhTien = PriceFormatter.format(ctpn.getDonGia() * ctpn.getSoLuong());
                
                pdfTable.addCell(new PdfPCell(new Phrase("" + i, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ma, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(donGia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soLuong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhTien, fontData)));

                i++;
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format(pn.getTongTien()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }

    public void closeFile() {
        document.close();
    }
}
