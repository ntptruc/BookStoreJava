/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.ChiTietPhieuNhapBLL;
import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import BLL.PhieuNhapBLL;
import BLL.PrintPDF;
import BLL.SachBLL;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SachDTO;
import DTO.TaiKhoanDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
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
public final class ImportInvoiceGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private SachBLL sachBLL = new SachBLL();
    private PhieuNhapBLL phieuNhapBLL = new PhieuNhapBLL();
    private NhanVienBLL nhanVienBLL = new NhanVienBLL();
    private NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    private ChiTietPhieuNhapBLL chiTietPhieuNhapBLL = new ChiTietPhieuNhapBLL();
    
    private Locale lc = new Locale("nv","VN"); //Định dạng locale việt nam
    private NumberFormat nf = NumberFormat.getInstance(lc);
    private Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
    private Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
    
    private JComboBox maNhanVienPN = new JComboBox();
    private JComboBox maNhaCungCapPN = new JComboBox();
    private JDateChooser ngayLapPN = new JDateChooser();
    private JPanel popUpUpdatePN = getPopUpUpdatePN();
    
    private JComboBox maPhieuNhapCTPN_add = new JComboBox();
    private JComboBox maSachCTPN_add = new JComboBox();
    private JTextField soLuongCTPN_add = new JTextField();
    
    private JComboBox maPhieuNhapCTPN_update = new JComboBox();
    private JComboBox maSachCTPN_update = new JComboBox();
    private JTextField soLuongCTPN_update = new JTextField();
    
    private JPanel popUpAddCTPN = getPopUpAddCTPN();
    private JPanel popUpUpdateCTPN = getPopUpUpdateCTPN();
    
    private void setJComboBox() {
        ArrayList<NhanVienDTO> nhanVienList = nhanVienBLL.getAll();
        ArrayList<PhieuNhapDTO> phieuNhapList = phieuNhapBLL.getAll();
        ArrayList<SachDTO> sachList = sachBLL.getAllSach();
        ArrayList<NhaCungCapDTO> nhaCungCapList = nhaCungCapBLL.getAll();
        
        maPhieuNhapCTPN_update.addItem("");
        maPhieuNhapCTPN_add.addItem("");
        
        maSachCTPN_update.addItem("");
        maSachCTPN_add.addItem("");
        
        maNhanVienPN.addItem("");
        maNhaCungCapPN.addItem("");
        
        maPhieuNhapCTPN_add.setSelectedIndex(0);
        maSachCTPN_add.setSelectedIndex(0);
        maNhanVienPN.setSelectedIndex(0);
        maNhaCungCapPN.setSelectedIndex(0);
        
        for (NhanVienDTO nv : nhanVienList) {
            maNhanVienPN.addItem(nv.getMaNhanVien());
        }
        
        for (NhaCungCapDTO ncc : nhaCungCapList) {
            maNhaCungCapPN.addItem(ncc.getMaNhaCungCap());
        }
        
        for (PhieuNhapDTO pn : phieuNhapList) {
            maPhieuNhapCTPN_add.addItem(pn.getMaPhieuNhap());
            maPhieuNhapCTPN_update.addItem(pn.getMaPhieuNhap());
        }
        
        for (SachDTO s : sachList) {
            maSachCTPN_add.addItem(s.getMaSach());
            maSachCTPN_update.addItem(s.getMaSach());
        }
    }
    
    private JPanel getPopUpUpdateCTPN() {
        maPhieuNhapCTPN_update.setFont(font_16_plain);
        maSachCTPN_update.setFont(font_16_plain);
        soLuongCTPN_update.setFont(font_16_plain);
        
        JLabel maPhieuNhapCTPNLabel = new JLabel("Mã phiếu nhập: ");
        maPhieuNhapCTPNLabel.setFont(font_16_bold);
        
        JLabel maSachCTPNLabel = new JLabel("Mã sách: ");
        maSachCTPNLabel.setFont(font_16_bold);
        
        JLabel soLuongCTPNLabel = new JLabel("Số lượng: ");
        soLuongCTPNLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel maPhieuNhapCTPNPanel = new JPanel();
        JPanel maSachCTPNPanel = new JPanel();
        JPanel soLuongCTPNPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        maPhieuNhapCTPNPanel.setLayout(new BorderLayout());
        maSachCTPNPanel.setLayout(new BorderLayout());
        soLuongCTPNPanel.setLayout(new BorderLayout());
        
        maPhieuNhapCTPNPanel.add(maPhieuNhapCTPNLabel, BorderLayout.NORTH);
        maPhieuNhapCTPNPanel.add(maPhieuNhapCTPN_update, BorderLayout.CENTER);
        
        maSachCTPNPanel.add(maSachCTPNLabel, BorderLayout.NORTH);
        maSachCTPNPanel.add(maSachCTPN_update, BorderLayout.CENTER);
        
        soLuongCTPNPanel.add(soLuongCTPNLabel, BorderLayout.NORTH);
        soLuongCTPNPanel.add(soLuongCTPN_update, BorderLayout.CENTER);
        
        containerPanel.add(maPhieuNhapCTPNPanel);
        containerPanel.add(maSachCTPNPanel);
        containerPanel.add(soLuongCTPNPanel);
        
        return containerPanel;
    }
    
    private JPanel getPopUpAddCTPN() {
        maPhieuNhapCTPN_add.setFont(font_16_plain);
        maSachCTPN_add.setFont(font_16_plain);
        soLuongCTPN_add.setFont(font_16_plain);
        
        JLabel maPhieuNhapCTPNLabel = new JLabel("Mã phiếu nhập: ");
        maPhieuNhapCTPNLabel.setFont(font_16_bold);
        
        JLabel maSachCTPNLabel = new JLabel("Mã sách: ");
        maSachCTPNLabel.setFont(font_16_bold);
        
        JLabel soLuongCTPNLabel = new JLabel("Số lượng: ");
        soLuongCTPNLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel maPhieuNhapCTPNPanel = new JPanel();
        JPanel maSachCTPNPanel = new JPanel();
        JPanel soLuongCTPNPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        maPhieuNhapCTPNPanel.setLayout(new BorderLayout());
        maSachCTPNPanel.setLayout(new BorderLayout());
        soLuongCTPNPanel.setLayout(new BorderLayout());
        
        maPhieuNhapCTPNPanel.add(maPhieuNhapCTPNLabel, BorderLayout.NORTH);
        maPhieuNhapCTPNPanel.add(maPhieuNhapCTPN_add, BorderLayout.CENTER);
        
        maSachCTPNPanel.add(maSachCTPNLabel, BorderLayout.NORTH);
        maSachCTPNPanel.add(maSachCTPN_add, BorderLayout.CENTER);
        
        soLuongCTPNPanel.add(soLuongCTPNLabel, BorderLayout.NORTH);
        soLuongCTPNPanel.add(soLuongCTPN_add, BorderLayout.CENTER);
        
        containerPanel.add(maPhieuNhapCTPNPanel);
        containerPanel.add(maSachCTPNPanel);
        containerPanel.add(soLuongCTPNPanel);
        
        return containerPanel;
    }
    
    private JPanel getPopUpUpdatePN() {
        maNhanVienPN.setFont(font_16_plain);
        maNhaCungCapPN.setFont(font_16_plain);
        ngayLapPN.setFont(font_16_plain);
        
        JLabel maNhanVienPNLabel = new JLabel("Mã nhân viên: ");
        maNhanVienPNLabel.setFont(font_16_bold);
        
        JLabel maNhaCungCapPNLabel = new JLabel("Mã nhà cung cấp: ");
        maNhaCungCapPNLabel.setFont(font_16_bold);
        
        JLabel ngayLapPNLabel = new JLabel("Ngày lập: ");
        ngayLapPNLabel.setFont(font_16_bold);
        
        
        JPanel containerPanel = new JPanel();
        JPanel maNhanVienPNPanel = new JPanel();
        JPanel maNhaCungCapPNPanel = new JPanel();
        JPanel ngayLapPNPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        maNhanVienPNPanel.setLayout(new BorderLayout());
        maNhaCungCapPNPanel.setLayout(new BorderLayout());
        ngayLapPNPanel.setLayout(new BorderLayout());

        maNhanVienPNPanel.add(maNhanVienPNLabel, BorderLayout.NORTH);
        maNhanVienPNPanel.add(maNhanVienPN, BorderLayout.CENTER);
        
        maNhaCungCapPNPanel.add(maNhaCungCapPNLabel, BorderLayout.NORTH);
        maNhaCungCapPNPanel.add(maNhaCungCapPN, BorderLayout.CENTER);
        
        ngayLapPNPanel.add(ngayLapPNLabel, BorderLayout.NORTH);
        ngayLapPNPanel.add(ngayLapPN, BorderLayout.CENTER);
        
        containerPanel.add(maNhanVienPNPanel);
        containerPanel.add(maNhaCungCapPNPanel);
        containerPanel.add(ngayLapPNPanel);
        
        return containerPanel;
    }
    
    private void removeRowTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
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
    
    private boolean validateValueUpdatePN() {
        String maNhanVien = String.valueOf(maNhanVienPN.getSelectedItem());
        String maNhaCungCap = String.valueOf(maNhaCungCapPN.getSelectedItem());
        
        if ("".equals(maNhanVien) || "".equals(maNhaCungCap)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void updatePNTable() {
        removeRowTable(PNTable);
        
        setPNTable();
    }
    
    private void showComfirmRemovePN(int row) {
        DefaultTableModel modelPN = (DefaultTableModel) PNTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            int ma = Integer.parseInt(String.valueOf(modelPN.getValueAt(row, 0)));
            modelPN.removeRow(row);
            phieuNhapBLL.delete(ma);
            updateCTPNTable();
        }
    }
    
    private void setPNTable() {
        ArrayList<PhieuNhapDTO> PNList = phieuNhapBLL.getAll();
        int maPhieuNhap;
        int maNhanVien;
        int maNhaCungCap;
        Date ngayLap;
        double tongTien;
        
        DefaultTableModel modelPN = (DefaultTableModel) PNTable.getModel();
        
        for (PhieuNhapDTO pn : PNList) {
            maPhieuNhap = pn.getMaPhieuNhap();
            maNhanVien = pn.getMaNhanVien();
            maNhaCungCap = pn.getMaNhaCungCap();
            ngayLap = pn.getNgayLap();
            tongTien = pn.getTongTien();
            
            modelPN.addRow(new Object[]{maPhieuNhap, maNhanVien, maNhaCungCap, ngayLap, tongTien, "O", "X"});
        }
        
        
    }
    
    private void addEventPNTable() {
        PNTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = PNTable.rowAtPoint(evt.getPoint());
                int col = PNTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 5) {
                    try {
                        int maPhieuNhap =  Integer.parseInt(String.valueOf(PNTable.getValueAt(row, 0)));
                        int maNhanVien = Integer.parseInt(String.valueOf(PNTable.getValueAt(row, 1)));
                        int maNhaCungCap = Integer.parseInt(String.valueOf(PNTable.getValueAt(row, 2)));
                        String ngayLap = String.valueOf(PNTable.getValueAt(row, 3));
                        double tongTien = Double.parseDouble(String.valueOf(PNTable.getValueAt(row, 4)));
                        
                        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(ngayLap);
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        
                        maNhanVienPN.setSelectedItem(maNhanVien);
                        maNhaCungCapPN.setSelectedItem(maNhaCungCap);
                        ngayLapPN.setDate(utilDate);
                        
                        int result = JOptionPane.showConfirmDialog(null, popUpUpdatePN,
                                "Mời sửa phiếu nhập " + maPhieuNhap, JOptionPane.OK_CANCEL_OPTION);
                        
                        if (result == JOptionPane.OK_OPTION) {
                            if (validateValueUpdatePN() == false) return;
                            
                            maNhanVien = Integer.parseInt(String.valueOf(maNhanVienPN.getSelectedItem()));
                            utilDate = ngayLapPN.getDate();
                            sqlDate = new java.sql.Date(utilDate.getTime());
                            
                            PhieuNhapDTO pn = new PhieuNhapDTO(maPhieuNhap, maNhanVien, maNhaCungCap, sqlDate, tongTien);
                            
                            phieuNhapBLL.update(pn);
                            
                            updatePNTable();
                            
                            maNhanVienPN.setSelectedItem("");
                            maNhaCungCapPN.setSelectedItem("");
                            ngayLapPN.setDate(null);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ImportInvoiceGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (row >= 0 && col == 6) {
                    showComfirmRemovePN(row);
                }
                
                if (row >= 0 && col != 5 && col != 6) {
                    int maPhieuNhap =  Integer.parseInt(String.valueOf(PNTable.getValueAt(row, 0)));
                    
                    ArrayList<ChiTietPhieuNhapDTO> ctpnList = chiTietPhieuNhapBLL.getByPNId(maPhieuNhap);
                    
                    DefaultTableModel modelCTPN = (DefaultTableModel) CTPNTable.getModel();
        
                    for (ChiTietPhieuNhapDTO ctpn : ctpnList) {
                        maPhieuNhap = ctpn.getMaPhieuNhap();
                        int maSach = ctpn.getMaSach();
                        int soLuong = ctpn.getSoLuong();
                        long donGia = ctpn.getDonGia();

                        modelCTPN.addRow(new Object[]{maPhieuNhap, maSach, soLuong, donGia, "O"});
                    }
                }
            }
        });
    }
    
    private boolean validateValueUpdateCTPN() {
        String maSach = String.valueOf(maSachCTPN_update.getSelectedItem());
        String soLuong = soLuongCTPN_update.getText();
        String maPhieuNhap = String.valueOf(maPhieuNhapCTPN_update.getSelectedItem());
        
        if ("".equals(maSach) || "".equals(soLuong) || "".equals(maPhieuNhap)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int soLuongNum = checkInputNumberValue(soLuong, "Số lượng");
        return soLuongNum != -1;
    }
    
    private void updateCTPNTable() {
        removeRowTable(CTPNTable);
        
        setCTPNTable();
    }
    
    private void setCTPNTable() {
        ArrayList<ChiTietPhieuNhapDTO> CTPNList = chiTietPhieuNhapBLL.getAll();
        int maPhieuNhap;
        int maSach;
        int soLuong;
        long donGia;
        
        DefaultTableModel modelCTPN = (DefaultTableModel) CTPNTable.getModel();
        
        for (ChiTietPhieuNhapDTO ctpn : CTPNList) {
            maPhieuNhap = ctpn.getMaPhieuNhap();
            maSach = ctpn.getMaSach();
            soLuong = ctpn.getSoLuong();
            donGia = ctpn.getDonGia();
            
            modelCTPN.addRow(new Object[]{maPhieuNhap, maSach, soLuong, donGia, "O"});
        }
        
       
    }
    
    private void addEventCTPNTable() {
        CTPNTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = CTPNTable.rowAtPoint(evt.getPoint());
                int col = CTPNTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 4) {
                    int maPhieuNhap =  Integer.parseInt(String.valueOf(CTPNTable.getValueAt(row, 0)));
                    int maSach = Integer.parseInt(String.valueOf(CTPNTable.getValueAt(row, 1)));
                    int soLuong = Integer.parseInt(String.valueOf(CTPNTable.getValueAt(row, 2)));
                    long donGia = Long.parseLong(String.valueOf(CTPNTable.getValueAt(row, 3)));

                    maPhieuNhapCTPN_update.setSelectedItem(maPhieuNhap);
                    maSachCTPN_update.setSelectedItem(maSach);
                    soLuongCTPN_update.setText(soLuong + "");
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateCTPN, 
                                "Mời sửa chi tiết phiếu nhập", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdateCTPN() == false) return;
                        
                        SachDTO sCu = sachBLL.getById(maSach);
                        sCu.setSoLuongConLai(sCu.getSoLuongConLai() - soLuong);
                        
                        int maPhieuNhapMoi = Integer.parseInt(String.valueOf(maPhieuNhapCTPN_update.getSelectedItem()));
                        int maSachMoi = Integer.parseInt(String.valueOf(maSachCTPN_update.getSelectedItem()));
                        SachDTO sMoi = sachBLL.getById(maSachMoi);
                        soLuong = Integer.parseInt(soLuongCTPN_update.getText());
                        donGia = sMoi.getGiaNhap();
                        
                        sMoi.setSoLuongConLai(sMoi.getSoLuongConLai() + soLuong);
                        
                        sachBLL.update(sMoi);
                        sachBLL.update(sCu);
                        
                        ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(maPhieuNhapMoi, maSachMoi, soLuong, donGia);
                        
                        chiTietPhieuNhapBLL.update(ctpn, maPhieuNhap, maSach);
                        
                        updateCTPNTable();
                        
                        double tongTien = 0;
                        
                        ArrayList<ChiTietPhieuNhapDTO> ctpnList = chiTietPhieuNhapBLL.getByPNId(maPhieuNhap);
                        for (ChiTietPhieuNhapDTO ct : ctpnList) {
                            tongTien += ct.getDonGia() * ct.getSoLuong();
                        }
                        
                        PhieuNhapDTO pn = phieuNhapBLL.getById(maPhieuNhap);
                        pn.setTongTien(tongTien);
                        phieuNhapBLL.update(pn);
                        
                        updatePNTable();
                        
                        maPhieuNhapCTPN_update.setSelectedItem("");
                        maSachCTPN_update.setSelectedItem("");
                        soLuongCTPN_update.setText("");
                    }
                }
            }
        });
    }
    
    public ImportInvoiceGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        PNTable.getColumnModel().getColumn(4).setCellRenderer(new CurrencyTableCellRenderer());
        PNTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        PNTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        
        CTPNTable.getColumnModel().getColumn(3).setCellRenderer(new CurrencyTableCellRenderer());
        CTPNTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setPNTable();
        addEventPNTable();
        
        setCTPNTable();
        addEventCTPNTable();
        
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
        inputCTPNId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addCTPBBtn = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CTPNTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        inputPNId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        PNTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        addPBBtn = new javax.swing.JLabel();
        searchCbbox = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        print = new javax.swing.JLabel();
        logoutBtn1 = new javax.swing.JLabel();
        exportExcel = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        backBtn = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phiếu Nhập và Chi Tiết Phiếu Nhập");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        inputCTPNId.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputCTPNId.setForeground(new java.awt.Color(102, 102, 102));
        inputCTPNId.setText("Nhập mã phiếu nhập");
        inputCTPNId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCTPNIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCTPNIdFocusLost(evt);
            }
        });
        inputCTPNId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputCTPNIdKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Chi Tiết Phiếu Nhập :");

        addCTPBBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/add.png"))); // NOI18N
        addCTPBBtn.setToolTipText("Thêm khách hàng");
        addCTPBBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCTPBBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCTPBBtnMouseClicked(evt);
            }
        });

        CTPNTable.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        CTPNTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập", "Mã Sách", "Số Lượng", "Đơn Giá", "Sửa"
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
        jScrollPane1.setViewportView(CTPNTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
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
                .addComponent(inputCTPNId, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(addCTPBBtn))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputCTPNId, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(addCTPBBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputPNId.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputPNId.setForeground(new java.awt.Color(102, 102, 102));
        inputPNId.setText("Nhập mã phiếu nhập");
        inputPNId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPNIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPNIdFocusLost(evt);
            }
        });
        inputPNId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputPNIdKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Phiếu Nhập :");

        PNTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        PNTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập", "Mã Nhân Viên", "Mã Nhà Cung Cấp", "Ngày Lập", "Tổng Tiền", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PNTable.setEditingColumn(1);
        PNTable.setEditingRow(1);
        jScrollPane3.setViewportView(PNTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
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

        searchCbbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu nhập", "Mã nhân viên", "Mã nhà cung cấp" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPNId, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchCbbox, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPBBtn)
                .addGap(20, 20, 20))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(inputPNId, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(addPBBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(searchCbbox))
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

        logoutBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        logoutBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtn1MouseClicked(evt);
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
                .addGap(28, 28, 28)
                .addComponent(infoUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(print)
                .addGap(35, 35, 35)
                .addComponent(logoutBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(infoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(print, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
            .addComponent(logoutBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 153));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/back.png"))); // NOI18N
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        dateTimeLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeLabel.setText("10/1/2003 - 22:00:00 PM");

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
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<PhieuNhapDTO> pnList = phieuNhapBLL.getAll();
        ArrayList<ChiTietPhieuNhapDTO> ctpnList = chiTietPhieuNhapBLL.getAll();
        
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("phieunhap");
            XSSFSheet sheet1 = workbook.createSheet("chitietphieunhap");
            
            XSSFRow row = null;
            XSSFCell cell = null;
            
            row = sheet.createRow(0);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã phiếu nhập");
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã nhân viên");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã nhà cung cấp");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày lập");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng tiền");
            
            int i = 1;
            for (PhieuNhapDTO pn : pnList) {
                row = sheet.createRow(0 + i);
                
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(pn.getMaPhieuNhap());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(pn.getMaNhanVien());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(pn.getMaNhaCungCap());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(pn.getNgayLap().toString());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(pn.getTongTien());
                
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
            cell.setCellValue("Mã sách");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số lượng");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Đơn giá");
            
            i = 1;
            for (ChiTietPhieuNhapDTO ctpn : ctpnList) {
                row = sheet1.createRow(0 + i);
                
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(ctpn.getMaPhieuNhap());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(ctpn.getMaSach());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(ctpn.getSoLuong());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(ctpn.getDonGia());
                
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

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        this.dispose();
        NhanVienDTO nv = new NhanVienBLL().getByNVid(tk.getMaNhanVien());
            
        switch (nv.getVaiTro()) {
            case "Quản lý" -> new ManagerMenuGUI(tk).setVisible(true);
            case "Nhân viên bán hàng" -> new SellEmployeeMenuGUI(tk).setVisible(true);
            case "Nhân viên nhập hàng" -> new ImportEmployeeMenuGUI(tk).setVisible(true);
        }
    }//GEN-LAST:event_backBtnMouseClicked

    private void inputPNIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPNIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String value = inputPNId.getText();
            int searchType = searchCbbox.getSelectedIndex();
            int maPhieuNhap;
            int maNhanVien;
            int maNhaCungCap;
            Date ngayLap;
            double tongTien;
            
            ArrayList<PhieuNhapDTO> PNList = null;
            DefaultTableModel modelPN = (DefaultTableModel) PNTable.getModel();
            modelPN.setRowCount(0);
            
            switch (searchType) {
                case 0 -> {
                    PNList = phieuNhapBLL.getByCondition("maPhieuNhap LIKE '%" + value + "%'");
                }
                case 1 -> {
                    PNList = phieuNhapBLL.getByCondition("maNhaCungCap LIKE '%" + value + "%'");
                }
                case 2 -> {
                    PNList = phieuNhapBLL.getByCondition("maNhanVien LIKE '%" + value + "%'");
                    break;
                }
            }

            if (PNList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setPNTable();
            } else {
                for (PhieuNhapDTO pn : PNList) {
                    maPhieuNhap = pn.getMaPhieuNhap();
                    maNhanVien = pn.getMaNhanVien();
                    maNhaCungCap = pn.getMaNhaCungCap();
                    ngayLap = pn.getNgayLap();
                    tongTien = pn.getTongTien();
                    
                    modelPN.addRow(new Object[]{maPhieuNhap, maNhanVien, maNhaCungCap, ngayLap, tongTien, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputPNIdKeyPressed

    private boolean validateValueAddCTPN() {
        String soLuong = soLuongCTPN_add.getText();
        String maPhieuNhap = String.valueOf(maPhieuNhapCTPN_add.getSelectedItem());
        String maSach = String.valueOf(String.valueOf(maSachCTPN_add.getSelectedItem()));
        
        if ("".equals(soLuong) || "".equals(maPhieuNhap) || "".equals(maSach)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int soLuongNum = checkInputNumberValue(soLuong, "Số lượng");
        return soLuongNum != -1;
    }
    
    private void addCTPBBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCTPBBtnMouseClicked
        int result = JOptionPane.showConfirmDialog(null, popUpAddCTPN, 
               "Mời nhập dữ liệu chi tiết phiếu nhập mới", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            if (validateValueAddCTPN() == false) return;
            
            int maPhieuNhap = Integer.parseInt(String.valueOf(maPhieuNhapCTPN_add.getSelectedItem()));
            int maSach = Integer.parseInt(String.valueOf(maSachCTPN_add.getSelectedItem()));
            int soLuong = Integer.parseInt(soLuongCTPN_add.getText());
            long donGia = sachBLL.getById(maSach).getGiaNhap();
            
            
            ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(maPhieuNhap, maSach, soLuong, donGia);
            
            if (chiTietPhieuNhapBLL.insert(ctpn)) {
                maPhieuNhapCTPN_add.setSelectedItem("");
                maSachCTPN_add.setSelectedItem("");
                soLuongCTPN_add.setText("");
            }
        }

    }//GEN-LAST:event_addCTPBBtnMouseClicked

    private void inputPNIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPNIdFocusGained
        inputPNId.setText("");
        inputPNId.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputPNIdFocusGained

    private void inputPNIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPNIdFocusLost
        inputPNId.setText("Nhập mã phiếu nhập");
        inputPNId.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputPNIdFocusLost

    private void inputCTPNIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCTPNIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String value = inputCTPNId.getText();
            if ("".equals(value)) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setCTPNTable();
            }
            
            try {
                int maPhieuNhap;
                int maSach;
                int soLuong;
                long donGia;

                ArrayList<ChiTietPhieuNhapDTO> ctpnList = chiTietPhieuNhapBLL.getByPNId(Integer.parseInt(value));
                DefaultTableModel modelCTPN = (DefaultTableModel) CTPNTable.getModel();
                modelCTPN.setRowCount(0);

                if (ctpnList.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    setCTPNTable();
                } else {
                    for (ChiTietPhieuNhapDTO ctpn : ctpnList) {
                        maPhieuNhap = ctpn.getMaPhieuNhap();
                        maSach = ctpn.getMaSach();
                        soLuong = ctpn.getSoLuong();
                        donGia = ctpn.getDonGia();

                        modelCTPN.addRow(new Object[]{maPhieuNhap, maSach, soLuong, donGia, "O"});
                    }
                }
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setCTPNTable();
            }
        }
    }//GEN-LAST:event_inputCTPNIdKeyPressed

    private void inputCTPNIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCTPNIdFocusLost
        inputCTPNId.setText("Nhập mã phiếu nhập");
        inputCTPNId.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputCTPNIdFocusLost

    private void inputCTPNIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCTPNIdFocusGained
        inputCTPNId.setText("");
        inputCTPNId.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputCTPNIdFocusGained

    private void addPBBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPBBtnMouseClicked
        this.dispose();
        new ImportBookGUI(tk);
    }//GEN-LAST:event_addPBBtnMouseClicked

    private void logoutBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtn1MouseClicked
        this.dispose();
        new LoginGUI();
    }//GEN-LAST:event_logoutBtn1MouseClicked

    private void printMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseClicked
        if (PNTable.getSelectedRow() != -1) {
            new PrintPDF().writePhieuNhap(Integer.parseInt(String.valueOf(PNTable.getValueAt(PNTable.getSelectedRow(), 0))));
            JOptionPane.showMessageDialog(null, "In hóa đơn thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để in","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_printMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CTPNTable;
    private javax.swing.JTable PNTable;
    private javax.swing.JLabel addCTPBBtn;
    private javax.swing.JLabel addPBBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputCTPNId;
    private javax.swing.JTextField inputPNId;
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
    private javax.swing.JLabel logoutBtn1;
    private javax.swing.JLabel print;
    private javax.swing.JComboBox<String> searchCbbox;
    // End of variables declaration//GEN-END:variables
}
