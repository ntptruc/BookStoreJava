/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;




import BLL.NhanVienBLL;
import DTO.TaiKhoanDTO;
import DTO.TheLoaiDTO;
import BLL.TheLoaiBLL;
import DTO.NhanVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hung
 */
public final class TypeGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    private TheLoaiBLL list_PN;
    
    private TheLoaiBLL TheLoaiBLL = new TheLoaiBLL();
//    private TacGiaBLL tacGiaBLL = new TacGiaBLL();
//    private NhaXuatBanBLL nhaXuatBanBLL = new NhaXuatBanBLL();
    
    
    private JTextField name = new JTextField();
   
    private JPanel popUpUpdateType = getPopUpUpdateType();

    
    private JPanel getPopUpUpdateType() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        name.setFont(font_16_plain);
        
        
        JLabel nameLabel = new JLabel("Tên Thể Loại: ");
        nameLabel.setFont(font_16_bold);
        
        
        
        JPanel containerPanel = new JPanel();
        JPanel namePanel = new JPanel();
        

        containerPanel.setLayout(new GridLayout(1,1, 10, 10));
        namePanel.setLayout(new BorderLayout());
        
        
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.CENTER);
        
        containerPanel.add(namePanel);

        return containerPanel;
    }
    
    private void updateTypeTable() {
        DefaultTableModel modelType = (DefaultTableModel) typeTable.getModel();
        modelType.setRowCount(0);
        
        setTypeTable();
    }
    
   
    
    private void showComfirmRemove(int row, int maTheLoai) {
        DefaultTableModel modelType = (DefaultTableModel) typeTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelType.removeRow(row);
            TheLoaiBLL.delete(maTheLoai);
        }
    }
    
    private boolean validateValueUpdateType() {
        
        String tenTheLoai = name.getText();
        
        
        if ( "".equals(tenTheLoai)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
       
        
        return true;
    }
    

    
    private void setTypeTable() {
        int maTheLoai;
        String tenTheLoai;
        
            
        ArrayList<TheLoaiDTO> List = TheLoaiBLL.getAllSach();
        DefaultTableModel modelType = (DefaultTableModel) typeTable.getModel();

        for (TheLoaiDTO s : List) {
            maTheLoai = s.getMaTL();
            tenTheLoai = s.getTenTL();
            
            

            modelType.addRow(new Object[]{maTheLoai, tenTheLoai, "O", "X"});
        }
    }
    
    private void addEventTypeTable() {
        typeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = typeTable.rowAtPoint(evt.getPoint());
                int col = typeTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 2) {
                    int maTheLoai = Integer.parseInt(String.valueOf(typeTable.getValueAt(row, 0)));
                    String tenTheLoai = String.valueOf(typeTable.getValueAt(row, 1));
                    
                    
                    
                    
                    name.setText(tenTheLoai);
                    
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateType, 
                                "Mời sửa Thể Loại " + tenTheLoai, JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdateType() == false) return;
                        
                        
                        tenTheLoai  = name.getText();
                        
                        
                        TheLoaiDTO s = new TheLoaiDTO(maTheLoai, tenTheLoai);
                        
                        TheLoaiBLL.update(s);
                        
                        updateTypeTable();
                        
                        name.setText("");
                        
                    }
                }
                
                if (row >= 0 && col == 3) {
                    int maTheLoai = Integer.parseInt(String.valueOf(typeTable.getValueAt(row, 0)));
                    showComfirmRemove(row, maTheLoai);
                    
                }
            }
        });
    }
    
    public TypeGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        
        typeTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        typeTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setTypeTable();
        addEventTypeTable();
        
//        setJComboBox();
        
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
        tenSachInput = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        inputTypeName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        typeTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        luaChonInput = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        exportExcel1 = new javax.swing.JLabel();
        infoUser = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        backBtn = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sách");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm thể loại");

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel3.setText("Tên Thể Loại      :");

        tenSachInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenSachInput, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tenSachInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputTypeName.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputTypeName.setForeground(new java.awt.Color(102, 102, 102));
        inputTypeName.setText("Nhập Thông Tin Thể Loại");
        inputTypeName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputTypeNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputTypeNameFocusLost(evt);
            }
        });
        inputTypeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputTypeNameKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Thể Loại :");

        typeTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        typeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thể Loại", "Tên Thể Loại", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        typeTable.setEditingColumn(1);
        typeTable.setEditingRow(1);
        jScrollPane3.setViewportView(typeTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        luaChonInput.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        luaChonInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Thể Loại", "Tên Thể Loại" }));
        luaChonInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luaChonInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputTypeName)
                .addGap(18, 18, 18)
                .addComponent(luaChonInput, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(inputTypeName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(luaChonInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(exportExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addComponent(infoUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    
    
    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?","Thông báo", 2) == JOptionPane.OK_OPTION) {
            
            tenSachInput.setText("");
            
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private boolean validateValueAddType() {
        
        String tenTheLoai = tenSachInput.getText();
        
        
        if ("".equals(tenTheLoai)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
      
        return true;
    }
    
    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddType() == false) return;
        
        String tenTheLoai = tenSachInput.getText();
        
        
        TheLoaiDTO s = new TheLoaiDTO(-1, tenTheLoai);
        
        int maTL = TheLoaiBLL.insert(s);
        
        if (maTL >= 1) {
            tenSachInput.setText("");
            
            
            DefaultTableModel modelType = (DefaultTableModel) typeTable.getModel();
            modelType.addRow(new Object[]{maTL, tenTheLoai, "O", "X"});

            JOptionPane.showMessageDialog(rootPane, "Thêm Thể Loại thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnMouseClicked

    private void inputTypeNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTypeNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int dk = luaChonInput.getSelectedIndex();
            String value = inputTypeName.getText();
            
            

            ArrayList<TheLoaiDTO> List = TheLoaiBLL.getByCondition(dk == 0 ? "maTheLoai" : "tenTheLoai", value);
            DefaultTableModel modelType = (DefaultTableModel) typeTable.getModel();
            modelType.setRowCount(0);

            List.stream().forEach((s) -> {
                modelType.addRow(new Object[]{ s.getMaTL(), s.getTenTL(), "O", "X"});
            });
            
            if (List.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setTypeTable();
            } 
         
        }
    }//GEN-LAST:event_inputTypeNameKeyPressed

    private void inputTypeNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTypeNameFocusLost
        inputTypeName.setText("Nhập tên sách");
        inputTypeName.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputTypeNameFocusLost

    private void inputTypeNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTypeNameFocusGained
        inputTypeName.setText("");
        inputTypeName.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputTypeNameFocusGained

    private void luaChonInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luaChonInputActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_luaChonInputActionPerformed

    private void exportExcel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcel1MouseClicked
        ArrayList<TheLoaiDTO> tlList = TheLoaiBLL.getAllSach();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("theloai");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã thể loại");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên thể loại");

            int i = 1;
            for (TheLoaiDTO tg : tlList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tg.getMaTL());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(tg.getTenTL());

                i++;
            }

            File f = new File("D://theloai.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/theloai.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel1;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputTypeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JComboBox<String> luaChonInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField tenSachInput;
    private javax.swing.JTable typeTable;
    // End of variables declaration//GEN-END:variables
}
