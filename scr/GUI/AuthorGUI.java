/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NhanVienBLL;
import BLL.TacGiaBLL;
import DTO.NhanVienDTO;
import DTO.TacGiaDTO;
import DTO.TaiKhoanDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class AuthorGUI extends javax.swing.JFrame {
    private TaiKhoanDTO tk;
    private TacGiaBLL tacGiaBLL = new TacGiaBLL();
    
    private JTextField tenTG = new JTextField();
    private JComboBox gioiTinh = new JComboBox<String>(new String[] {"Nam","Nữ"});
    private JTextField namSinh = new JTextField();
    
     private JPanel popUpUpdateTG = getPopUpUpdateTG();

    /**
     * Creates new form TacGiaGUI
     */
    public AuthorGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        username.setText(tk.getTenDangNhap());
     
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
     
        setTGTable();
     
        TGTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        TGTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
    
        addEventTGTable();
     
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private boolean validateValueAddTG() {
        String tentg = tenTGInput.getText();
        String gt = (String) gioiTinhInput.getSelectedItem();
        String namsinh = namSinhInput.getText();
        
        
        if ("".equals(tentg) || "".equals(gt)
                || "".equals(namsinh) ) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if (namsinh.matches("[0-9]\\d{1,}") == false) {
            JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private boolean validateValueUpdateTG() {
        String tentg = tenTG.getText();
        String gt = (String) gioiTinh.getSelectedItem();
        String namsinh = namSinh.getText();
        
        
        if ("".equals(tentg) || "".equals(gt)
                || "".equals(namsinh) ) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
      
        if (namsinh.matches("[0-9]\\d{1,}") == false) {
            JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private JPanel getPopUpUpdateTG() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        tenTG.setFont(font_16_plain);
        gioiTinh.setFont(font_16_plain);
        namSinh.setFont(font_16_plain);
        
        
        JLabel tenTGLabel = new JLabel("Tên tác giả: ");
        tenTGLabel.setFont(font_16_bold);
        
        JLabel gioiTinhLabel = new JLabel("Giới tính: ");
        gioiTinhLabel.setFont(font_16_bold);
        
        JLabel namSinhLabel = new JLabel("Năm sinh: ");
        namSinhLabel.setFont(font_16_bold);
        
        
        
        JPanel containerPanel = new JPanel();
        JPanel tenTGPanel = new JPanel();
        JPanel gioiTinhPanel = new JPanel();
        JPanel namSinhPanel = new JPanel();
        

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        tenTGPanel.setLayout(new BorderLayout());
        gioiTinhPanel.setLayout(new BorderLayout());
        namSinhPanel.setLayout(new BorderLayout());
        
        tenTGPanel.add(tenTGLabel, BorderLayout.NORTH);
        tenTGPanel.add(tenTG, BorderLayout.CENTER);
        
        gioiTinhPanel.add(gioiTinhLabel, BorderLayout.NORTH);
        gioiTinhPanel.add(gioiTinh, BorderLayout.CENTER);
        
        namSinhPanel.add(namSinhLabel, BorderLayout.NORTH);
        namSinhPanel.add(namSinh, BorderLayout.CENTER);
        
        containerPanel.add(tenTGPanel);
        containerPanel.add(gioiTinhPanel);
        containerPanel.add(namSinhPanel);
       
        return containerPanel;
    }
    
    
    private void addEventTGTable() {
        TGTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TGTable.rowAtPoint(evt.getPoint());
                int col = TGTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 4) {
                    int matg = Integer.parseInt(String.valueOf(TGTable.getValueAt(row, 0)));
                    String tentg = String.valueOf(TGTable.getValueAt(row, 1));
                    String gt = String.valueOf(TGTable.getValueAt(row, 2));
                    int namsinh = Integer.parseInt(String.valueOf(TGTable.getValueAt(row, 3)));
                    
                    
                    tenTG.setText(tentg);
                    gioiTinh.setSelectedItem(gt);
                    namSinh.setText(namsinh + "");
                  
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateTG, 
                                "Mời sửa thông tin tác giả " , JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION){
                        if (validateValueUpdateTG() == false) return;
                        
                        tentg = tenTG.getText();
                        gt = (String) gioiTinh.getSelectedItem();
                        namsinh = Integer.parseInt(namSinh.getText());
                        
                        TacGiaDTO tg = new TacGiaDTO(matg,tentg,gt,namsinh);
                        
                        tacGiaBLL.update(tg);
                        
                        updateTGTable();
                        
                        tenTG.setText("");
                        gioiTinh.setSelectedItem("");
                        namSinh.setText("");
                    }
                }
                
                if (row >= 0 && col == 5) {
                    int ma = Integer.parseInt(String.valueOf(TGTable.getValueAt(row, 0)));
                    showComfirmRemove(row, ma);
                }
            }
        });
    }
    
    private void showComfirmRemove(int row, int matg) {
        DefaultTableModel modelTG = (DefaultTableModel) TGTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelTG.removeRow(row);
            tacGiaBLL.delete(matg);
        }
    }
    
    private void setTGTable() {
        int matg;
        String tentg;
        String gt;
        int namsinh;
        
            
        ArrayList<TacGiaDTO> TGList = tacGiaBLL.getAll();
        DefaultTableModel modelTG = (DefaultTableModel) TGTable.getModel();
        modelTG.setRowCount(0);

        for (TacGiaDTO s : TGList) {
            tentg = s.getTen();
            gt = s.getGioiTinh();
            matg = s.getMaTacGia();
            namsinh = s.getNamSinh();
            

            modelTG.addRow(new Object[]{matg,tentg,gt,namsinh, "O", "X"});
        }
    }
    
    
    private void updateTGTable() {
        DefaultTableModel modelTG = (DefaultTableModel) TGTable.getModel();
        modelTG.setRowCount(0);
        
        setTGTable();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tenTGInput = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        namSinhInput = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        gioiTinhInput = new javax.swing.JComboBox<String>(new String[] {"Nam","Nữ"});
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        inputSearch = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TGTable = new javax.swing.JTable();
        condition = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        exportExcel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setPreferredSize(new java.awt.Dimension(442, 551));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thêm Tác Giả");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 33));

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel11.setText("Tên tác giả      :");
        jLabel11.setPreferredSize(new java.awt.Dimension(213, 25));

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel12.setText("Giới tính        :");
        jLabel12.setPreferredSize(new java.awt.Dimension(213, 25));

        tenTGInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tenTGInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenTGInputActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel13.setText("Năm Sinh         :");
        jLabel13.setPreferredSize(new java.awt.Dimension(213, 25));

        namSinhInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        namSinhInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namSinhInputActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gioiTinhInput, 0, 197, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenTGInput))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namSinhInput))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenTGInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gioiTinhInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namSinhInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel14.setText("Tìm tác giả :");

        inputSearch.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputSearch.setForeground(new java.awt.Color(102, 102, 102));
        inputSearch.setText("Tìm kiếm ...");
        inputSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputSearchFocusLost(evt);
            }
        });
        inputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSearchActionPerformed(evt);
            }
        });
        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputSearchKeyPressed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        TGTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        TGTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Tác Giả", "Tên Tác Giả", "Giới Tính", "Năm Sinh", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
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
        TGTable.setUpdateSelectionOnSort(false);
        TGTable.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(TGTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã tác giả", "Tên tác giả" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(inputSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 153));

        exportExcel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/import-excel.png"))); // NOI18N
        exportExcel1.setToolTipText("Xuất Excel");
        exportExcel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportExcel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportExcel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        username.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 153));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/back.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        dateTimeLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeLabel.setText("jLabel8");
        dateTimeLabel.setPreferredSize(new java.awt.Dimension(158, 33));

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tenTGInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenTGInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenTGInputActionPerformed

    private void namSinhInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namSinhInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namSinhInputActionPerformed

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddTG() == false)
            return;
        
        String tentg = tenTGInput.getText();
        String gt = (String) gioiTinhInput.getSelectedItem();
        int namsinh = Integer.parseInt(namSinhInput.getText());

        TacGiaDTO tg = new TacGiaDTO(-1,tentg,gt,namsinh);

        int matg = tacGiaBLL.insert(tg);
        
        if (matg >= 1) {
            tenTGInput.setText("");
            gioiTinhInput.setSelectedIndex(0);
            namSinhInput.setText("");
            
            DefaultTableModel modelTG = (DefaultTableModel) TGTable.getModel();
            modelTG.addRow(new Object[] { matg,tentg,gt,namsinh, "O", "X" });

            JOptionPane.showMessageDialog(rootPane, "Thêm tác giả thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnMouseClicked

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
       if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == JOptionPane.OK_OPTION) {
            tenTGInput.setText("");
            gioiTinhInput.setSelectedIndex(0);
            namSinhInput.setText("");
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetBtnActionPerformed

    private void inputSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusGained
         inputSearch.setText("");
        inputSearch.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputSearchFocusGained

    private void inputSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusLost
       inputSearch.setText("Tìm kiếm ...");
        inputSearch.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputSearchFocusLost

    private void inputSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int con =  condition.getSelectedIndex();
            String value = inputSearch.getText();
            String tentg;
            String gt;
            int matg;
            int namsinh;
            
            System.out.println(con);
           
            ArrayList<TacGiaDTO> TGList = new ArrayList<TacGiaDTO>();
            
            if(con == 0){
                TGList = tacGiaBLL.getByCondition("maTacGia LIKE '%" + value + "%'");
            }else{
                TGList = tacGiaBLL.getByCondition("tenTacGia LIKE '%" + value + "%'");
            }
            
            DefaultTableModel modelTG = (DefaultTableModel) TGTable.getModel();
            modelTG.setRowCount(0);

            if (TGList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại  hoặc điều kiện kiện tìm kiếm không đúng","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setTGTable();
            } else {
                for (TacGiaDTO s : TGList) {
                    tentg = s.getTen();
                    gt = s.getGioiTinh();
                    matg = s.getMaTacGia();
                    namsinh= s.getNamSinh();
                    
                    
                    modelTG.addRow(new Object[]{matg,tentg,gt,namsinh, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputSearchKeyPressed

    private void inputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSearchActionPerformed

    private void exportExcel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcel1MouseClicked
        ArrayList<TacGiaDTO> tgList = tacGiaBLL.getAll();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("tacgia");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã tác giả");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên tác giả");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Năm sinh");

            int i = 1;
            for (TacGiaDTO tg : tgList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tg.getMaTacGia());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(tg.getTen());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(tg.getGioiTinh());

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tg.getNamSinh());

                i++;
            }

            File f = new File("D://tacgia.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/tacgia.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcel1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
        NhanVienDTO nv = new NhanVienBLL().getByNVid(tk.getMaNhanVien());
            
        switch (nv.getVaiTro()) {
            case "Quản lý" -> new ManagerMenuGUI(tk).setVisible(true);
            case "Nhân viên bán hàng" -> new SellEmployeeMenuGUI(tk).setVisible(true);
            case "Nhân viên nhập hàng" -> new ImportEmployeeMenuGUI(tk).setVisible(true);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
        new LoginGUI();
    }//GEN-LAST:event_jLabel2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TGTable;
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> condition;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel1;
    private javax.swing.JComboBox<String> gioiTinhInput;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTextField namSinhInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField tenTGInput;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
