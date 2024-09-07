/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.ChiTietPhieuBanBLL;
import BLL.ChiTietPhieuNhapBLL;
import BLL.NhanVienBLL;
import BLL.PhieuBanBLL;
import BLL.PhieuNhapBLL;
import DTO.ChiTietPhieuBanDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuBanDTO;
import DTO.PhieuNhapDTO;
import DTO.TaiKhoanDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
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
public final class StatiticsGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private PhieuBanBLL phieuBanBLL = new PhieuBanBLL();
    private PhieuNhapBLL phieuNhapBLL = new PhieuNhapBLL();
    private ChiTietPhieuBanBLL chiTietPhieuBanBLL = new ChiTietPhieuBanBLL();
    private ChiTietPhieuNhapBLL chiTietPhieuNhapBLL = new ChiTietPhieuNhapBLL();
    private ArrayList<PhieuBanDTO> PBList = phieuBanBLL.getAll();
    private ArrayList<PhieuNhapDTO> PNList = phieuNhapBLL.getAll();

    private Locale lc = new Locale("nv","VN"); //Định dạng locale việt nam
    private NumberFormat nf = NumberFormat.getInstance(lc);
    
    private long totalSell = 0;
    private long totalImport = 0;
    private int totalSellBook = 0;
    private int totalImportBook = 0;
    
    
    private void setImportBookTable() {
        int maPhieuNhap;
        int maNhanVien;
        int maNhaCungCap;
        Date ngayLap;
        double tongTien;
        
        totalImport = 0;
        totalImportBook = 0;
        
        DefaultTableModel modelImportBookTable = (DefaultTableModel) importBookTable.getModel();
        
        for (PhieuNhapDTO pn : PNList) {
            maPhieuNhap = pn .getMaPhieuNhap();
            maNhanVien = pn.getMaNhanVien();
            maNhaCungCap = pn.getMaNhaCungCap();
            ngayLap = pn.getNgayLap();
            tongTien = pn.getTongTien();
            
            ArrayList<ChiTietPhieuNhapDTO> ctpnList = chiTietPhieuNhapBLL.getByPNId(maPhieuNhap);
            
            for (ChiTietPhieuNhapDTO ctpn : ctpnList) {
                int soLuong = ctpn.getSoLuong();
                
                totalImportBook += soLuong;
            }
            
            totalImport += tongTien;
            
            modelImportBookTable.addRow(new Object[]{maPhieuNhap, maNhanVien, maNhaCungCap, ngayLap, tongTien});
        }
        
        totalImportBookLabel.setText(totalImportBook + " sách");
        totalImportPay.setText(totalImport + " VNĐ");
    }
   
    
    private void setSellBookTable() {
        int maPhieuBan;
        int maKhachHang;
        int maNhanVien;
        int maKhuyenMai;
        Date ngayLap;
        double tongTien;
        
        totalSellBook = 0;
        totalSell = 0;
        
        DefaultTableModel modelSellBookTable = (DefaultTableModel) sellBookTable.getModel();
        
        for (PhieuBanDTO pb : PBList) {
            maPhieuBan = pb .getMaPhieuBan();
            maKhachHang = pb.getMaKhachHang();
            maNhanVien = pb.getMaNhanVien();
            maKhuyenMai = pb.getMaKhuyenMai();
            ngayLap = pb.getNgayLap();
            tongTien = pb.getTongTien();
            
            modelSellBookTable.addRow(new Object[]{maPhieuBan, maKhachHang, maNhanVien, maKhuyenMai, ngayLap, tongTien});
            
            ArrayList<ChiTietPhieuBanDTO> ctpbList = chiTietPhieuBanBLL.getByPBId(maPhieuBan);
            
            for (ChiTietPhieuBanDTO ctpb : ctpbList) {
                int soLuong = ctpb.getSoLuong();
                
                totalSellBook += soLuong;
            }
            
            totalSell += tongTien;
        }
        
        totalSellBookLabel.setText(totalSellBook + " sách");
        totalSellPay.setText(totalSell + " VNĐ");
    }
    
    
    public StatiticsGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        
        importBookTable.getColumnModel().getColumn(4).setCellRenderer(new CurrencyTableCellRenderer());
        
        sellBookTable.getColumnModel().getColumn(5).setCellRenderer(new CurrencyTableCellRenderer());
        
        setImportBookTable();
        
        setSellBookTable();
        
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
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        importBookTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel8 = new javax.swing.JLabel();
        importFrom = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        importTo = new com.toedter.calendar.JDateChooser();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalImportPay = new javax.swing.JLabel();
        totalImportBookLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        sellBookTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sellFrom = new com.toedter.calendar.JDateChooser();
        sellTo = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        totalSellPay = new javax.swing.JLabel();
        totalSellBookLabel = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        logoutBtn = new javax.swing.JLabel();
        exportExcel = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        dateTimeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thống Kê");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Thống Kê Nhập Sách");

        importBookTable.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        importBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập", "Mã Nhân Viên", "Mã Nhà Cung Cấp", "Ngày Lập", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
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
        jScrollPane1.setViewportView(importBookTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel8.setText("Từ :");

        importFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                importFromPropertyChange(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel9.setText("Đến :");

        importTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                importToPropertyChange(evt);
            }
        });

        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 153));
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 153, 0));

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel3.setText("Tổng số sách đã nhập  :");

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel5.setText("Tổng tiền             :");

        totalImportPay.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        totalImportPay.setText("0 VNĐ");

        totalImportBookLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        totalImportBookLabel.setText("0");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalImportPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalImportBookLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(totalImportBookLabel))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(totalImportPay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importTo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(importFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(importTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        sellBookTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        sellBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Bán", "Mã Khách Hàng", "Mã Nhân Viên", "Mã Khuyến Mãi", "Ngày Lập", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sellBookTable.setEditingColumn(1);
        sellBookTable.setEditingRow(1);
        jScrollPane3.setViewportView(sellBookTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Thống Kê Bán Sách");

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel10.setText("Đến :");

        sellFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                sellFromPropertyChange(evt);
            }
        });

        sellTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                sellToPropertyChange(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel11.setText("Từ :");

        kGradientPanel4.setkEndColor(new java.awt.Color(255, 255, 153));
        kGradientPanel4.setkStartColor(new java.awt.Color(255, 153, 0));

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel4.setText("Tổng số sách đã bán   :");

        jLabel24.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel24.setText("Tổng tiền             :");

        totalSellPay.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        totalSellPay.setText("0 VNĐ");

        totalSellBookLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        totalSellBookLabel.setText("0");

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalSellBookLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalSellPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalSellBookLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(totalSellPay))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellTo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(kGradientPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sellFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sellTo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 153));

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
                .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addComponent(infoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void importFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_importFromPropertyChange
        Date dateFrom = importFrom.getDate();
        Date dateTo = importTo.getDate();
        if (dateFrom == null || dateTo == null) return;
        
        if (dateFrom.compareTo(dateTo) > 0) return;
        
        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
        
        DefaultTableModel modelImportBookTable = (DefaultTableModel) importBookTable.getModel();
        PNList = phieuNhapBLL.getByCondition("ngayLap between '" + sqlDateFrom.toString() + "' AND '" + sqlDateTo.toString() +"'");
        modelImportBookTable.setRowCount(0);
        
        setImportBookTable();
    }//GEN-LAST:event_importFromPropertyChange

    private void importToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_importToPropertyChange
        Date dateFrom = importFrom.getDate();
        Date dateTo = importTo.getDate();
        if (dateFrom == null || dateTo == null) return;
        
        if (dateTo.compareTo(dateFrom) < 0) return;
        
        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
        
        DefaultTableModel modelImportBookTable = (DefaultTableModel) importBookTable.getModel();
        PNList = phieuNhapBLL.getByCondition("ngayLap between '" + sqlDateFrom.toString() + "' AND '" + sqlDateTo.toString() +"'");
        modelImportBookTable.setRowCount(0);
        
        setImportBookTable();
    }//GEN-LAST:event_importToPropertyChange

    private void sellFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sellFromPropertyChange
        Date dateFrom = sellFrom.getDate();
        Date dateTo = sellTo.getDate();
        if (dateFrom == null || dateTo == null) return;
        
        if (dateFrom.compareTo(dateTo) > 0) return;
        
        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
        
        DefaultTableModel modelSellBookTable = (DefaultTableModel) sellBookTable.getModel();
        PBList = phieuBanBLL.getByCondition("ngayLap between '" + sqlDateFrom.toString() + "' AND '" + sqlDateTo.toString() +"'");
        modelSellBookTable.setRowCount(0);
        
        setSellBookTable();
    }//GEN-LAST:event_sellFromPropertyChange

    private void sellToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sellToPropertyChange
        Date dateFrom = sellFrom.getDate();
        Date dateTo = sellTo.getDate();
        
        if (dateFrom == null || dateTo == null) return;
        
        if (dateTo.compareTo(dateFrom) < 0) return;
        
        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
        
        DefaultTableModel modelSellBookTable = (DefaultTableModel) sellBookTable.getModel();
        PBList = phieuBanBLL.getByCondition("ngayLap between '" + sqlDateFrom.toString() + "' AND '" + sqlDateTo.toString() +"'");
        modelSellBookTable.setRowCount(0);
        
        setSellBookTable();
    }//GEN-LAST:event_sellToPropertyChange

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<PhieuBanDTO> pbList = phieuBanBLL.getAll();
        int maPhieuBan;
        int maPhieuNhap;
        int maNhaCungCap;
        int maKhachHang;
        int maNhanVien;
        int maKhuyenMai;
        Date ngayLap;
        double tongTien;
        
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("thongkephieuban");
            XSSFSheet sheet1 = workbook.createSheet("thongkephieunhap");
            
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
            cell.setCellValue("Mã khuyến mãi");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày lập");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tổng tiền");
            
            int i = 1;
            
            for (PhieuBanDTO pb : PBList) {
                maPhieuBan = pb .getMaPhieuBan();
                maKhachHang = pb.getMaKhachHang();
                maNhanVien = pb.getMaNhanVien();
                maKhuyenMai = pb.getMaKhuyenMai();
                ngayLap = pb.getNgayLap();
                tongTien = pb.getTongTien();

                row = sheet.createRow(0 + i);
                
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(maPhieuBan);

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(maKhachHang);

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(maNhanVien);

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(maKhuyenMai);

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(ngayLap);

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(tongTien);

                i++;
            }
            
            row = null;
            cell = null;
            
            row = sheet1.createRow(0);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã phiếu nhập");
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã nhân viên");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã nhà cung cấp");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mã sách");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày lập");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Số lượng");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Đơn giá");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Thành tiền");
            
            i = 1;
            for (PhieuNhapDTO pn : PNList) {
                maPhieuNhap = pn .getMaPhieuNhap();
                maNhanVien = pn.getMaNhanVien();
                maNhaCungCap = pn.getMaNhaCungCap();
                ngayLap = pn.getNgayLap();
                tongTien = pn.getTongTien();

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(maPhieuNhap);

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(maNhanVien);

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(maNhaCungCap);

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(ngayLap);

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(tongTien);
                
                i++;
            }
            
            File f = new File("D://thongke.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                
                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/thongke.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcelMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JTable importBookTable;
    private com.toedter.calendar.JDateChooser importFrom;
    private com.toedter.calendar.JDateChooser importTo;
    private javax.swing.JLabel infoUser;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JTable sellBookTable;
    private com.toedter.calendar.JDateChooser sellFrom;
    private com.toedter.calendar.JDateChooser sellTo;
    private javax.swing.JLabel totalImportBookLabel;
    private javax.swing.JLabel totalImportPay;
    private javax.swing.JLabel totalSellBookLabel;
    private javax.swing.JLabel totalSellPay;
    // End of variables declaration//GEN-END:variables
}
