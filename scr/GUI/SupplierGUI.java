/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
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
public class SupplierGUI extends javax.swing.JFrame {
    private TaiKhoanDTO tk;
    private NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    
    private JTextField tenNCC = new JTextField();
    private JTextField diaChi = new JTextField();
    private JTextField soDienThoai = new JTextField();
    
    private JPanel popUpUpdateNCC = getPopUpUpdateNCC();
    
    
    /**
     * Creates new form NhaXuatBanGUI
     */
    public SupplierGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        username.setText(tk.getTenDangNhap());
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
     
        setNCCTable();

        NCCTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        NCCTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());

        addEventNCCTable();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Kiểm tra giá trị nhập vào khi thêm nhà cung cấp 
    private boolean validateValueAddNCC() {
        String tenncc = tenNCCInput.getText();
        String diachi = diaChiInput.getText();
        String sdt = soDienThoaiInput.getText();
        
        
        if ("".equals(tenncc) || "".equals(diachi)|| "".equals(sdt) ) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b") == false){
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    // Kiểm tra giá trị nhập vào khi sửa nhà cung cấp 
    private boolean validateValueUpdateNCC() {
        String tenncc = tenNCC.getText();
        String diachi = diaChi.getText();
        String sdt = soDienThoai.getText();
        if ("".equals(tenncc) || "".equals(diachi) || "".equals(sdt) ) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
      if(sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b") == false){
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }       
        return true;
    }
    // Khung sửa nhà cung cấp
    private JPanel getPopUpUpdateNCC() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        tenNCC.setFont(font_16_plain);
        diaChi.setFont(font_16_plain);
        soDienThoai.setFont(font_16_plain);
        
        JLabel tenNCCLabel = new JLabel("Tên nhà cung cấp: ");
        tenNCCLabel.setFont(font_16_bold);
        
        JLabel diaChiLabel = new JLabel("Địa chỉ: ");
        diaChiLabel.setFont(font_16_bold);
        
        JLabel soDienThoaiLabel = new JLabel("Số điện thoại: ");
        soDienThoaiLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel tenNCCPanel = new JPanel();
        JPanel diaChiPanel = new JPanel();
        JPanel soDienThoaiPanel = new JPanel();
        

        containerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        tenNCCPanel.setLayout(new BorderLayout());
        diaChiPanel.setLayout(new BorderLayout());
        soDienThoaiPanel.setLayout(new BorderLayout());
        
        
        tenNCCPanel.add(tenNCCLabel, BorderLayout.NORTH);
        tenNCCPanel.add(tenNCC, BorderLayout.CENTER);
        
        diaChiPanel.add(diaChiLabel, BorderLayout.NORTH);
        diaChiPanel.add(diaChi, BorderLayout.CENTER);
        
        soDienThoaiPanel.add(soDienThoaiLabel, BorderLayout.NORTH);
        soDienThoaiPanel.add(soDienThoai, BorderLayout.CENTER);
        
        
        containerPanel.add(tenNCCPanel);
        containerPanel.add(diaChiPanel);
        containerPanel.add(soDienThoaiPanel);
        
        return containerPanel;
    }
    
    
    private void addEventNCCTable() {
        NCCTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = NCCTable.rowAtPoint(evt.getPoint());
                int col = NCCTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 4) {
                    int mancc = Integer.parseInt(String.valueOf(NCCTable.getValueAt(row, 0)));
                    String tenncc = String.valueOf(NCCTable.getValueAt(row, 1));
                    String diachi = String.valueOf(NCCTable.getValueAt(row, 2));
                    String sdt = String.valueOf(NCCTable.getValueAt(row, 3));
                    
                    tenNCC.setText(tenncc);
                    diaChi.setText(diachi);
                    soDienThoai.setText(sdt);
                  
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateNCC, 
                                "Mời sửa thông tin NCC " , JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION){
                        if (validateValueUpdateNCC() == false) return;
                        
                        tenncc = tenNCC.getText();
                        diachi = diaChi.getText();
                        sdt = soDienThoai.getText();
                        
                        NhaCungCapDTO ncc = new NhaCungCapDTO(mancc,tenncc,diachi,sdt);
                        
                        nhaCungCapBLL.update(ncc);
                        
                        updateNCCTable();
                        
                        tenNCC.setText("");
                        diaChi.setText("");
                        soDienThoai.setText("");
                    }
                    
                }
                
                if (row >= 0 && col == 5) {
                    int mancc = Integer.parseInt(String.valueOf(NCCTable.getValueAt(row, 0)));
                    showComfirmRemove(row, mancc);
                }
            }
        });
    }
    
    private void showComfirmRemove(int row, int mancc) {
        DefaultTableModel modelNCC = (DefaultTableModel) NCCTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelNCC.removeRow(row);
            nhaCungCapBLL.delete(mancc);
        }
    }
    
     private void setNCCTable() {
        int maNhaCungCap;
        String tenNhaCungCap;
        String diachi;
        String sdt;
        
            
        ArrayList<NhaCungCapDTO> NCCList = nhaCungCapBLL.getAll();
        DefaultTableModel modelNCC = (DefaultTableModel) NCCTable.getModel();
        modelNCC.setRowCount(0);

        for ( NhaCungCapDTO s : NCCList) {
            maNhaCungCap = s.getMaNhaCungCap();
            tenNhaCungCap = s.getTenNhaCungCap();
            diachi = s.getDiaChi();
            sdt = s.getSoDienThoai();
            modelNCC.addRow(new Object[]{maNhaCungCap,tenNhaCungCap,diachi,sdt, "O", "X"});
        }
    }
     
     private void updateNCCTable() {
        DefaultTableModel modelNCC = (DefaultTableModel) NCCTable.getModel();
        modelNCC.setRowCount(0);
        setNCCTable();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        inputSearch = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        NhaCungCap = new javax.swing.JScrollPane();
        NCCTable = new javax.swing.JTable();
        condition = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        diaChiInput = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        soDienThoaiInput = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        tenNCCInput = new javax.swing.JTextField();
        Header = new keeptoo.KGradientPanel();
        username = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();
        exportExcel = new javax.swing.JLabel();
        Footer = new keeptoo.KGradientPanel();
        backBtn = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel14.setText("Tìm nhà cung cấp:");

        inputSearch.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputSearch.setForeground(new java.awt.Color(102, 102, 102));
        inputSearch.setText("Tìm kiếm  ...");
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

        NhaCungCap.setBackground(new java.awt.Color(255, 255, 255));

        NCCTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        NCCTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã NCC ", "Tên NCC", "Địa Chỉ", "SĐT", "Sửa", "Xóa"
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
        NCCTable.setUpdateSelectionOnSort(false);
        NCCTable.setVerifyInputWhenFocusTarget(false);
        NhaCungCap.setViewportView(NCCTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NhaCungCap)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );

        condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã NCC", "Tên NCC"
            + "" }));
