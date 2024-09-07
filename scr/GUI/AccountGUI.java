/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;


import BLL.NhanVienBLL;
import DTO.TaiKhoanDTO;
import BLL.TaiKhoanBLL;
import DTO.NhanVienDTO;
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
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
public final class AccountGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuEmployee
     */
    private TaiKhoanDTO tk;
    
    private TaiKhoanBLL TaiKhoanBLL = new TaiKhoanBLL();
    
    private JTextField username = new JTextField();
    private JTextField pass = new JTextField();
    private JPanel popUpUpdateAccount = getPopUpUpdateAccount();

    
    private JPanel getPopUpUpdateAccount() {
        Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
        Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
        
        pass.setFont(font_16_plain);
        username.setFont(font_16_plain);
        
        
        JLabel usernameLabel = new JLabel("Tên đăng nhập: ");
        usernameLabel.setFont(font_16_bold);
        
        JLabel passLabel = new JLabel("Mật Khẩu: ");
        passLabel.setFont(font_16_bold);
        
        JPanel containerPanel = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel passPanel = new JPanel();

        containerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        usernamePanel.setLayout(new BorderLayout());
        passPanel.setLayout(new BorderLayout());
        
        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(username, BorderLayout.CENTER);
        
        passPanel.add(passLabel, BorderLayout.NORTH);
        passPanel.add(pass, BorderLayout.CENTER);
        
        containerPanel.add(usernamePanel);
        containerPanel.add(passPanel);
        
        return containerPanel;
    }
    
    private void updateAccountTable() {
        DefaultTableModel modelAccount = (DefaultTableModel) accountTable.getModel();
        modelAccount.setRowCount(0);
        
        setAccountTable();
    }
    
   
    
    private void showComfirmRemove(int row, int maNV) {
        DefaultTableModel modelAccount = (DefaultTableModel) accountTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Question", 2) == 0) {
            modelAccount.removeRow(row);
            TaiKhoanBLL.delete(maNV);
        }
    }
    
    private boolean validateValueUpdateAccount() {
        String matKhau = pass.getText();
        String tenDangNhap = username.getText();
        
        
        if ("".equals(tenDangNhap) || "".equals(matKhau)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào");
            return false;
        }
        
        
        ArrayList<TaiKhoanDTO> tkList = TaiKhoanBLL.getAllSach();
        
        for (TaiKhoanDTO t : tkList) {
            if (tenDangNhap.equals(t.getTenDangNhap())) {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã có trong hệ thống");
                return false;
            }
        }
        
        return true;
    }
    

    
    private void setAccountTable() {
        int maNhanVien;
        String tenDangNhap;
        String matKhau;
        
            
        ArrayList<TaiKhoanDTO> accountList = TaiKhoanBLL.getAllSach();
        DefaultTableModel modelAccount = (DefaultTableModel) accountTable.getModel();

        for (TaiKhoanDTO s : accountList) {
            maNhanVien = s.getMaNhanVien();
            tenDangNhap = s.getTenDangNhap();
            matKhau = s.getMatKhau();
            
            

            modelAccount.addRow(new Object[]{maNhanVien, tenDangNhap, matKhau, "O", "X"});
        }
    }
    
    private void addEventAccountTable() {
        accountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = accountTable.rowAtPoint(evt.getPoint());
                int col = accountTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 3) {
                    int maNhanVien = Integer.parseInt(String.valueOf(accountTable.getValueAt(row, 0)));
                    String tenDangNhap = String.valueOf(accountTable.getValueAt(row, 1));
                    String matKhau = String.valueOf(accountTable.getValueAt(row, 2));
                    
                    username.setText(tenDangNhap);
                    pass.setText(matKhau);
                    
                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateAccount, 
                                "Mời sửa Tài Khoản " + maNhanVien, JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        if (validateValueUpdateAccount() == false) return;
                        
                        tenDangNhap  = username.getText();
                        matKhau  = pass.getText();
                        
                        TaiKhoanDTO s = new TaiKhoanDTO(maNhanVien, tenDangNhap, matKhau);
                        
                        TaiKhoanBLL.update(s);
                        
                        updateAccountTable();
                        
                        username.setText("");
                        pass.setText("");
                        
                    }
                }
                
                if (row >= 0 && col == 4) {
                    int maNhanVien = Integer.parseInt(String.valueOf(accountTable.getValueAt(row, 0)));
                    showComfirmRemove(row, maNhanVien);
                    
                }
            }
        });
    }
    
    private void setJComboBox() {
        ArrayList<NhanVienDTO> nhanVienList = new NhanVienBLL().getEmployeeUnAccount();
        maNhanVienInput.addItem("");
        
        maNhanVienInput.setSelectedIndex(0);
        
        
        
        for (NhanVienDTO nv : nhanVienList) {
            maNhanVienInput.addItem(nv.getMaNhanVien() + " - " + nv.getTen());
        }

    }
    
    public AccountGUI(TaiKhoanDTO tk) {
        initComponents();
        
        this.tk = tk;
        
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        
        infoUser.setText(tk.getTenDangNhap());
        
        accountTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        accountTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
        
        setAccountTable();
        addEventAccountTable();
        
        setJComboBox();
        
        this.setTitle("Tài khoản");
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
        jLabel2 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tenDangNhapInput = new javax.swing.JTextField();
        maNhanVienInput = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        matKhauInput = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        inputAccountName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        luaChonInput = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        infoUser = new javax.swing.JLabel();
        exportExcel1 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        dateTimeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sách");
        setBackground(new java.awt.Color(255, 204, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm tài khoản");

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel2.setText("Mã Nhân Viên       :");

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

        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel4.setText("Mật Khẩu           :");

        tenDangNhapInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        maNhanVienInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel3.setText("Tên Đăng Nhập      :");

        matKhauInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(maNhanVienInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addComponent(tenDangNhapInput)
                            .addComponent(matKhauInput))))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maNhanVienInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tenDangNhapInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(matKhauInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        inputAccountName.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        inputAccountName.setForeground(new java.awt.Color(102, 102, 102));
        inputAccountName.setText("Nhập Thông Tin Tài Khoản");
        inputAccountName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputAccountNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputAccountNameFocusLost(evt);
            }
        });
        inputAccountName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputAccountNameActionPerformed(evt);
            }
        });
        inputAccountName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputAccountNameKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel12.setText("Tìm Tài Khoản :");

        accountTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Đăng Nhập", "Mật Khẩu", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        accountTable.setEditingColumn(1);
        accountTable.setEditingRow(1);
        jScrollPane3.setViewportView(accountTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        luaChonInput.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        luaChonInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Nhân Viên", "Tên Đăng Nhập" }));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputAccountName, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(luaChonInput, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(inputAccountName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(luaChonInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 153));

        infoUser.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        infoUser.setForeground(new java.awt.Color(51, 51, 51));
        infoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoUser.setText("NV1 - Biện Thành Hưng");

        exportExcel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/import-excel.png"))); // NOI18N
        exportExcel1.setToolTipText("Xuất Excel");
        exportExcel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportExcel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportExcel1MouseClicked(evt);
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
                .addComponent(infoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(infoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(exportExcel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
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
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?","Question", 2) == JOptionPane.OK_OPTION) {
            maNhanVienInput.setSelectedIndex(0);
            tenDangNhapInput.setText("");
            
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private boolean validateValueAddAccount() {
        String maNhanVien = (String) maNhanVienInput.getSelectedItem();
        String tenDangNhap = tenDangNhapInput.getText();
        String matKhau = tenDangNhapInput.getText();
        
        
        if ("".equals(maNhanVien) || "".equals(matKhau) || "".equals(tenDangNhap)) {
            JOptionPane.showMessageDialog(this, "Không được để trống bất kì trường nào");
            return false;
        }
        
     
        return true;
    }
    
    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        if (validateValueAddAccount() == false) return;
        
        int maNhanVien = Integer.parseInt(String.valueOf(maNhanVienInput.getSelectedItem()));
        String tenDangNhap = tenDangNhapInput.getText();
        String matKhau = matKhauInput.getText();
        
        
        TaiKhoanDTO s = new TaiKhoanDTO(maNhanVien, tenDangNhap, matKhau);
        
        if (TaiKhoanBLL.insert(s)) {
            maNhanVienInput.setSelectedIndex(0);
            tenDangNhapInput.setText("");
            matKhauInput.setText("");
            setJComboBox();
            
            
            DefaultTableModel modelAccount = (DefaultTableModel) accountTable.getModel();
            modelAccount.addRow(new Object[]{maNhanVien, tenDangNhap, matKhau, "O", "X"});

            JOptionPane.showMessageDialog(rootPane, "Thêm Tài Khoản thành công");
        }
    }//GEN-LAST:event_addBtnMouseClicked

    
    
    private void inputAccountNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputAccountNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String value = inputAccountName.getText();
            int dk = luaChonInput.getSelectedIndex();
            
            
            ArrayList<TaiKhoanDTO> accountList = TaiKhoanBLL.getByCondition(dk == 0 ? "maNhanVien" : "tenDangNhap" ,value );
            DefaultTableModel modelAccount = (DefaultTableModel) accountTable.getModel();
            modelAccount.setRowCount(0);

           accountList.stream().forEach((s) -> {
                modelAccount.addRow(new Object[]{ s.getMaNhanVien(), s.getTenDangNhap(), s.getMatKhau(), "O", "X"});
            });
            
            if (accountList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại trong cơ sở dữ liệu");
                setAccountTable();
            } 
        }
    }//GEN-LAST:event_inputAccountNameKeyPressed

    private void inputAccountNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputAccountNameFocusLost
        inputAccountName.setText("Nhập thông tin tài khoản");
        inputAccountName.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputAccountNameFocusLost

    private void inputAccountNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputAccountNameFocusGained
        inputAccountName.setText("");
        inputAccountName.setForeground(new Color(51, 51, 51));
    }//GEN-LAST:event_inputAccountNameFocusGained

    private void inputAccountNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputAccountNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputAccountNameActionPerformed

    private void luaChonInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luaChonInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_luaChonInputActionPerformed

    private void exportExcel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcel1MouseClicked
        ArrayList<TaiKhoanDTO> tkList = TaiKhoanBLL.getAllSach();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("taikhoan");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã nhân viên");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên đăng nhập");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mật khẩu");

            int i = 1;
            for (TaiKhoanDTO taiKhoan : tkList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(taiKhoan.getMaNhanVien());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(taiKhoan.getTenDangNhap());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(taiKhoan.getMatKhau());

                i++;
            }

            File f = new File("D://taikhoan.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/taikhoan.xlsx");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel backBtn;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel1;
    private javax.swing.JLabel infoUser;
    private javax.swing.JTextField inputAccountName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JComboBox<String> maNhanVienInput;
    private javax.swing.JTextField matKhauInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField tenDangNhapInput;
    // End of variables declaration//GEN-END:variables
}
