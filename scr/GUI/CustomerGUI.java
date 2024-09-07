/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import BLL.KhachHangBLL;
import BLL.NhanVienBLL;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tan
 */
public class CustomerGUI extends javax.swing.JFrame {
    /**
     * Creates new form KhachHangGUI
     */
    
    private TaiKhoanDTO tk;
    
    private KhachHangBLL khachHangBLL = new KhachHangBLL();
    
    private JTextField ten = new JTextField();
    private JComboBox gioiTinh = new JComboBox<String>(new String[] {"Nam","Nữ"});
    private JTextField sdt = new JTextField();
    private JTextField namSinh = new JTextField();
    private JPanel popUpUpdateKH = getPopUpUpdateKH();

      
    
    public CustomerGUI(TaiKhoanDTO tk){
        initComponents();
        
        this.tk = tk;
        
        username.setText(tk.getTenDangNhap());
     
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
     
        setKHTable();
     
        KHTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        KHTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
    
        addEventKHTable();
     
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private boolean validateValueAddKH() {
        String tenKH = tenKHInput.getText();
        String gt = (String) genderInput.getSelectedItem();
        String sDT = sdtInput.getText();
        String nSinh = namSinhInput.getText();
        
        
        if ("".equals(tenKH) || "".equals(gt) || "".equals(sDT)
                || "".equals(nSinh) ) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        
        
        ArrayList<KhachHangDTO> KHList = khachHangBLL.getAll();

        for (KhachHangDTO s : KHList) {
            if (s.getSoDienThoai().equals(sDT)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng đã tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        
        if (nSinh.matches("[0-9]\\d{1,}") == false) {
            JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if(sDT.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b") == false){
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }       
        
        return true;
    }
    private boolean validateValueUpdateKH(int maKhachHang) {
        String tenKH = ten.getText();
        String gt = (String) gioiTinh.getSelectedItem();
        String sDT = sdt.getText();
        String nSinh = namSinh.getText();
        
        
        if ("".equals(tenKH) || "".equals(gt) || "".equals(sDT)
                || "".equals(nSinh) ) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        ArrayList<KhachHangDTO> KHList = khachHangBLL.getAll();

        for (KhachHangDTO s : KHList) {
            if (s.getSoDienThoai().equals(sDT) && s.getMaKhachHang() != maKhachHang) {
                JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng đã tồn tại trong cơ sở dữ liệu","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
      
        if (nSinh.matches("[0-9]\\d{1,}") == false) {
            JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if(sDT.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b") == false){
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    private JPanel getPopUpUpdateKH() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        ten.setFont(font_16_plain);
        gioiTinh.setFont(font_16_plain);
        sdt.setFont(font_16_plain);
        namSinh.setFont(font_16_plain);
        
        
        JLabel tenLabel = new JLabel("Tên khách hàng: ");
        tenLabel.setFont(font_16_bold);
        
        JLabel gioiTinhLabel = new JLabel("Giới tính: ");
        gioiTinhLabel.setFont(font_16_bold);
        
        JLabel sdtLabel = new JLabel("Số điện thoại: ");
        sdtLabel.setFont(font_16_bold);
        
        JLabel namSinhLabel = new JLabel("Năm sinh: ");
        namSinhLabel.setFont(font_16_bold);
        
        
        
        JPanel containerPanel = new JPanel();
        JPanel tenPanel = new JPanel();
        JPanel gioiTinhPanel = new JPanel();
        JPanel sdtPanel = new JPanel();
        JPanel namSinhPanel = new JPanel();
        

        containerPanel.setLayout(new GridLayout(3, 3, 10, 10));
        tenPanel.setLayout(new BorderLayout());
        gioiTinhPanel.setLayout(new BorderLayout());
        sdtPanel.setLayout(new BorderLayout());
        namSinhPanel.setLayout(new BorderLayout());
        
        
        tenPanel.add(tenLabel, BorderLayout.NORTH);
        tenPanel.add(ten, BorderLayout.CENTER);
        
        gioiTinhPanel.add(gioiTinhLabel, BorderLayout.NORTH);
        gioiTinhPanel.add(gioiTinh, BorderLayout.CENTER);
        
        sdtPanel.add(sdtLabel, BorderLayout.NORTH);
        sdtPanel.add(sdt, BorderLayout.CENTER);
        
        namSinhPanel.add(namSinhLabel, BorderLayout.NORTH);
        namSinhPanel.add(namSinh, BorderLayout.CENTER);
        
        containerPanel.add(tenPanel);
        containerPanel.add(gioiTinhPanel);
        containerPanel.add(sdtPanel);
        containerPanel.add(namSinhPanel);
       
        
        return containerPanel;
    }
    
     
     
     private void addEventKHTable() {
        KHTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = KHTable.rowAtPoint(evt.getPoint());
                int col = KHTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 5) {
                    int maKH = Integer.parseInt(String.valueOf(KHTable.getValueAt(row, 0)));
                    String tenKH = String.valueOf(KHTable.getValueAt(row, 1));
                    String gt = String.valueOf(KHTable.getValueAt(row, 2));
                    String sDT = String.valueOf(KHTable.getValueAt(row, 3));
                    int nSinh = Integer.parseInt(String.valueOf(KHTable.getValueAt(row, 4)));
                    
                    ten.setText(tenKH);
                    gioiTinh.setSelectedItem(gt);
                    sdt.setText(sDT);
                    namSinh.setText(nSinh + "");
                  
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateKH, 
                                "Mời sửa thông tin khách hàng " , JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION){
                        if (validateValueUpdateKH(maKH) == false) return;
                        
                        tenKH = ten.getText();
                        gt = (String) gioiTinh.getSelectedItem();
                        sDT = sdt.getText();
                        nSinh = Integer.parseInt(namSinh.getText());
                        
                        KhachHangDTO kh = new KhachHangDTO(maKH,tenKH,gt,sDT,nSinh);
                        
                        khachHangBLL.update(kh);
                        
                        updateKHTable();
                        
                        ten.setText("");
                        gioiTinh.setSelectedItem("");
                        sdt.setText("");
                        namSinh.setText("");
                    }
                }
                
                if (row >= 0 && col == 6) {
                    int maKH = Integer.parseInt(String.valueOf(KHTable.getValueAt(row, 0)));
                    showComfirmRemove(row, maKH);
                }
            }
        });
    }
     
     private void showComfirmRemove(int row, int ma) {
        DefaultTableModel modelKH = (DefaultTableModel) KHTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelKH.removeRow(row);
            khachHangBLL.delete(ma);
        }
    }
    
    private void setKHTable() {
        int maKH;
        String tenKH;
        String gt;
        String soDienThoai;
        int nSinh;
        
            
        ArrayList<KhachHangDTO> KHList = khachHangBLL.getAll();
        DefaultTableModel modelKH = (DefaultTableModel) KHTable.getModel();
        modelKH.setRowCount(0);

        for (KhachHangDTO s : KHList) {
            maKH = s.getMaKhachHang();
            tenKH = s.getTen();
            gt = s.getGioiTinh();
            soDienThoai = s.getSoDienThoai();
            nSinh = s.getNamSinh();

            modelKH.addRow(new Object[]{maKH,tenKH,gt,soDienThoai,nSinh, "O", "X"});
        }
    }
    
      private void updateKHTable() {
        DefaultTableModel modelBook = (DefaultTableModel) KHTable.getModel();
        modelBook.setRowCount(0);
        
        setKHTable();
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tenKHInput = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        genderInput = new javax.swing.JComboBox<String>(new String[]{"Nam", "Nữ"});
        jLabel12 = new javax.swing.JLabel();
        sdtInput = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        namSinhInput = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        inputSearch = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        abc = new javax.swing.JScrollPane();
        KHTable = new javax.swing.JTable();
        condition = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        exportExcel = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        dateTimeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Khách Hàng");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setPreferredSize(new java.awt.Dimension(442, 551));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thêm Khách Hàng");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 33));

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel10.setText("Tên khách hàng   :");
        jLabel10.setPreferredSize(new java.awt.Dimension(213, 25));

        tenKHInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tenKHInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenKHInputActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel11.setText("Giới tính        :");
        jLabel11.setPreferredSize(new java.awt.Dimension(213, 25));

        genderInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderInputActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel12.setText("Số điện thoại    :");
        jLabel12.setPreferredSize(new java.awt.Dimension(213, 25));

        sdtInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        sdtInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdtInputActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenKHInput))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderInput, 0, 247, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sdtInput))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namSinhInput)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tenKHInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(genderInput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sdtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namSinhInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel14.setText("Tìm khách hàng :");

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

        abc.setBackground(new java.awt.Color(255, 255, 255));

        KHTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        KHTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng ", "Giới Tính", "Số Điện Thoại", "Năm Sinh", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
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
        KHTable.setUpdateSelectionOnSort(false);
        KHTable.setVerifyInputWhenFocusTarget(false);
        abc.setViewportView(KHTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(abc)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(abc, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã khách hàng", "Tên khách hàng", "Số điện thoại" }));
        condition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conditionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(inputSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtnMouseClicked(evt);
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
                .addComponent(exportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(exportExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 153));

        dateTimeLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeLabel.setText("dateTimeLabel");
        dateTimeLabel.setPreferredSize(new java.awt.Dimension(158, 33));

        backBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/back.png"))); // NOI18N
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genderInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderInputActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBtnActionPerformed

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

    private void conditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conditionActionPerformed

    private void inputSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusLost
        // TODO add your handling code here:
        inputSearch.setText("Tìm kiếm ...");
        inputSearch.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputSearchFocusLost

    private void tenKHInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenKHInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenKHInputActionPerformed

    private void sdtInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdtInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sdtInputActionPerformed

    private void namSinhInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namSinhInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namSinhInputActionPerformed

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<KhachHangDTO> khList = khachHangBLL.getAll();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhaxuatban");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khách hàng");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên khách hàng");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Năm sinh");

            int i = 1;
            for (KhachHangDTO kh : khList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(kh.getMaKhachHang());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(kh.getTen());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(kh.getGioiTinh());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(kh.getSoDienThoai());
                
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(kh.getNamSinh());

                i++;
            }

            File f = new File("D://khachhang.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/khachhang.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcelMouseClicked

    private void inputSDTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_inputSDTActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_inputSDTActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField3ActionPerformed
    
       private void inputSearchKeyPressed(java.awt.event.KeyEvent evt) {                                         
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int con =  condition.getSelectedIndex();
            String value = inputSearch.getText();
            int maKH;
            String tenKH;
            String gt;
            String sDT;
            int nSinh;
            
            ArrayList<KhachHangDTO> KHList = new ArrayList<>();
            
            KHList = switch (con) {
                case 0 -> khachHangBLL.getByCondition("maKhachHang LIKE '%" + value + "%'");
                case 1 -> khachHangBLL.getByCondition("tenKhachHang LIKE '%" + value + "%'");
                default -> khachHangBLL.getByCondition("soDienThoai LIKE '%" + value + "%'");
            };
            
            DefaultTableModel modelKH = (DefaultTableModel) KHTable.getModel();
            modelKH.setRowCount(0);

            if (KHList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại  hoặc điều kiện kiện tìm kiếm không đúng","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setKHTable();
            } else {
                for (KhachHangDTO s : KHList) {
                    maKH = s.getMaKhachHang();
                    tenKH = s.getTen();
                    gt = s.getGioiTinh();
                    sDT = s.getSoDienThoai();
                    nSinh= s.getNamSinh();
                    
                    
                    modelKH.addRow(new Object[]{maKH,tenKH,gt,sDT,nSinh, "O", "X"});
                }
            }
        }
    }                                        

    
    
    private void inputSearchFocusGained(java.awt.event.FocusEvent evt) {                                          
        inputSearch.setText("");
        inputSearch.setForeground(new Color(51, 51, 51));
    }                                         

                
    
    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddKH() == false)
            return;
        
        String tenKH = tenKHInput.getText();
        String gt = (String) genderInput.getSelectedItem();
        String sDT = sdtInput.getText();
        int nSinh = Integer.parseInt(namSinhInput.getText());

        KhachHangDTO kh = new KhachHangDTO(-1, tenKH, gt, sDT, nSinh);
        
        int maKH = khachHangBLL.insert(kh);
        
        if (maKH >= 0) {
            tenKHInput.setText("");
            genderInput.setSelectedIndex(0);
            sdtInput.setText("");
            namSinhInput.setText("");
            

            DefaultTableModel modelKH = (DefaultTableModel) KHTable.getModel();
            modelKH.addRow(new Object[] { maKH,tenKH,gt,sDT,nSinh, "O", "X" });

            JOptionPane.showMessageDialog(rootPane, "Thêm khách hàng thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }// GEN-LAST:event_addBtnMouseClicked

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_resetBtnMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == JOptionPane.OK_OPTION) {
            tenKHInput.setText("");
            genderInput.setSelectedIndex(0);
            sdtInput.setText("");
            namSinhInput.setText("");
        }
    }// GEN-LAST:event_resetBtnMouseClicked

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_resetBtnActionPerformed

    
    

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_genderActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_genderActionPerformed
    
     private void inputSearchActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }    

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable KHTable;
    private javax.swing.JScrollPane abc;
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JComboBox<String> condition;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JComboBox<String> genderInput;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JLabel logoutBtn;
    private javax.swing.JTextField namSinhInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField sdtInput;
    private javax.swing.JTextField tenKHInput;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
