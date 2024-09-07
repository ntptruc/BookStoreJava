/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import DTO.TaiKhoanDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.sql.Date;
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
public final class SaleGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private KhuyenMaiBLL khuyenMaiBLL = new KhuyenMaiBLL();
    
    private JTextField saleName = new JTextField();
    private JTextField percent = new JTextField();
    private JDateChooser ngayBatDau = new JDateChooser();
    private JDateChooser ngayKetThuc = new JDateChooser();
    private JPanel popUpUpdateSale = getPopUpUpdateSale();

    
    private JPanel getPopUpUpdateSale() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        saleName.setFont(font_16_plain);
        percent.setFont(font_16_plain);
        ngayBatDau.setFont(font_16_plain);
        ngayKetThuc.setFont(font_16_plain);
        
        JLabel saleNameLabel = new JLabel("Tên khuyến mãi: ");
        saleNameLabel.setFont(font_16_bold);
        
        JLabel percentLabel = new JLabel("Phần trăm: ");
        percentLabel.setFont(font_16_bold);
        
        JLabel ngayBatDauLabel = new JLabel("Ngày bắt đầu: ");
        ngayBatDauLabel.setFont(font_16_bold);
        
        JLabel ngayKetThucLabel = new JLabel("Ngày kết thúc: ");
        ngayKetThucLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel saleNamePanel = new JPanel();
        JPanel percentPanel = new JPanel();
        JPanel ngayBatDauPanel = new JPanel();
        JPanel ngayKetThucPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(2, 3, 10, 10));
        saleNamePanel.setLayout(new BorderLayout());
        percentPanel.setLayout(new BorderLayout());
        ngayBatDauPanel.setLayout(new BorderLayout());
        ngayKetThucPanel.setLayout(new BorderLayout());
        
        saleNamePanel.add(saleNameLabel, BorderLayout.NORTH);
        saleNamePanel.add(saleName, BorderLayout.CENTER);
        
        ngayBatDauPanel.add(ngayBatDauLabel, BorderLayout.NORTH);
        ngayBatDauPanel.add(ngayBatDau, BorderLayout.CENTER);
        
        percentPanel.add(percentLabel, BorderLayout.NORTH);
        percentPanel.add(percent, BorderLayout.CENTER);
        
        ngayKetThucPanel.add(ngayKetThucLabel, BorderLayout.NORTH);
        ngayKetThucPanel.add(ngayKetThuc, BorderLayout.CENTER);
        
        containerPanel.add(saleNamePanel);
        containerPanel.add(percentPanel);
        containerPanel.add(ngayBatDauPanel);
        containerPanel.add(ngayKetThucPanel);
        
        return containerPanel;
    }
    
    private void updateSaleTable() {
        DefaultTableModel modelSale = (DefaultTableModel) saleTable.getModel();
        modelSale.setRowCount(0);
        
        setSaleTable();
    }
    
    private long checkInputNumberValue(String value, String name) {
        long num;
        if (value == null) return -1;
        
        try {
            num = Integer.parseInt(value);
            if (num >= 0) {
                return num;
            } else {
                JOptionPane.showMessageDialog(this, name + " là một số không âm","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, name + " là một số nguyên dương","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        return -1;
    }
    
    private void showComfirmRemove(int row, int maKhuyenMai) {
        DefaultTableModel modelSale = (DefaultTableModel) saleTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelSale.removeRow(row);
            khuyenMaiBLL.delete(maKhuyenMai);
        }
    }
    
    private boolean validateValueUpdateSale() {
        String tenKhuyenMai = saleName.getText();
        String phanTram = percent.getText();
        String ngayBatDauText = ngayBatDau.getDateFormatString();
        String ngayKetThucText = ngayKetThuc.getDateFormatString();
        
        if ( "".equals(tenKhuyenMai)
            || "".equals(phanTram) || "".equals(ngayBatDauText) || "".equals(ngayKetThucText)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int phanTramInt = (int) checkInputNumberValue(phanTram, "Số lượng");
        if (phanTramInt < 0) {
            return false;
        }
        
        
        java.util.Date ngayBatDauDate = ngayBatDau.getDate();
        java.util.Date ngayKetThucDate = ngayKetThuc.getDate();
        
        if (ngayKetThucDate.compareTo(ngayBatDauDate) < 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn ngày kết thúc","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void setSaleTable() {
        int maKhuyenMai;
        String tenKhuyenMai;
        int phanTram;
        Date ngayBatDauText;
        Date ngayKetThucText;
            
        ArrayList<KhuyenMaiDTO> saleList = khuyenMaiBLL.getAll();
        DefaultTableModel modelSale = (DefaultTableModel) saleTable.getModel();

        for (KhuyenMaiDTO tl : saleList) {
            maKhuyenMai = tl.getMaKhuyenMai();
            tenKhuyenMai = tl.getTenKhuyenMai();
            phanTram = tl.getPhanTram();
            ngayBatDauText = tl.getNgayBatDau();
            ngayKetThucText = tl.getNgayKetThuc();

            modelSale.addRow(new Object[]{maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDauText, ngayKetThucText, "O", "X"});
        }
        
        
    }
    
    public void addEventSaleTable() {
        saleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = saleTable.rowAtPoint(evt.getPoint());
                int col = saleTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 5) {
                    int maKhuyenMai = Integer.parseInt(String.valueOf(saleTable.getValueAt(row, 0)));
                    String tenKhuyenMai = String.valueOf(saleTable.getValueAt(row, 1));
                    int phanTram = (int) saleTable.getValueAt(row, 2);
                    Date ngayBatDauDate = Date.valueOf(String.valueOf(saleTable.getValueAt(row, 3)));
                    Date ngayKetThucDate = Date.valueOf(String.valueOf(saleTable.getValueAt(row, 4)));
                    
                    saleName.setText(tenKhuyenMai);
                    percent.setText("" + phanTram);
                    ngayBatDau.setDate(ngayBatDauDate);
                    ngayKetThuc.setDate(ngayKetThucDate);
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateSale, 
                                "Mời sửa khuyến mãi " + tenKhuyenMai, JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdateSale() == false) return;
                        
                        tenKhuyenMai = saleName.getText();
                        phanTram = Integer.parseInt(percent.getText());
                        java.util.Date dateFrom = ngayBatDau.getDate();
                        java.util.Date dateTo = ngayKetThuc.getDate();
                        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
                        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
                        
                        KhuyenMaiDTO tl = new KhuyenMaiDTO(maKhuyenMai, tenKhuyenMai, phanTram, sqlDateFrom, sqlDateTo);
                        
                        khuyenMaiBLL.update(tl);
                        
                        updateSaleTable();
                        
                        saleName.setText("");
                        percent.setText("");
                    }
                }
                
                if (row >= 0 && col == 6) {
                    int maKhuyenMai = Integer.parseInt(String.valueOf(saleTable.getValueAt(row, 0)));
                    showComfirmRemove(row, maKhuyenMai);
                }
            }
        });
    }
    
    public SaleGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        saleTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        saleTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setSaleTable();
        addEventSaleTable();
        
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        saleNameInput = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        percentInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ngayKetThucInput = new com.toedter.calendar.JDateChooser();
        ngayBatDauInput = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        inputSaleSearch = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        saleTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        typeCbbox = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        exportExcel = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        dateTimeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thể Loại");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm khuyến mãi");

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel3.setText("Tên khuyến mãi  :");

        saleNameInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

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
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        percentInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel5.setText("Phần trăm       :");

        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel6.setText("Ngày bắt đầu    :");

        jLabel7.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel7.setText("Ngày kết thúc   :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percentInput))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saleNameInput))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngayBatDauInput, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngayKetThucInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(saleNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(percentInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngayBatDauInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngayKetThucInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputSaleSearch.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputSaleSearch.setForeground(new java.awt.Color(102, 102, 102));
        inputSaleSearch.setText("Nhập thông tin khuyến mãi");
        inputSaleSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputSaleSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputSaleSearchFocusLost(evt);
            }
        });
        inputSaleSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSaleSearchKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Khuyến Mãi:");

        saleTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        saleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Phần Trăm", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        saleTable.setEditingColumn(1);
        saleTable.setEditingRow(1);
        jScrollPane3.setViewportView(saleTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        typeCbbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã khuyến mãi", "Tên khuyến mãi" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(inputSaleSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(typeCbbox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(inputSaleSearch)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeCbbox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 153));

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
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(infoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
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
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void inputSaleSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSaleSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String value = inputSaleSearch.getText();
            int searchSale = typeCbbox.getSelectedIndex();
            
            int maKhuyenMai;
            String tenKhuyenMai;
            int phanTram;
            Date ngayBatDauText;
            Date ngayKetThucText;
            
            ArrayList<KhuyenMaiDTO> saleList = null;
            DefaultTableModel modelSale = (DefaultTableModel) saleTable.getModel();
            modelSale.setRowCount(0);
            
            switch (searchSale) {
                case 0 -> {
                    saleList = khuyenMaiBLL.getByCondition("maKhuyenMai LIKE '%" + value.toUpperCase() + "%'");
                }
                case 1 -> {
                    saleList = khuyenMaiBLL.getByCondition("tenKhuyenMai LIKE '%" + value + "%'");
                }
            }
            

            if (saleList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setSaleTable();
            } else {
                for (KhuyenMaiDTO tl : saleList) {
                    maKhuyenMai = tl.getMaKhuyenMai();
                    tenKhuyenMai = tl.getTenKhuyenMai();
                    phanTram = tl.getPhanTram();
                    ngayBatDauText = tl.getNgayBatDau();
                    ngayKetThucText = tl.getNgayKetThuc();
                    
                    modelSale.addRow(new Object[]{maKhuyenMai, tenKhuyenMai, phanTram, ngayBatDauText, ngayKetThucText, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputSaleSearchKeyPressed

    
    
    private void inputSaleSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSaleSearchFocusGained
        inputSaleSearch.setText("");
        inputSaleSearch.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputSaleSearchFocusGained

    private void inputSaleSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSaleSearchFocusLost
        inputSaleSearch.setText("Nhập thông tin khuyến mãi");
        inputSaleSearch.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputSaleSearchFocusLost

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?","Thông báo", 2) == JOptionPane.OK_OPTION) {
            saleNameInput.setText("");
            percentInput.setText("");
            ngayBatDauInput.setDateFormatString("");
            ngayKetThucInput.setDateFormatString("");
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    
    private boolean validateValueAddSale() {
        String tenKhuyenMai = saleNameInput.getText();
        String phanTram = percentInput.getText();
        String ngayBatDauText = ngayBatDauInput.getDateFormatString();
        String ngayKetThucText = ngayKetThucInput.getDateFormatString();
        
        if ("".equals(tenKhuyenMai) || "".equals(phanTram) || "".equals(ngayBatDauText) || "".equals(ngayKetThucText)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        int phanTramInt = (int) checkInputNumberValue(phanTram, "Phần trăm");
        if (phanTramInt < 0 || phanTramInt > 100) {
            return false;
        }
        
        java.util.Date ngayBatDauDate = ngayBatDauInput.getDate();
        java.util.Date ngayKetThucDate = ngayKetThucInput.getDate();
        
        if (ngayKetThucDate.compareTo(ngayBatDauDate) < 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn ngày kết thúc","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddSale() == false) return;
        
        String tenKhuyenMai = saleNameInput.getText();
        int phanTram = Integer.parseInt(percentInput.getText());
        java.util.Date dateFrom = ngayBatDauInput.getDate();
        java.util.Date dateTo = ngayKetThucInput.getDate();
        java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
        java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());
        
        KhuyenMaiDTO tl = new KhuyenMaiDTO(-1, tenKhuyenMai, phanTram, sqlDateFrom, sqlDateTo);
        
        int maKhuyenMai = khuyenMaiBLL.addSale(tl);
        
        if (maKhuyenMai >= 0) {
            saleNameInput.setText("");
            percentInput.setText("");
            ngayBatDauInput.setDateFormatString("");
            ngayKetThucInput.setDateFormatString("");
            
            DefaultTableModel modelSale = (DefaultTableModel) saleTable.getModel();
            modelSale.addRow(new Object[]{maKhuyenMai, tenKhuyenMai, phanTram, sqlDateFrom, sqlDateTo, "O", "X"});

            JOptionPane.showMessageDialog(rootPane, "Thêm khuyến mãi thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnMouseClicked

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetBtnActionPerformed

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<KhuyenMaiDTO> kmList = khuyenMaiBLL.getAll();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("khuyenmai");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khuyến mãi");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên khuyến mãi");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Phần trăm");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày bắt đầu");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày kết thúc");

            int i = 1;
            for (KhuyenMaiDTO km : kmList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(km.getMaKhuyenMai());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(km.getTenKhuyenMai());
                
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(km.getPhanTram());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(km.getNgayBatDau());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(km.getNgayKetThuc());

                i++;
            }

            File f = new File("D://khuyenmai.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/khuyenmai.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputSaleSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private com.toedter.calendar.JDateChooser ngayBatDauInput;
    private com.toedter.calendar.JDateChooser ngayKetThucInput;
    private javax.swing.JTextField percentInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField saleNameInput;
    private javax.swing.JTable saleTable;
    private javax.swing.JComboBox<String> typeCbbox;
    // End of variables declaration//GEN-END:variables
}
