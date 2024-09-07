/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NhaXuatBanBLL;
import BLL.NhanVienBLL;
import BLL.PhieuBanBLL;
import BLL.SachBLL;
import BLL.TacGiaBLL;
import BLL.TheLoaiBLL;
import DTO.NhaXuatBanDTO;
import DTO.NhanVienDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TaiKhoanDTO;
import DTO.TheLoaiDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hung
 */
public final class BookGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private SachBLL sachBLL = new SachBLL();
    private PhieuBanBLL phieuBanBLL = new PhieuBanBLL();
    private TheLoaiBLL theLoaiBLL = new TheLoaiBLL();
    private TacGiaBLL tacGiaBLL = new TacGiaBLL();
    private NhaXuatBanBLL nhaXuatBanBLL = new NhaXuatBanBLL();
    
    private JTextField name = new JTextField();
    private JComboBox authorId = new JComboBox<String>();
    private JComboBox typeId = new JComboBox<String>();
    private JComboBox publisherId = new JComboBox<String>();
    private JTextField publishYear = new JTextField();
    private JTextField remain = new JTextField();
    private JTextField sellPrice = new JTextField();
    private JTextField importPrice = new JTextField();
    private JPanel popUpUpdateBook = getPopUpUpdateBook();

    
    private JPanel getPopUpUpdateBook() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        name.setFont(font_16_plain);
        authorId.setFont(font_16_plain);
        typeId.setFont(font_16_plain);
        publisherId.setFont(font_16_plain);
        publishYear.setFont(font_16_plain);
        remain.setFont(font_16_plain);
        sellPrice.setFont(font_16_plain);
        importPrice.setFont(font_16_plain);
        
        JLabel nameLabel = new JLabel("Tên sách: ");
        nameLabel.setFont(font_16_bold);
        
        JLabel authorIdLabel = new JLabel("Mã tác giả: ");
        authorIdLabel.setFont(font_16_bold);
        
        JLabel typeIdLabel = new JLabel("Mã thể loại: ");
        typeIdLabel.setFont(font_16_bold);
        
        JLabel publisherIdLabel = new JLabel("Mã nhà xuất bản: ");
        publisherIdLabel.setFont(font_16_bold);
        
        JLabel publishYearLabel = new JLabel("Năm xuất bản: ");
        publishYearLabel.setFont(font_16_bold);
        
        JLabel remainLabel = new JLabel("Số lượng còn lại: ");
        remainLabel.setFont(font_16_bold);
        
        JLabel sellPriceLabel = new JLabel("Giá bán: ");
        sellPriceLabel.setFont(font_16_bold);
        
        JLabel importPriceLabel = new JLabel("Giá nhập: ");
        importPriceLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel authorPanel = new JPanel();
        JPanel typePanel = new JPanel();
        JPanel publisherPanel = new JPanel();
        JPanel publishYearPanel = new JPanel();
        JPanel remainPanel = new JPanel();
        JPanel sellPricePanel = new JPanel();
        JPanel importPricePanel = new JPanel();

        containerPanel.setLayout(new GridLayout(3, 3, 10, 10));
        namePanel.setLayout(new BorderLayout());
        authorPanel.setLayout(new BorderLayout());
        typePanel.setLayout(new BorderLayout());
        publisherPanel.setLayout(new BorderLayout());
        publishYearPanel.setLayout(new BorderLayout());
        remainPanel.setLayout(new BorderLayout());
        sellPricePanel.setLayout(new BorderLayout());
        importPricePanel.setLayout(new BorderLayout());
        
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.CENTER);
        
        authorPanel.add(authorIdLabel, BorderLayout.NORTH);
        authorPanel.add(authorId, BorderLayout.CENTER);
        
        typePanel.add(typeIdLabel, BorderLayout.NORTH);
        typePanel.add(typeId, BorderLayout.CENTER);
        
        publisherPanel.add(publisherIdLabel, BorderLayout.NORTH);
        publisherPanel.add(publisherId, BorderLayout.CENTER);
        
        publishYearPanel.add(publishYearLabel, BorderLayout.NORTH);
        publishYearPanel.add(publishYear, BorderLayout.CENTER);
        
        remainPanel.add(remainLabel, BorderLayout.NORTH);
        remainPanel.add(remain, BorderLayout.CENTER);
        
        sellPricePanel.add(sellPriceLabel, BorderLayout.NORTH);
        sellPricePanel.add(sellPrice, BorderLayout.CENTER);
        
        importPricePanel.add(importPriceLabel, BorderLayout.NORTH);
        importPricePanel.add(importPrice, BorderLayout.CENTER);
        
        containerPanel.add(namePanel);
        containerPanel.add(authorPanel);
        containerPanel.add(typePanel);
        containerPanel.add(publisherPanel);
        containerPanel.add(publishYearPanel);
        containerPanel.add(remainPanel);
        containerPanel.add(sellPricePanel);
        containerPanel.add(importPricePanel);
        
        return containerPanel;
    }
    
    private void updateBookTable() {
        DefaultTableModel modelBook = (DefaultTableModel) bookTable.getModel();
        modelBook.setRowCount(0);
        
        setBookTable();
    }
    
    private long checkInputNumberValue(String value, String name) {
        long num;
        if (value == null) return -1;
        
        try {
            num = Long.parseLong(value);
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
    
    private void showComfirmRemove(int row, int maSach) {
        DefaultTableModel modelBook = (DefaultTableModel) bookTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelBook.removeRow(row);
            sachBLL.delete(maSach);
        }
    }
    
    private boolean validateValueUpdateBook() {
        String tenSach = name.getText();
        String maTheLoai = String.valueOf(typeId.getSelectedItem());
        String maTacGia = String.valueOf(authorId.getSelectedItem());
        String maNhaXuatBan = String.valueOf(publisherId.getSelectedItem());
        String soLuongConLai = remain.getText();
        String giaBan = sellPrice.getText();
        String giaNhap = importPrice.getText();
        String namXuatBan = publishYear.getText();
        
        if ("".equals(tenSach) || "".equals(maTheLoai)
                || "".equals(maTacGia) || "".equals(maNhaXuatBan)
                || "".equals(soLuongConLai) || "".equals(giaBan)
                || "".equals(giaNhap) || "".equals(namXuatBan)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        
        int soLuongInt = (int) checkInputNumberValue(soLuongConLai, "Số lượng");
        if (soLuongInt < 0) {
            return false;
        }
        
        long giaBanLong = checkInputNumberValue(giaBan, "Giá bán");
        if (giaBanLong < 0) {
            return false;
        }
        
        long giaNhapLong = checkInputNumberValue(giaNhap, "Giá nhập");
        if (giaNhapLong < 0) {
            return false;
        }
        
        if (namXuatBan.matches("[0-9]\\d{1,}") == false) {
            JOptionPane.showMessageDialog(this, "Năm xuất bản không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void setJComboBox() {
        ArrayList<TheLoaiDTO> theLoaiList = theLoaiBLL.getAllSach();
        ArrayList<TacGiaDTO> tacGiaList = tacGiaBLL.getAll();
        ArrayList<NhaXuatBanDTO> nhaXuatBanList = nhaXuatBanBLL.getAll();
        
        typeId.addItem("");
        maTheLoaiInput.addItem("");
        
        authorId.addItem("");
        maTacGiaInput.addItem("");
        
        publisherId.addItem("");
        maNhaXuatBanInput.addItem("");
        
        maTheLoaiInput.setSelectedIndex(0);
        maTacGiaInput.setSelectedIndex(0);
        maNhaXuatBanInput.setSelectedIndex(0);
        
        for (TheLoaiDTO tl : theLoaiList) {
            typeId.addItem(tl.getMaTL() + " - " + tl.getTenTL());
            
            maTheLoaiInput.addItem(tl.getMaTL()  + " - " + tl.getTenTL());
        }
        
        for (TacGiaDTO tg : tacGiaList) {
            authorId.addItem(tg.getMaTacGia() + " - " + tg.getTen());
            maTacGiaInput.addItem(tg.getMaTacGia() + " - " + tg.getTen());
        }
        
        for (NhaXuatBanDTO nxb : nhaXuatBanList) {
            publisherId.addItem(nxb.getMaNhaXuatBan() + " - " + nxb.getTenNhaXuatBan());
            maNhaXuatBanInput.addItem(nxb.getMaNhaXuatBan() + " - " + nxb.getTenNhaXuatBan());
        }
    }
    
    private void setBookTable() {
        int maSach;
        String tenSach;
        int maTheLoai;
        int maTacGia;
        int maNhaXuatBan;
        int soLuongConLai;
        long giaBan;
        long giaNhap;
        int namXuatBan;
            
        ArrayList<SachDTO> bookList = sachBLL.getAllSach();
        DefaultTableModel modelBook = (DefaultTableModel) bookTable.getModel();

        for (SachDTO s : bookList) {
            maSach = s.getMaSach();
            tenSach = s.getTenSach();
            maTheLoai = s.getMaTheLoai();
            maTacGia = s.getMaTacGia();
            maNhaXuatBan = s.getMaNhaXuatBan();
            soLuongConLai = s.getSoLuongConLai();
            giaBan = s.getGiaBan();
            giaNhap = s.getGiaNhap();
            namXuatBan = s.getNamXuatBan();

            modelBook.addRow(new Object[]{maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan, "O", "X"});
        }
    }
    
    private void addEventBookTable() {
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = bookTable.rowAtPoint(evt.getPoint());
                int col = bookTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 9) {
                    int maSach = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 0)));
                    String tenSach = String.valueOf(bookTable.getValueAt(row, 1));
                    int maTheLoai = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 2)));
                    int maTacGia = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 3)));
                    int maNhaXuatBan = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 4)));
                    int soLuongConLai = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 5)));
                    long giaBan = Long.parseLong(String.valueOf(bookTable.getValueAt(row, 6)));
                    long giaNhap = Long.parseLong(String.valueOf(bookTable.getValueAt(row, 7)));
                    int namXuatBan = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 8)));
                    
                    name.setText(tenSach);
                    typeId.setSelectedItem(maTheLoai);
                    authorId.setSelectedItem(maTacGia);
                    publisherId.setSelectedItem(maNhaXuatBan);
                    publishYear.setText(namXuatBan + "");
                    remain.setText(soLuongConLai + "");
                    sellPrice.setText(giaBan + "");
                    importPrice.setText(giaNhap + "");
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateBook, 
                                "Mời sửa sách " + tenSach, JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdateBook() == false) return;
                        
                        tenSach = name.getText();
                        maTheLoai = Integer.parseInt(String.valueOf(typeId.getSelectedItem()));
                        maTacGia = Integer.parseInt(String.valueOf(authorId.getSelectedItem()));
                        maNhaXuatBan = Integer.parseInt(String.valueOf(publisherId.getSelectedItem()));
                        soLuongConLai = Integer.parseInt(remain.getText());
                        giaBan = Long.parseLong(sellPrice.getText());
                        giaNhap = Long.parseLong(importPrice.getText());
                        namXuatBan = Integer.parseInt(publishYear.getText());
                        
                        SachDTO s = new SachDTO(maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan);
                        
                        sachBLL.update(s);
                        
                        updateBookTable();
                        
                        name.setText("");
                        authorId.setSelectedItem("");
                        typeId.setSelectedItem("");
                        publisherId.setSelectedItem("");
                        publishYear.setText("");
                        remain.setText("");
                        sellPrice.setText("");
                        importPrice.setText("");
                    }
                }
                
                if (row >= 0 && col == 10) {
                    int maSach = Integer.parseInt(String.valueOf(bookTable.getValueAt(row, 0)));
                    showComfirmRemove(row, maSach);
                    
                }
            }
        });
    }
    
    public BookGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        bookTable.getColumnModel().getColumn(6).setCellRenderer(new CurrencyTableCellRenderer());
        bookTable.getColumnModel().getColumn(7).setCellRenderer(new CurrencyTableCellRenderer());
        bookTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        bookTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setBookTable();
        addEventBookTable();
        
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

        kGradientPanel2 = new keeptoo.KGradientPanel();
        backBtn = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tenSachInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        namXuatBanInput = new javax.swing.JTextField();
        soLuongConLaiInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        giaBanInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        giaNhapInput = new javax.swing.JTextField();
        maTheLoaiInput = new javax.swing.JComboBox<String>();
        maNhaXuatBanInput = new javax.swing.JComboBox<String>();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        maTacGiaInput = new javax.swing.JComboBox<String>();
        jPanel2 = new javax.swing.JPanel();
        inputBookName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        bookCbbox = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        infoUser = new javax.swing.JLabel();
        exportExcel = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sách");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
            .addComponent(dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm Sách");

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel3.setText("Tên sách          :");

        tenSachInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel4.setText("Mã thể loại       :");

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel5.setText("Mã tác giả        :");

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel6.setText("Mã nhà xuất bản   :");

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel7.setText("Năm xuất bản      :");

        namXuatBanInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        soLuongConLaiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel8.setText("Số lượng còn lại  :");

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel9.setText("Giá bán           :");

        giaBanInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel10.setText("Giá nhập          :");

        giaNhapInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        maTheLoaiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        maNhaXuatBanInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        addBtn.setBackground(new java.awt.Color(255, 153, 51));
        addBtn.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Thêm");
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });

        resetBtn.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        resetBtn.setText("Đặt lại");
        resetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetBtnMouseClicked(evt);
            }
        });

        maTacGiaInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenSachInput))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giaBanInput))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maNhaXuatBanInput, 0, 201, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maTacGiaInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maTheLoaiInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giaNhapInput))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soLuongConLaiInput))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namXuatBanInput)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tenSachInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(maTheLoaiInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maTacGiaInput))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(maNhaXuatBanInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(namXuatBanInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(soLuongConLaiInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(giaBanInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(giaNhapInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputBookName.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputBookName.setForeground(new java.awt.Color(102, 102, 102));
        inputBookName.setText("Nhập thông tin sách");
        inputBookName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputBookNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputBookNameFocusLost(evt);
            }
        });
        inputBookName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputBookNameKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Sách :");

        bookTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sách", "Tên Sách", "Mã TL", "Mã TG", "Mã NXB", "Còn Lại", "Giá Bán", "Giá Nhập", "Năm Xuất Bản", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookTable.setEditingColumn(1);
        bookTable.setEditingRow(1);
        jScrollPane3.setViewportView(bookTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bookCbbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sách", "Tên sách", "Mã thể loại", "Mã tác giả", "Mã nhà xuất bản" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputBookName, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookCbbox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBookName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookCbbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 153));

        infoUser.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        infoUser.setForeground(new java.awt.Color(51, 51, 51));
        infoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUser.setText("NV1 - Biện Thành Hưng");

        exportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/import-excel.png"))); // NOI18N
        exportExcel.setToolTipText("Xuất Excel");
        exportExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportExcelMouseClicked(evt);
            }
        });

        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtnMouseClicked(evt);
            }
        });

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
                .addGap(20, 20, 20))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addComponent(infoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void inputBookNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputBookNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int searchType = bookCbbox.getSelectedIndex();
            String value = inputBookName.getText();
            int maSach;
            String tenSach;
            int maTacGia;
            int maTheLoai;
            int maNhaXuatBan;
            int soLuongConLai;
            long giaBan;
            long giaNhap;
            int namXuatBan;
            
            ArrayList<SachDTO> bookList = null;
            DefaultTableModel modelBook = (DefaultTableModel) bookTable.getModel();
            modelBook.setRowCount(0);
            
            switch (searchType) {
                case 0 -> {
                    bookList= sachBLL.getByCondition("maSach LIKE '%" + value + "%'");
                }
                case 1 -> {
                    bookList= sachBLL.getByCondition("tenSach LIKE '%" + value + "%'");
                }
                case 2 -> {
                    bookList= sachBLL.getByCondition("maTheLoai LIKE '%" + value + "%'");
                }
                case 3 -> {
                    bookList= sachBLL.getByCondition("maTacGia LIKE '%" + value + "%'");
                }
                case 4 -> {
                    bookList= sachBLL.getByCondition("maNhaXuatBan LIKE '%" + value + "%'");
                }
            }
            
            

            if (bookList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setBookTable();
            } else {
                for (SachDTO s : bookList) {
                    maSach = s.getMaSach();
                    tenSach = s.getTenSach();
                    maTheLoai = s.getMaTheLoai();
                    maTacGia = s.getMaTacGia();
                    maNhaXuatBan = s.getMaNhaXuatBan();
                    soLuongConLai = s.getSoLuongConLai();
                    giaBan = s.getGiaBan();
                    giaNhap = s.getGiaNhap();
                    namXuatBan = s.getNamXuatBan();
                    
                    modelBook.addRow(new Object[]{maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputBookNameKeyPressed

    
    
    private void inputBookNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputBookNameFocusGained
        inputBookName.setText("");
        inputBookName.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputBookNameFocusGained

    private void inputBookNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputBookNameFocusLost
        inputBookName.setText("Nhập tên sách");
        inputBookName.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputBookNameFocusLost

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?","Thông báo", 2) == JOptionPane.OK_OPTION) {
            tenSachInput.setText("");
            maTheLoaiInput.setSelectedIndex(0);
            maTacGiaInput.setSelectedIndex(0);
            maNhaXuatBanInput.setSelectedIndex(0);
            soLuongConLaiInput.setText("");
            giaBanInput.setText("");
            giaNhapInput.setText("");
            namXuatBanInput.setText("");
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private boolean validateValueAddBook() {
        String tenSach = tenSachInput.getText();
        String maTheLoai = String.valueOf(maTheLoaiInput.getSelectedItem());
        String maTacGia = String.valueOf(maTacGiaInput.getSelectedItem());
        String maNhaXuatBan = String.valueOf(maNhaXuatBanInput.getSelectedItem());
        String soLuongConLai = soLuongConLaiInput.getText();
        String giaBan = giaBanInput.getText();
        String giaNhap = giaNhapInput.getText();
        String namXuatBan = namXuatBanInput.getText();
        
        if ("".equals(tenSach) || "".equals(maTheLoai)
                || "".equals(maTacGia) || "".equals(maNhaXuatBan)
                || "".equals(soLuongConLai) || "".equals(giaBan)
                || "".equals(giaNhap) || "".equals(namXuatBan)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int soLuongInt = (int) checkInputNumberValue(soLuongConLai, "Số lượng");
        if (soLuongInt < 0) {
            return false;
        }
        
        long giaBanLong = checkInputNumberValue(giaBan, "Giá bán");
        if (giaBanLong < 0) {
            return false;
        }
        
        long giaNhapLong = checkInputNumberValue(giaNhap, "Giá nhập");
        if (giaNhapLong < 0) {
            return false;
        }
        
        if (namXuatBan.matches("[0-9]\\d{1,}") == false) {
            JOptionPane.showMessageDialog(this, "Năm xuất bản không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddBook() == false) return;
        
        String tenSach = tenSachInput.getText();
        int maTheLoai = Integer.parseInt(String.valueOf(maTheLoaiInput.getSelectedItem()));
        int maTacGia = Integer.parseInt(String.valueOf(maTacGiaInput.getSelectedItem()));
        int maNhaXuatBan = Integer.parseInt(String.valueOf(maNhaXuatBanInput.getSelectedItem()));
        int soLuongConLai = Integer.parseInt(soLuongConLaiInput.getText());
        long giaBan = Long.parseLong(giaBanInput.getText());
        long giaNhap = Long.parseLong(giaNhapInput.getText());
        int namXuatBan = Integer.parseInt(namXuatBanInput.getText());
        
        SachDTO s = new SachDTO(-1, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan);
        
        int maSach = sachBLL.insert(s);
        
        if (maSach >= 0) {
            tenSachInput.setText("");
            maTheLoaiInput.setSelectedIndex(0);
            maTacGiaInput.setSelectedIndex(0);
            maNhaXuatBanInput.setSelectedIndex(0);
            soLuongConLaiInput.setText("");
            giaBanInput.setText("");
            giaNhapInput.setText("");
            namXuatBanInput.setText("");
            
            DefaultTableModel modelBook = (DefaultTableModel) bookTable.getModel();
            modelBook.addRow(new Object[]{maSach, tenSach, maTheLoai, maTacGia, maNhaXuatBan, soLuongConLai, giaBan, giaNhap, namXuatBan, "O", "X"});

            JOptionPane.showMessageDialog(rootPane, "Thêm sách thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnMouseClicked

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<SachDTO> sList = sachBLL.getAllSach();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhanvien");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã sách");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên sách");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã thể loại");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mã tác giả");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Mã nhà xuất bản");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Số lượng còn lại");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Giá bán");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Giá nhập");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Năm xuất bản");

            int i = 1;
            for (SachDTO s : sList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(s.getMaSach());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(s.getTenSach());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(s.getMaTheLoai());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(s.getMaTacGia());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(s.getMaNhaXuatBan());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(s.getSoLuongConLai());

                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(s.getGiaBan());

                cell = row.createCell(8, CellType.NUMERIC);
                cell.setCellValue(s.getGiaNhap());

                cell = row.createCell(9, CellType.NUMERIC);
                cell.setCellValue(s.getNamXuatBan());

                i++;
            }

            File f = new File("D://sach.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/sach.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JComboBox<String> bookCbbox;
    private javax.swing.JTable bookTable;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JTextField giaBanInput;
    private javax.swing.JTextField giaNhapInput;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputBookName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JComboBox<String> maNhaXuatBanInput;
    private javax.swing.JComboBox<String> maTacGiaInput;
    private javax.swing.JComboBox<String> maTheLoaiInput;
    private javax.swing.JTextField namXuatBanInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField soLuongConLaiInput;
    private javax.swing.JTextField tenSachInput;
    // End of variables declaration//GEN-END:variables
}
