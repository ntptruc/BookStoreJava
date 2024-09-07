/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NhaXuatBanBLL;
import BLL.NhanVienBLL;
import DTO.NhaXuatBanDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
public final class PublisherGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private NhaXuatBanBLL nhaXuatBanBLL = new NhaXuatBanBLL();
    
    private JTextField name = new JTextField();
    private JTextField address = new JTextField();
    private JTextField phone = new JTextField();
    private JPanel popUpUpdatePublisher = getPopUpUpdatePublisher();

    
    private JPanel getPopUpUpdatePublisher() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        name.setFont(font_16_plain);
        address.setFont(font_16_plain);
        phone.setFont(font_16_plain);
        
        JLabel nameLabel = new JLabel("Tên nhà xuất bản: ");
        nameLabel.setFont(font_16_bold);
        
        JLabel addressLabel = new JLabel("Địa chỉ: ");
        addressLabel.setFont(font_16_bold);
        
        JLabel phoneLabel = new JLabel("Số điện thoại: ");
        phoneLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel idPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel phonePanel = new JPanel();
        
        containerPanel.setLayout(new GridLayout(1, 3, 10, 10));
        namePanel.setLayout(new BorderLayout());
        addressPanel.setLayout(new BorderLayout());
        phonePanel.setLayout(new BorderLayout());
        
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.CENTER);
        
        addressPanel.add(addressLabel, BorderLayout.NORTH);
        addressPanel.add(address, BorderLayout.CENTER);
        
        phonePanel.add(phoneLabel, BorderLayout.NORTH);
        phonePanel.add(phone, BorderLayout.CENTER);
        
        containerPanel.add(namePanel);
        containerPanel.add(addressPanel);
        containerPanel.add(phonePanel);
        
        return containerPanel;
    }
    
    private void updatePublisherTable() {
        DefaultTableModel modelPublisher = (DefaultTableModel) publisherTable.getModel();
        modelPublisher.setRowCount(0);
        setPublisherTable();
    }
    
    private void showComfirmRemove(int row, int maNhaXuatBan) {
        DefaultTableModel modelPublisher = (DefaultTableModel) publisherTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelPublisher.removeRow(row);
            nhaXuatBanBLL.delete(maNhaXuatBan);
        }
    }
    
    private boolean validateValueUpdatePublisher() {
        String tenNhaXuatBan = name.getText();
        String diaChi = address.getText();
        String soDienThoai = phone.getText();
        
        if ("".equals(tenNhaXuatBan) || "".equals(diaChi)
                || "".equals(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if (soDienThoai.matches("^[0-9]{10}$") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void setPublisherTable() {
        int maNhaXuatBan;
        String tenNhaXuatBan;
        String diaChi;
        String soDienThoai;
            
        ArrayList<NhaXuatBanDTO> publisherList = nhaXuatBanBLL.getAll();
        DefaultTableModel modelPublisher = (DefaultTableModel) publisherTable.getModel();

        for (NhaXuatBanDTO s : publisherList) {
            maNhaXuatBan = s.getMaNhaXuatBan();
            tenNhaXuatBan = s.getTenNhaXuatBan();
            diaChi = s.getDiaChi();
            soDienThoai = s.getSoDienThoai();

            modelPublisher.addRow(new Object[]{maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai, "O", "X"});
        }
    }
    
    private void addEventPublisherTable() {
        publisherTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = publisherTable.rowAtPoint(evt.getPoint());
                int col = publisherTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 4) {
                    int maNhaXuatBan = Integer.parseInt(String.valueOf(publisherTable.getValueAt(row, 0)));
                    String tenNhaXuatBan = String.valueOf(publisherTable.getValueAt(row, 1));
                    String diaChi = String.valueOf(publisherTable.getValueAt(row, 2));
                    String soDienThoai = String.valueOf(publisherTable.getValueAt(row, 3));
                    
                    name.setText(tenNhaXuatBan);
                    address.setText(diaChi);
                    phone.setText(soDienThoai);
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdatePublisher, 
                                "Mời sửa nhà xuất bản " + tenNhaXuatBan, JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdatePublisher() == false) return;
                        
                        tenNhaXuatBan = name.getText();
                        diaChi = address.getText();
                        soDienThoai = phone.getText();
                        
                        NhaXuatBanDTO nxb = new NhaXuatBanDTO(maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai);
                        
                        nhaXuatBanBLL.update(nxb);
                        
                        updatePublisherTable();
                        
                        name.setText("");
                        address.setText("");
                        phone.setText("");
                    }
                }
                
                if (row >= 0 && col == 5) {
                    int maNhaXuatBan = Integer.parseInt(String.valueOf(publisherTable.getValueAt(row, 0)));
                    showComfirmRemove(row, maNhaXuatBan);
                }
            }
        });
    }
    
    public PublisherGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        publisherTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        publisherTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setPublisherTable();
        addEventPublisherTable();
        
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
        tenNhaXuatBanInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        diaChiInput = new javax.swing.JTextField();
        soDienThoaiInput = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        inputPublisherName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        publisherTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        searchCbbox = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        logoutBtn = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        exportExcel = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        backBtn = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nhà Xuất Bản");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm Nhà Xuất Bản");

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel3.setText("Tên nhà xuất bản  :");

        tenNhaXuatBanInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel4.setText("Địa chỉ           :");

        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel5.setText("Số điện thoại     :");

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

        diaChiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        soDienThoaiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenNhaXuatBanInput))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soDienThoaiInput))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diaChiInput)))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tenNhaXuatBanInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(diaChiInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(soDienThoaiInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputPublisherName.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputPublisherName.setForeground(new java.awt.Color(102, 102, 102));
        inputPublisherName.setText("Nhập thông tin nhà xuất bản");
        inputPublisherName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputPublisherNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputPublisherNameFocusLost(evt);
            }
        });
        inputPublisherName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputPublisherNameKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Nhà Xuất Bản :");

        publisherTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        publisherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhà Xuất Bản", "Tên Nhà Xuất Bản", "Địa Chỉ", "Số Điện Thoại", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        publisherTable.setEditingColumn(1);
        publisherTable.setEditingRow(1);
        jScrollPane3.setViewportView(publisherTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        searchCbbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhà xuất bản", "Tên nhà xuất bản", "Số điện thoại" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputPublisherName, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchCbbox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPublisherName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchCbbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(exportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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

    private void inputPublisherNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputPublisherNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String value = inputPublisherName.getText();
            int searchType = searchCbbox.getSelectedIndex();
            int maNhaXuatBan;
            String tenNhaXuatBan;
            String diaChi;
            String soDienThoai;
            
            ArrayList<NhaXuatBanDTO> publisherList = null;
            DefaultTableModel modelPublisher = (DefaultTableModel) publisherTable.getModel();
            modelPublisher.setRowCount(0);
            
            switch (searchType) {
                case 0 -> {
                    publisherList = nhaXuatBanBLL.getByCondition("maNhaXuatBan LIKE '%" + value.toUpperCase() + "%'");
                }
                case 1 -> {
                    publisherList = nhaXuatBanBLL.getByCondition("tenNhaXuatBan LIKE '%" + value + "%'");
                }
                case 2 -> {
                    publisherList = nhaXuatBanBLL.getByCondition("soDienThoai LIKE '%" + value + "%'");
                }
            }

            if (publisherList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setPublisherTable();
            } else {
                for (NhaXuatBanDTO s : publisherList) {
                    maNhaXuatBan = s.getMaNhaXuatBan();
                    tenNhaXuatBan = s.getTenNhaXuatBan();
                    diaChi = s.getDiaChi();
                    soDienThoai = s.getSoDienThoai();
                    
                    modelPublisher.addRow(new Object[]{maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputPublisherNameKeyPressed

    
    
    private void inputPublisherNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPublisherNameFocusGained
        inputPublisherName.setText("");
        inputPublisherName.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputPublisherNameFocusGained

    private void inputPublisherNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputPublisherNameFocusLost
        inputPublisherName.setText("Nhập thông tin nhà xuất bản");
        inputPublisherName.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputPublisherNameFocusLost

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?","Thông báo", 2) == JOptionPane.OK_OPTION) {
            tenNhaXuatBanInput.setText("");
            diaChiInput.setText("");
            soDienThoaiInput.setText("");
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private boolean validateValueAddPublisher() {
        String tenNhaXuatBan = tenNhaXuatBanInput.getText();
        String diaChi = diaChiInput.getText();
        String soDienThoai =  soDienThoaiInput.getText();
        
        if ("".equals(tenNhaXuatBan) || "".equals(diaChi)
                || "".equals(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if (soDienThoai.matches("^[0-9]{10}$") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddPublisher() == false) return;
        
        String tenNhaXuatBan = tenNhaXuatBanInput.getText();
        String diaChi = diaChiInput.getText();
        String soDienThoai =  soDienThoaiInput.getText();
        
        NhaXuatBanDTO nxb = new NhaXuatBanDTO(-1, tenNhaXuatBan, diaChi, soDienThoai);
        
        int maNhaXuatBan = nhaXuatBanBLL.insert(nxb);
        
        if (maNhaXuatBan >= 0) {
            tenNhaXuatBanInput.setText("");
            diaChiInput.setText("");
            soDienThoaiInput.setText("");
            
            DefaultTableModel modelPublisher = (DefaultTableModel) publisherTable.getModel();
            modelPublisher.addRow(new Object[]{maNhaXuatBan, tenNhaXuatBan, diaChi, soDienThoai, "O", "X"});

            JOptionPane.showMessageDialog(rootPane, "Thêm nhà xuất bản thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnMouseClicked

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<NhaXuatBanDTO> nxbList = nhaXuatBanBLL.getAll();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhaxuatban");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã nhà xuất bản");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên nhà xuất bản");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số điện thoại");

            int i = 1;
            for (NhaXuatBanDTO nxb : nxbList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(nxb.getMaNhaXuatBan());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(nxb.getTenNhaXuatBan());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(nxb.getDiaChi());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(nxb.getSoDienThoai());

                i++;
            }

            File f = new File("D://nhaxuatban.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/nhaxuatban.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JTextField diaChiInput;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputPublisherName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTable publisherTable;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox<String> searchCbbox;
    private javax.swing.JTextField soDienThoaiInput;
    private javax.swing.JTextField tenNhaXuatBanInput;
    // End of variables declaration//GEN-END:variables
}
