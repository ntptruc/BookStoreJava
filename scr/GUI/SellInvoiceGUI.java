/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.ChiTietPhieuBanBLL;
import BLL.KhachHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import BLL.PhieuBanBLL;
import BLL.PrintPDF;
import BLL.SachBLL;
import DTO.ChiTietPhieuBanDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import DTO.PhieuBanDTO;
import DTO.SachDTO;
import DTO.TaiKhoanDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hung
 */
public final class SellInvoiceGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private SachBLL sachBLL = new SachBLL();
    private PhieuBanBLL phieuBanBLL = new PhieuBanBLL();
    private KhachHangBLL khachHangBLL = new KhachHangBLL();
    private NhanVienBLL nhanVienBLL = new NhanVienBLL();
    private KhuyenMaiBLL khuyenMaiBLL = new KhuyenMaiBLL();
    private ChiTietPhieuBanBLL chiTietPhieuBanBLL = new ChiTietPhieuBanBLL();
    
    private Locale lc = new Locale("nv","VN"); //Định dạng locale việt nam
    private NumberFormat nf = NumberFormat.getInstance(lc);
    private Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
    private Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
    
    private JComboBox maKhachHangPB = new JComboBox();
    private JComboBox maNhanVienPB = new JComboBox();
    private JDateChooser ngayLapPB = new JDateChooser();
    private JComboBox maKhuyenMaiPB = new JComboBox();
    private JPanel popUpUpdatePB = getPopUpUpdatePB();
    
    private JComboBox maPhieuBanCTPB_add = new JComboBox();
    private JComboBox maPhieuBanPDF = new JComboBox();
    private JComboBox maSachCTPB_add = new JComboBox();
    private JTextField soLuongCTPB_add = new JTextField();
    
    private JComboBox maPhieuBanCTPB_update = new JComboBox();
    private JComboBox maSachCTPB_update = new JComboBox();
    private JTextField soLuongCTPB_update = new JTextField();
    
    private JPanel popUpAddCTPB = getPopUpAddCTPB();
    private JPanel popUpUpdateCTPB = getPopUpUpdateCTPB();
    
    
    private void setJComboBox() {
        long millis=System.currentTimeMillis();  
        java.sql.Date now = new java.sql.Date(millis);
        ArrayList<KhachHangDTO> khachHangList = khachHangBLL.getAll();
        ArrayList<NhanVienDTO> nhanVienList = nhanVienBLL.getAll();
        ArrayList<PhieuBanDTO> phieuBanList = phieuBanBLL.getAll();
        ArrayList<SachDTO> sachList = sachBLL.getAllSach();
        ArrayList<KhuyenMaiDTO> khuyenMaiList = khuyenMaiBLL.getSaleByDate(now);
        
        maKhuyenMaiPB.addItem("");
        
        maPhieuBanCTPB_update.addItem("");
        maPhieuBanCTPB_add.addItem("");
        
        maSachCTPB_update.addItem("");
        maSachCTPB_add.addItem("");
        
        maKhachHangPB.addItem("");
        
        maNhanVienPB.addItem("");
        maPhieuBanPDF.addItem("");
        
        maPhieuBanCTPB_add.setSelectedIndex(0);
        maPhieuBanPDF.setSelectedIndex(0);
        maSachCTPB_add.setSelectedIndex(0);
        maKhachHangPB.setSelectedIndex(0);
        maNhanVienPB.setSelectedIndex(0);
        maKhuyenMaiPB.setSelectedIndex(0);
        
        for (KhuyenMaiDTO km : khuyenMaiList) {
            maKhuyenMaiPB.addItem(km.getMaKhuyenMai());
        }
        
        for (KhachHangDTO kh : khachHangList) {
            maKhachHangPB.addItem(kh.getMaKhachHang());
        }
        
        for (NhanVienDTO nv : nhanVienList) {
            maNhanVienPB.addItem(nv.getMaNhanVien());
        }
        
        for (PhieuBanDTO pb : phieuBanList) {
            maPhieuBanCTPB_add.addItem(pb.getMaPhieuBan());
            maPhieuBanCTPB_update.addItem(pb.getMaPhieuBan());
            maPhieuBanPDF.addItem(pb.getMaPhieuBan());
        }
        
        for (SachDTO s : sachList) {
            maSachCTPB_add.addItem(s.getMaSach());
            maSachCTPB_update.addItem(s.getMaSach());
        }
    }
    
    private JPanel getPopUpUpdateCTPB() {
        maPhieuBanCTPB_update.setFont(font_16_plain);
        maSachCTPB_update.setFont(font_16_plain);
        soLuongCTPB_update.setFont(font_16_plain);
        
        JLabel maPhieuBanCTPBLabel = new JLabel("Mã phiếu bán: ");
        maPhieuBanCTPBLabel.setFont(font_16_bold);
        
        JLabel maSachCTPBLabel = new JLabel("Mã sách: ");
        maSachCTPBLabel.setFont(font_16_bold);
        
        JLabel soLuongCTPBLabel = new JLabel("Số lượng: ");
        soLuongCTPBLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel maPhieuBanCTPBPanel = new JPanel();
        JPanel maSachCTPBPanel = new JPanel();
        JPanel soLuongCTPBPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        maPhieuBanCTPBPanel.setLayout(new BorderLayout());
        maSachCTPBPanel.setLayout(new BorderLayout());
        soLuongCTPBPanel.setLayout(new BorderLayout());
        
        maPhieuBanCTPBPanel.add(maPhieuBanCTPBLabel, BorderLayout.NORTH);
        maPhieuBanCTPBPanel.add(maPhieuBanCTPB_update, BorderLayout.CENTER);
        
        maSachCTPBPanel.add(maSachCTPBLabel, BorderLayout.NORTH);
        maSachCTPBPanel.add(maSachCTPB_update, BorderLayout.CENTER);
        
        soLuongCTPBPanel.add(soLuongCTPBLabel, BorderLayout.NORTH);
        soLuongCTPBPanel.add(soLuongCTPB_update, BorderLayout.CENTER);
        
        containerPanel.add(maPhieuBanCTPBPanel);
        containerPanel.add(maSachCTPBPanel);
        containerPanel.add(soLuongCTPBPanel);
        
        return containerPanel;
    }
    
    private JPanel getPopUpAddCTPB() {
        maPhieuBanCTPB_add.setFont(font_16_plain);
        maSachCTPB_add.setFont(font_16_plain);
        soLuongCTPB_add.setFont(font_16_plain);
        
        JLabel maPhieuBanCTPBLabel = new JLabel("Mã phiếu bán: ");
        maPhieuBanCTPBLabel.setFont(font_16_bold);
        
        JLabel maSachCTPBLabel = new JLabel("Mã sách: ");
        maSachCTPBLabel.setFont(font_16_bold);
        
        JLabel soLuongCTPBLabel = new JLabel("Số lượng: ");
        soLuongCTPBLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel maPhieuBanCTPBPanel = new JPanel();
        JPanel maSachCTPBPanel = new JPanel();
        JPanel soLuongCTPBPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        maPhieuBanCTPBPanel.setLayout(new BorderLayout());
        maSachCTPBPanel.setLayout(new BorderLayout());
        soLuongCTPBPanel.setLayout(new BorderLayout());
        
        maPhieuBanCTPBPanel.add(maPhieuBanCTPBLabel, BorderLayout.NORTH);
        maPhieuBanCTPBPanel.add(maPhieuBanCTPB_add, BorderLayout.CENTER);
        
        maSachCTPBPanel.add(maSachCTPBLabel, BorderLayout.NORTH);
        maSachCTPBPanel.add(maSachCTPB_add, BorderLayout.CENTER);
        
        soLuongCTPBPanel.add(soLuongCTPBLabel, BorderLayout.NORTH);
        soLuongCTPBPanel.add(soLuongCTPB_add, BorderLayout.CENTER);
        
        containerPanel.add(maPhieuBanCTPBPanel);
        containerPanel.add(maSachCTPBPanel);
        containerPanel.add(soLuongCTPBPanel);
        
        return containerPanel;
    }
    
    private JPanel getPopUpUpdatePB() {
        maKhachHangPB.setFont(font_16_plain);
        maNhanVienPB.setFont(font_16_plain);
        ngayLapPB.setFont(font_16_plain);
        
        JLabel maKhachHangPBLabel = new JLabel("Mã khách hàng: ");
        maKhachHangPBLabel.setFont(font_16_bold);
        
        JLabel maNhanVienPBLabel = new JLabel("Mã nhân viên: ");
        maNhanVienPBLabel.setFont(font_16_bold);
        
        JLabel ngayLapPBLabel = new JLabel("Ngày lập: ");
        ngayLapPBLabel.setFont(font_16_bold);
        
        JLabel maKhuyenMaiPBLabel = new JLabel("Mã khuyến mãi: ");
        maKhuyenMaiPBLabel.setFont(font_16_bold);
        
        
        JPanel containerPanel = new JPanel();
        JPanel maKhachHangPBPanel = new JPanel();
        JPanel maNhanVienPBPanel = new JPanel();
        JPanel ngayLapPBPanel = new JPanel();
        JPanel maKhuyenMaiPBPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        maKhachHangPBPanel.setLayout(new BorderLayout());
        maNhanVienPBPanel.setLayout(new BorderLayout());
        ngayLapPBPanel.setLayout(new BorderLayout());
        maKhuyenMaiPBPanel.setLayout(new BorderLayout());
        
        maKhachHangPBPanel.add(maKhachHangPBLabel, BorderLayout.NORTH);
        maKhachHangPBPanel.add(maKhachHangPB, BorderLayout.CENTER);
        
        maNhanVienPBPanel.add(maNhanVienPBLabel, BorderLayout.NORTH);
        maNhanVienPBPanel.add(maNhanVienPB, BorderLayout.CENTER);
        
        ngayLapPBPanel.add(ngayLapPBLabel, BorderLayout.NORTH);
        ngayLapPBPanel.add(ngayLapPB, BorderLayout.CENTER);
        
        maKhuyenMaiPBPanel.add(maKhuyenMaiPBLabel, BorderLayout.NORTH);
        maKhuyenMaiPBPanel.add(maKhuyenMaiPB, BorderLayout.CENTER);
        
        containerPanel.add(maKhachHangPBPanel);
        containerPanel.add(maNhanVienPBPanel);
        containerPanel.add(ngayLapPBPanel);
        containerPanel.add(maKhuyenMaiPBPanel);
        
        return containerPanel;
    }
    
    private int checkInputNumberValue(String value, String name) {
        int num;
        if (value == null) return -1;
        
        try {
            
            num = Integer.parseInt(value);
            if (num > 0) {
                return num;
            } else {
                JOptionPane.showMessageDialog(this, name + " là một số không âm","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, name + " là một số nguyên dương","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        return -1;
    }
    
    private boolean validateValueUpdatePB() {
        String maKhachHang = String.valueOf(maKhachHangPB.getSelectedItem());
        String maNhanVien = String.valueOf(maNhanVienPB.getSelectedItem());
        
        if ("".equals(maKhachHang) || "".equals(maNhanVien)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void updatePBTable() {
        removeRowTable(PBTable);
        
        setPBTable();
    }
    
    private void removeRowTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    
    private void showComfirmRemovePB(int row) {
        DefaultTableModel modelPB = (DefaultTableModel) PBTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            int ma = Integer.parseInt(String.valueOf(modelPB.getValueAt(row, 0)));
            modelPB.removeRow(row);
            phieuBanBLL.delete(ma);
            updateCTPBTable();
        }
    }
    
    private void setPBTable() {
        ArrayList<PhieuBanDTO> PBList = phieuBanBLL.getAll();
        int maPhieuBan;
        int maKhachHang;
        int maNhanVien;
        Date ngayLap;
        int maKhuyenMai;
        double tongTien;
        
        DefaultTableModel modelPB = (DefaultTableModel) PBTable.getModel();
        
        for (PhieuBanDTO pb : PBList) {
            maPhieuBan = pb.getMaPhieuBan();
            maKhachHang = pb.getMaKhachHang();
            maNhanVien = pb.getMaNhanVien();
            ngayLap = pb.getNgayLap();
            tongTien = pb.getTongTien();
            maKhuyenMai = pb.getMaKhuyenMai();
            
            modelPB.addRow(new Object[]{maPhieuBan, maKhachHang, maNhanVien, ngayLap, tongTien, maKhuyenMai, "O", "X"});
        }
    }
    
    private void addEventPBTable() {
        PBTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = PBTable.rowAtPoint(evt.getPoint());
                int col = PBTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 6) {
                    try {
                        int maPhieuBan =  Integer.parseInt(String.valueOf(PBTable.getValueAt(row, 0)));
                        int maKhachHang = Integer.parseInt(String.valueOf(PBTable.getValueAt(row, 1)));
                        int maNhanVien = Integer.parseInt(String.valueOf(PBTable.getValueAt(row, 2)));
                        String ngayLap = String.valueOf(PBTable.getValueAt(row, 3));
                        double tongTien = Double.parseDouble(String.valueOf(PBTable.getValueAt(row, 4)));
                        int maKhuyenMaiCu = Integer.parseInt(String.valueOf(PBTable.getValueAt(row, 5)));
                        
                        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(ngayLap);
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        
                        maKhachHangPB.setSelectedItem(maKhachHang);
                        maNhanVienPB.setSelectedItem(maNhanVien);
                        ngayLapPB.setDate(utilDate);
                        maKhuyenMaiPB.setSelectedItem(maKhuyenMaiCu);
                        
                        int result = JOptionPane.showConfirmDialog(null, popUpUpdatePB,
                                "Mời sửa phiếu bán " + maPhieuBan, JOptionPane.OK_CANCEL_OPTION);
                        
                        if (result == JOptionPane.OK_OPTION) {
                            if (validateValueUpdatePB() == false) return;
                            
                            maKhachHang = Integer.parseInt(String.valueOf(maKhachHangPB.getSelectedItem()));
                            maNhanVien = Integer.parseInt(String.valueOf(maNhanVienPB.getSelectedItem()));
                            utilDate = ngayLapPB.getDate();
                            sqlDate = new java.sql.Date(utilDate.getTime());
                            int maKhuyenMaiMoi = 0;
                            
                            if (!"".equals(String.valueOf(maKhuyenMaiPB.getSelectedItem()))) {
                                maKhuyenMaiMoi = Integer.parseInt(String.valueOf(maKhuyenMaiPB.getSelectedItem()));
                            }
                            
                            double tongTienBanDau = tongTien + (tongTien * maKhuyenMaiCu / 100);
                            double tongTienMoi = tongTienBanDau - (tongTienBanDau * maKhuyenMaiMoi / 100);
                            
                            PhieuBanDTO pb = new PhieuBanDTO(maPhieuBan, maKhachHang, maNhanVien, sqlDate, tongTienMoi, maKhuyenMaiMoi);
                            
                            phieuBanBLL.update(pb);
                            
                            updatePBTable();
                            
                            maKhachHangPB.setSelectedItem("");
                            maNhanVienPB.setSelectedItem("");
                            ngayLapPB.setDate(null);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(SellInvoiceGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (row >= 0 && col == 7) {
                    showComfirmRemovePB(row);
                }
                
                if (row >= 0 && col != 6 && col != 7) {
                    int maPhieuBan =  Integer.parseInt(String.valueOf(PBTable.getValueAt(row, 0)));
                    
                    ArrayList<ChiTietPhieuBanDTO> ctpbList = chiTietPhieuBanBLL.getByPBId(maPhieuBan);
                    removeRowTable(CTPBTable);
                    DefaultTableModel modelCTPB = (DefaultTableModel) CTPBTable.getModel();
                    for (ChiTietPhieuBanDTO ctpb : ctpbList) {
                        maPhieuBan = ctpb.getMaPhieuBan();
                        int maSach = ctpb.getMaSach();
                        int soLuong = ctpb.getSoLuong();
                        long donGia = ctpb.getDonGia();

                        modelCTPB.addRow(new Object[]{maPhieuBan, maSach, soLuong, donGia, "O"});
                    }
                }
            }
        });
    }
    
    private boolean validateValueUpdateCTPB() {
        String maSach = String.valueOf(maSachCTPB_update.getSelectedItem());
        String soLuong = soLuongCTPB_update.getText();
        
        if ("".equals(maSach) || "".equals(soLuong)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int soLuongNum = checkInputNumberValue(soLuong, "Số lượng");
        return soLuongNum != -1;
    }
    
    private void updateCTPBTable() {
        removeRowTable(CTPBTable);
        
        setCTPBTable();
    }
    
    private void setCTPBTable() {
        ArrayList<ChiTietPhieuBanDTO> CTPBList = chiTietPhieuBanBLL.getAll();
        int maPhieuBan;
        int maSach;
        int soLuong;
        long donGia;
        
        DefaultTableModel modelCTPB = (DefaultTableModel) CTPBTable.getModel();
        
        for (ChiTietPhieuBanDTO ctpb : CTPBList) {
            maPhieuBan = ctpb.getMaPhieuBan();
            maSach = ctpb.getMaSach();
            soLuong = ctpb.getSoLuong();
            donGia = ctpb.getDonGia();
            
            modelCTPB.addRow(new Object[]{maPhieuBan, maSach, soLuong, donGia, "O"});
        }
        
        
    }
    
    private void addEventCTPBTable() {
        CTPBTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = CTPBTable.rowAtPoint(evt.getPoint());
                int col = CTPBTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 4) {
                    int maPhieuBan =  Integer.parseInt(String.valueOf(CTPBTable.getValueAt(row, 0)));
                    int maSach = Integer.parseInt(String.valueOf(CTPBTable.getValueAt(row, 1)));
                    int soLuong = Integer.parseInt(String.valueOf(CTPBTable.getValueAt(row, 2)));

                    maPhieuBanCTPB_update.setSelectedItem(maPhieuBan);
                    maSachCTPB_update.setSelectedItem(maSach);
                    soLuongCTPB_update.setText(soLuong + "");
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateCTPB, 
                                "Mời sửa chi tiết phiếu bán", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdateCTPB() == false) return;
                        
                        int maPhieuBanMoi = Integer.parseInt(String.valueOf(maPhieuBanCTPB_update.getSelectedItem()));
                        int maSachMoi = Integer.parseInt(String.valueOf(maSachCTPB_update.getSelectedItem()));
                        int soLuongMoi = Integer.parseInt(soLuongCTPB_update.getText());
                        ChiTietPhieuBanDTO ctpb = new ChiTietPhieuBanDTO();
                        
                        SachDTO sMoi = sachBLL.getById(maSachMoi);
                        SachDTO sCu = sachBLL.getById(maSach);
                        
                        if (maPhieuBanMoi != maPhieuBan) {
                            ctpb.setMaPhieuBan(maPhieuBanMoi);
                        } else {
                            ctpb.setMaPhieuBan(maPhieuBan);
                        }
                        
                        if (maSachMoi != maSach) {
                            if (sMoi.getSoLuongConLai() - soLuongMoi < 0) {
                                JOptionPane.showMessageDialog(rootPane, "Sách có mã " + maSachMoi + " chỉ còn lại " + sMoi.getSoLuongConLai() + " sách","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                            ctpb.setMaSach(maSachMoi);
                            ctpb.setDonGia(sMoi.getGiaBan());
                            sCu.setSoLuongConLai(sCu.getSoLuongConLai() + soLuong);
                            sMoi.setSoLuongConLai(sMoi.getSoLuongConLai() - soLuongMoi);
                            sachBLL.update(sMoi);
                        } else {
                            ctpb.setMaSach(maSach);
                            ctpb.setDonGia(sCu.getGiaBan());
                            sCu.setSoLuongConLai(sCu.getSoLuongConLai() + soLuong - soLuongMoi);
                            sachBLL.update(sCu);
                        }
                        
                        ctpb.setSoLuong(soLuongMoi);
                        
                        chiTietPhieuBanBLL.update(ctpb, maPhieuBan, maSach);
                        
                        updateCTPBTable();
                        
                        double tongTien = 0;
                        
                        ArrayList<ChiTietPhieuBanDTO> ctpnList = chiTietPhieuBanBLL.getByPBId(maPhieuBanMoi);
                        for (ChiTietPhieuBanDTO ct : ctpnList) {
                            tongTien += ct.getDonGia() * ct.getSoLuong();
                        }
                        
                        PhieuBanDTO pb = phieuBanBLL.getById(maPhieuBanMoi);
                        
                        int maKhuyenMai = pb.getMaKhuyenMai();
                        KhuyenMaiDTO km = khuyenMaiBLL.getById(maKhuyenMai);
                        
                        int phanTram = 0;
                        
                        if (km != null) {
                            phanTram = km.getPhanTram();
                        }
                        
                        tongTien = tongTien - (tongTien * phanTram / 100);
                        pb.setTongTien(tongTien);
                        phieuBanBLL.update(pb);

                        updatePBTable();
                        
                        maPhieuBanCTPB_update.setSelectedItem("");
                        maSachCTPB_update.setSelectedItem("");
                        soLuongCTPB_update.setText("");
                    }
                }
            }
        });
    }
    
    public SellInvoiceGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        PBTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        PBTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        
        CTPBTable.getColumnModel().getColumn(3).setCellRenderer(new CurrencyTableCellRenderer());
        CTPBTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setPBTable();
        addEventPBTable();
        
        setCTPBTable();
        addEventCTPBTable();
        
        setJComboBox();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        inputCTPBId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addCTPBBtn = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CTPBTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        inputPBId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        PBTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        addPBBtn = new javax.swing.JLabel();
        searchCbbox = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        print = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();
        exportExcel = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        dateTimeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phiếu Bán và Chi Tiết Phiếu Bán");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        inputCTPBId.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputCTPBId.setForeground(new java.awt.Color(102, 102, 102));
        inputCTPBId.setText("Nhập mã phiếu bán");
        inputCTPBId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCTPBIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCTPBIdFocusLost(evt);
            }
        });
        inputCTPBId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCTPBIdKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Chi Tiết Phiếu Bán :");

        addCTPBBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/add.png"))); // NOI18N
        addCTPBBtn.setToolTipText("Thêm khách hàng");
        addCTPBBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCTPBBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCTPBBtnMouseClicked(evt);
            }
        });

        CTPBTable.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        CTPBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Bán", "Mã Sách", "Số Lượng", "Đơn Giá", "Sửa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CTPBTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputCTPBId, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(addCTPBBtn)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputCTPBId, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(addCTPBBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputPBId.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputPBId.setForeground(new java.awt.Color(102, 102, 102));
        inputPBId.setText("Nhập mã phiếu bán");
        inputPBId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPBIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPBIdFocusLost(evt);
            }
        });
        inputPBId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputPBIdKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Phiếu Bán :");

        PBTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        PBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Bán", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Lập", "Tổng tiền", "Mã Khuyến Mãi", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PBTable.setEditingColumn(1);
        PBTable.setEditingRow(1);
        jScrollPane3.setViewportView(PBTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        addPBBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/add.png"))); // NOI18N
        addPBBtn.setToolTipText("Thêm khách hàng");
        addPBBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPBBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPBBtnMouseClicked(evt);
            }
        });

        searchCbbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu bán", "Mã khách hàng", "Mã nhân viên" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPBId)
                .addGap(18, 18, 18)
                .addComponent(searchCbbox, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addPBBtn)
                .addGap(16, 16, 16))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(inputPBId, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchCbbox))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addPBBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 153));

        print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/pdf.png"))); // NOI18N
        print.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printMouseClicked(evt);
            }
        });

        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtnMouseClicked(evt);
            }
        });

        exportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/import-excel.png"))); // NOI18N
        exportExcel.setToolTipText("Xuất Excel");
        exportExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportExcelMouseClicked(evt);
            }
        });

        infoUser.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        infoUser.setForeground(new java.awt.Color(51, 51, 51));
        infoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUser.setText("NV1 - Biện Thành Hưng");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(infoUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(print)
                .addGap(35, 35, 35)
                .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
            .addComponent(infoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(print, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 153));

        dateTimeLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeLabel.setText("10/1/2003 - 22:00:00 PM");

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/back.png"))); // NOI18N
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(backBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
            .addComponent(dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtnMouseClicked
        this.dispose();
        new LoginGUI();
    }//GEN-LAST:event_logoutBtnMouseClicked

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        this.dispose();
        NhanVienDTO nv = new NhanVienBLL().getByNVid(tk.getMaNhanVien());
            
        switch (nv.getVaiTro()) {
            case "Quản lý" -> new ManagerMenuGUI(tk).setVisible(true);
            case "Nhân viên bán hàng" -> new SellEmployeeMenuGUI(tk).setVisible(true);
            case "Nhân viên nhập hàng" -> new ImportEmployeeMenuGUI(tk).setVisible(true);
        }
    }//GEN-LAST:event_backBtnMouseClicked

    private void inputPBIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPBIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int searchType = searchCbbox.getSelectedIndex();
            String value = inputPBId.getText();
            int maPhieuBan;
            int maKhachHang;
            int maNhanVien;
            Date ngayLap;
            
            ArrayList<PhieuBanDTO> PBList = null;
            DefaultTableModel modelPB = (DefaultTableModel) PBTable.getModel();
            modelPB.setRowCount(0);
            
            switch (searchType) {
                case 0 -> {
                    PBList = phieuBanBLL.getByCondition("maPhieuBan LIKE '%" + value.toUpperCase() + "%'");
                }
                case 1 -> {
                    PBList = phieuBanBLL.getByCondition("maKhachHang LIKE '%" + value.toUpperCase() + "%'");
                }
                case 2 -> {
                    PBList = phieuBanBLL.getByCondition("maNhanVien LIKE '%" + value.toUpperCase() + "%'");
                }
            }

            if (PBList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setPBTable();
            } else {
                for (PhieuBanDTO pb : PBList) {
                    maPhieuBan = pb.getMaPhieuBan();
                    maKhachHang = pb.getMaKhachHang();
                    maNhanVien = pb.getMaNhanVien();
                    ngayLap = pb.getNgayLap();
                    
                    modelPB.addRow(new Object[]{maPhieuBan, maKhachHang, maNhanVien, ngayLap, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputPBIdKeyPressed

    private boolean validateValueAddCTPB() {
        String maPhieuBan = (String) maPhieuBanCTPB_add.getSelectedItem();
        String maSach = (String) maSachCTPB_add.getSelectedItem();
        String soLuong = soLuongCTPB_add.getText();
        
        if ("".equals(maSach) || "".equals(soLuong) || "".equals(maPhieuBan)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int soLuongNum = checkInputNumberValue(soLuong, "Số lượng");
        return soLuongNum != -1;
    }
    
    private void addCTPBBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCTPBBtnMouseClicked
        int result = JOptionPane.showConfirmDialog(null, popUpAddCTPB, 
               "Mời nhập dữ liệu chi tiết phiếu bán mới", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            if (validateValueAddCTPB() == false) return;
            
            int maPhieuBan = Integer.parseInt(String.valueOf(maPhieuBanCTPB_add.getSelectedItem()));
            int maSach = Integer.parseInt(String.valueOf(maSachCTPB_add.getSelectedItem()));
            int soLuong = Integer.parseInt(soLuongCTPB_add.getText());
            long donGia = sachBLL.getById(maSach).getGiaBan();
            
            
            ChiTietPhieuBanDTO ctpb = new ChiTietPhieuBanDTO(maPhieuBan, maSach, soLuong, donGia);
            
            if (chiTietPhieuBanBLL.insert(ctpb)) {
                maPhieuBanCTPB_add.setSelectedItem("");
                maSachCTPB_add.setSelectedItem("");
                soLuongCTPB_add.setText("");
            }
        }

    }//GEN-LAST:event_addCTPBBtnMouseClicked

    private void inputPBIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPBIdFocusGained
        inputPBId.setText("");
        inputPBId.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputPBIdFocusGained

    private void inputPBIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPBIdFocusLost
        inputPBId.setText("Nhập mã phiếu bán");
        inputPBId.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputPBIdFocusLost

    private void inputCTPBIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCTPBIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String value = inputCTPBId.getText();
            if ("".equals(value)) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setCTPBTable();
                return;
            }
            
            try {
                int maPhieuBan;
                int maSach;
                int soLuong;
                long donGia;

                ArrayList<ChiTietPhieuBanDTO> ctpbList = chiTietPhieuBanBLL.getByPBId(Integer.parseInt(value));
                DefaultTableModel modelCTPB = (DefaultTableModel) CTPBTable.getModel();
                modelCTPB.setRowCount(0);

                if (ctpbList.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    setCTPBTable();
                } else {
                    for (ChiTietPhieuBanDTO ctpb : ctpbList) {
                        maPhieuBan = ctpb.getMaPhieuBan();
                        maSach = ctpb.getMaSach();
                        soLuong = ctpb.getSoLuong();
                        donGia = ctpb.getDonGia();

                        modelCTPB.addRow(new Object[]{maPhieuBan, maSach, soLuong, donGia, "O", "X"});
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setCTPBTable();
            }
        }
    }//GEN-LAST:event_inputCTPBIdKeyPressed

    private void inputCTPBIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCTPBIdFocusLost
        inputCTPBId.setText("Nhập mã phiếu bán");
        inputCTPBId.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputCTPBIdFocusLost

    private void inputCTPBIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCTPBIdFocusGained
        inputCTPBId.setText("");
        inputCTPBId.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputCTPBIdFocusGained

    private void addPBBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPBBtnMouseClicked
        this.dispose();
        new SellBookGUI(tk);
    }//GEN-LAST:event_addPBBtnMouseClicked

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<PhieuBanDTO> pbList = phieuBanBLL.getAll();
        ArrayList<ChiTietPhieuBanDTO> ctpbList = chiTietPhieuBanBLL.getAll();
        
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("phieuban");
            XSSFSheet sheet1 = workbook.createSheet("chitietphieuban");
            
            XSSFRow row = null;
            XSSFCell cell = null;
            
            row = sheet.createRow(0);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã phiếu bán");
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã nhân viên");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày lập");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng tiền");
            
            int i = 1;
            for (PhieuBanDTO pb : pbList) {
                row = sheet.createRow(0 + i);
                
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(pb.getMaPhieuBan());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(pb.getMaKhachHang());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(pb.getMaNhanVien());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(pb.getNgayLap().toString());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(pb.getTongTien());
                
                i++;
            }
            
            
            row = null;
            cell = null;
            
            row = sheet1.createRow(0);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã phiếu bán");
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã sách");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số lượng");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Đơn giá");
            
            i = 1;
            for (ChiTietPhieuBanDTO ctpb : ctpbList) {
                row = sheet1.createRow(0 + i);
                
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(ctpb.getMaPhieuBan());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(ctpb.getMaSach());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(ctpb.getSoLuong());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(ctpb.getDonGia());
                
                i++;
            }
            
            File f = new File("D://phieunhapvachitietphieunhap.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                
                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/phieunhapvachitietphieunhap.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcelMouseClicked

    private void printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseClicked
        if (PBTable.getSelectedRow() != -1) {
            new PrintPDF().writeHoaDon(Integer.parseInt(String.valueOf(PBTable.getValueAt(PBTable.getSelectedRow(), 0))));
            JOptionPane.showMessageDialog(null, "In hóa đơn thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để in","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_printMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CTPBTable;
    private javax.swing.JTable PBTable;
    private javax.swing.JLabel addCTPBBtn;
    private javax.swing.JLabel addPBBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputCTPBId;
    private javax.swing.JTextField inputPBId;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JLabel print;
    private javax.swing.JComboBox<String> searchCbbox;
    // End of variables declaration//GEN-END:variables
}