condition.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        conditionActionPerformed(evt);
    }
    });

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jLabel14)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(30, Short.MAX_VALUE))
        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(condition)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(inputSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel5.setPreferredSize(new java.awt.Dimension(442, 551));

    jPanel6.setBackground(new java.awt.Color(255, 255, 255));

    jLabel9.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
    jLabel9.setForeground(new java.awt.Color(255, 153, 0));
    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel9.setText("Thêm Nhà Cung Cấp");
    jLabel9.setPreferredSize(new java.awt.Dimension(130, 33));

    jLabel11.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
    jLabel11.setText("Tên nhà cung cấp :");
    jLabel11.setPreferredSize(new java.awt.Dimension(213, 25));

    jLabel12.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
    jLabel12.setText("Địa chỉ          :");
    jLabel12.setPreferredSize(new java.awt.Dimension(213, 25));

    diaChiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
    diaChiInput.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            diaChiInputActionPerformed(evt);
        }
    });

    jLabel13.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
    jLabel13.setText("Số điện thoại    :");
    jLabel13.setPreferredSize(new java.awt.Dimension(213, 25));

    soDienThoaiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
    soDienThoaiInput.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            soDienThoaiInputActionPerformed(evt);
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
    addBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addBtnActionPerformed(evt);
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

    tenNCCInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
    tenNCCInput.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            tenNCCInputActionPerformed(evt);
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
                    .addGap(40, 40, 40)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(61, 61, 61))
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(soDienThoaiInput))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(diaChiInput))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tenNCCInput, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 23, Short.MAX_VALUE))))
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(23, 23, 23)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tenNCCInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(diaChiInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(soDienThoaiInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(40, 40, 40)
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
        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    Header.setkEndColor(new java.awt.Color(255, 153, 0));
    Header.setkStartColor(new java.awt.Color(255, 255, 153));

    username.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
    username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
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

    javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
    Header.setLayout(HeaderLayout);
    HeaderLayout.setHorizontalGroup(
        HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(HeaderLayout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(username)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(44, 44, 44)
            .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18))
    );
    HeaderLayout.setVerticalGroup(
        HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(HeaderLayout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE))
    );

    Footer.setkEndColor(new java.awt.Color(255, 153, 0));
    Footer.setkStartColor(new java.awt.Color(255, 255, 153));

    backBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/back.png"))); // NOI18N
    backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            backBtnMouseClicked(evt);
        }
    });

    dateTimeLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
    dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    dateTimeLabel.setText("dateTimeLabel");
    dateTimeLabel.setPreferredSize(new java.awt.Dimension(158, 33));

    javax.swing.GroupLayout FooterLayout = new javax.swing.GroupLayout(Footer);
    Footer.setLayout(FooterLayout);
    FooterLayout.setHorizontalGroup(
        FooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(FooterLayout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addComponent(backBtn)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    FooterLayout.setVerticalGroup(
        FooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FooterLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addContainerGap())
        .addComponent(dateTimeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(Header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Footer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, 0))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0)
            .addComponent(Footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSearchActionPerformed

    private void conditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conditionActionPerformed

    private void diaChiInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaChiInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaChiInputActionPerformed

    private void soDienThoaiInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soDienThoaiInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soDienThoaiInputActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnActionPerformed

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == JOptionPane.OK_OPTION) {
            tenNCCInput.setText("");
            diaChiInput.setText("");
            soDienThoaiInput.setText("");
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetBtnActionPerformed

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        this.dispose();
        NhanVienDTO nv = new NhanVienBLL().getByNVid(tk.getMaNhanVien());
            
        switch (nv.getVaiTro()) {
            case "Quản lý" -> new ManagerMenuGUI(tk).setVisible(true);
            case "Nhân viên bán hàng" -> new SellEmployeeMenuGUI(tk).setVisible(true);
            case "Nhân viên nhập hàng" -> new ImportEmployeeMenuGUI(tk).setVisible(true);
        }
        
    }//GEN-LAST:event_backBtnMouseClicked

    private void logoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtnMouseClicked
        this.dispose();
        new LoginGUI();
    }//GEN-LAST:event_logoutBtnMouseClicked

    private void tenNCCInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenNCCInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenNCCInputActionPerformed

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        // TODO add your handling code here:
        if (validateValueAddNCC() == false)
            return;
        
        String tenncc = tenNCCInput.getText();
        String diachi = diaChiInput.getText();
        String sdt = soDienThoaiInput.getText();

        NhaCungCapDTO ncc = new NhaCungCapDTO(-1,tenncc,diachi,sdt);

        int mancc = nhaCungCapBLL.insert(ncc);
        
        if (mancc >= 1) {
            tenNCCInput.setText("");
            diaChiInput.setText("");
            soDienThoaiInput.setText("");
            

            DefaultTableModel modelNCC = (DefaultTableModel) NCCTable.getModel();
            modelNCC.addRow(new Object[] { mancc,tenncc,diachi,sdt, "O", "X" });

            JOptionPane.showMessageDialog(rootPane, "Thêm nhà cung cấp thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnMouseClicked

    private void inputSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int con =  condition.getSelectedIndex();
            String value = inputSearch.getText();
            String tenncc;
            String diachi;
            int mancc;
            String sdt;
            
            ArrayList<NhaCungCapDTO> NCCList = new ArrayList<>();
            
            if(con == 0){
                NCCList = nhaCungCapBLL.getByCondition("maNhaCungCap LIKE '%" + value + "%'");
            }else{
                NCCList = nhaCungCapBLL.getByCondition("tenNhaCungCap LIKE '%" + value + "%'");
            }
            
            DefaultTableModel modelNCC = (DefaultTableModel) NCCTable.getModel();
            modelNCC.setRowCount(0);

            if (NCCList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại  hoặc điều kiện kiện tìm kiếm không đúng","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setNCCTable();
            } else {
                for (NhaCungCapDTO s : NCCList) {
                    mancc = s.getMaNhaCungCap();
                    tenncc = s.getTenNhaCungCap();
                    diachi = s.getDiaChi();
                    sdt= s.getSoDienThoai();
                    modelNCC.addRow(new Object[]{mancc,tenncc,diachi,sdt, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputSearchKeyPressed

    private void inputSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusGained
        // TODO add your handling code here:
        inputSearch.setText("");
        inputSearch.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputSearchFocusGained

    private void inputSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusLost
        // TODO add your handling code here:
        inputSearch.setText("Tìm kiếm ...");
        inputSearch.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputSearchFocusLost

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<NhaCungCapDTO> nccList = nhaCungCapBLL.getAll();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhacungcap");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã nhà cung cấp ");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên nhà cung cấp");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số điện thoại");

            int i = 1;
            for (NhaCungCapDTO ncc : nccList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(ncc.getMaNhaCungCap());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(ncc.getTenNhaCungCap());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(ncc.getDiaChi());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(ncc.getSoDienThoai());

                i++;
            }

            File f = new File("D://nhacungcap.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/nhacungcap.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel Footer;
    private keeptoo.KGradientPanel Header;
    private javax.swing.JTable NCCTable;
    private javax.swing.JScrollPane NhaCungCap;
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JComboBox<String> condition;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JTextField diaChiInput;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField soDienThoaiInput;
    private javax.swing.JTextField tenNCCInput;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
