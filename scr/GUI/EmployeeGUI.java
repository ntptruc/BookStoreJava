/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
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
public class EmployeeGUI extends javax.swing.JFrame {
    private TaiKhoanDTO tk;
    private NhanVienBLL nhanVienBLL = new NhanVienBLL();

    private JTextField tenNV = new JTextField();
    private JTextField namSinh = new JTextField();
    private JComboBox gioiTinh = new JComboBox<String>(new String[] {"Nam","Nữ"});
    private JTextField sDT = new JTextField();
    private JTextField luong = new JTextField();
    private JTextField soNgayDaNghi = new JTextField();
    private JComboBox vaiTro = new JComboBox<String>(new String[] {"Quản lý", "Nhân viên bán hàng", "Nhân viên nhập hàng"});

    private JPanel popUpUpdateNV = getPopUpUpdateNV();

        /**
         * Creates new form NhanVienGUI
         */
    public EmployeeGUI(TaiKhoanDTO tk) {
        initComponents();
        this.tk = tk;
        
        username.setText(tk.getTenDangNhap());
        
        setNVTable();
        Thread th = new ClockLabel(dateTimeLabel);
        th.start();
        addEventKHTable();

        NVTable.getColumnModel().getColumn(5).setCellRenderer(new CurrencyTableCellRenderer());
        NVTable.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
        NVTable.getColumn("Sửa").setCellRenderer(new ButtonRenderer());

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
        
    private void showComfirmRemove(int row, int manv) {
        DefaultTableModel modelNV = (DefaultTableModel) NVTable.getModel();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == 0) {
            modelNV.removeRow(row);
            nhanVienBLL.delete(manv);
        }
    }
        
    private boolean validateValueUpdateNV() {
           String tennv = tenNV.getText();
           String namsinh = namSinh.getText();
           String gioitinh = (String) gioiTinh.getSelectedItem();
           String sdt = sDT.getText();
           String lg = luong.getText();
           String songaydanghi = soNgayDaNghi.getText();
           String vaitro = (String) vaiTro.getSelectedItem();

           if ("".equals(tennv) || "".equals(namsinh) || "".equals(gioitinh) || "".equals(sdt)
                           || "".equals(lg) || "".equals(songaydanghi) || "".equals(vaitro))

           {
                   JOptionPane.showMessageDialog(this, "Không được để trống bất kỳ trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   return false;
           }


           if (namsinh.matches("[0-9]\\d{1,}") == false) {
                   JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   return false;
           }

           if (sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b") == false) {
                   JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   return false;
           }

           int lgInt = (int) checkInputNumberValue(lg, "Lương");
           if (lgInt < 0) {
                   return false;
           }

           int sndnInt = (int) checkInputNumberValue(songaydanghi, "Số ngày đã nghỉ");
           if (sndnInt < 0) {
                   return false;
           }

           return true;
   }
        
    private void updateNVTable() {
        DefaultTableModel modelNV = (DefaultTableModel) NVTable.getModel();
        modelNV.setRowCount(0);

        setNVTable();
    }
    
         
    private void addEventKHTable() {
        NVTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = NVTable.rowAtPoint(evt.getPoint());
                int col = NVTable.columnAtPoint(evt.getPoint());

                if (row >= 0 && col == 8) {
                    int manv = Integer.parseInt(String.valueOf(NVTable.getValueAt(row, 0)));
                    String tennv = String.valueOf(NVTable.getValueAt(row, 1));
                    int namsinh = Integer.parseInt(String.valueOf(NVTable.getValueAt(row, 2)));
                    String gioitinh = String.valueOf(NVTable.getValueAt(row, 3));
                    String sdt = String.valueOf(NVTable.getValueAt(row, 4));
                    long lg = Long.parseLong(String.valueOf(NVTable.getValueAt(row, 5)));
                    int sndn= Integer.parseInt(String.valueOf(NVTable.getValueAt(row, 6)));
                    String vaitro = String.valueOf(NVTable.getValueAt(row, 7));

                    tenNV.setText(tennv);
                    namSinh.setText(namsinh + "");
                    gioiTinh.setSelectedItem(gioitinh);
                    sDT.setText(sdt);
                    luong.setText(lg + "");
                    soNgayDaNghi.setText(sndn +"");
                    vaiTro.setSelectedItem(vaitro);

                    int result = JOptionPane.showConfirmDialog(null, popUpUpdateNV, 
                                "Mời sửa thông tin nhân viên " , JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION){
                        if (validateValueUpdateNV() == false) return;

                        tennv = tenNV.getText();
                        namsinh = Integer.parseInt(namSinh.getText());
                        gioitinh = (String) gioiTinh.getSelectedItem();
                        sdt = sDT.getText();
                        lg = Long.parseLong(luong.getText());
                        sndn = Integer.parseInt(soNgayDaNghi.getText());
                        vaitro = (String) vaiTro.getSelectedItem();

                        NhanVienDTO nv = new NhanVienDTO(manv,tennv,namsinh,gioitinh,sdt,lg,sndn,vaitro);

                        nhanVienBLL.update(nv);

                        updateNVTable();

                        tenNV.setText("");
                        namSinh.setText("");
                        gioiTinh.setSelectedIndex(0);
                        sDT.setText("");
                        luong.setText("");
                        soNgayDaNghi.setText("");
                        vaiTro.setSelectedIndex(0);
                    }
                }


                if (row >= 0 && col == 9) {
                    int ma = Integer.parseInt(String.valueOf(NVTable.getValueAt(row, 0)));
                    showComfirmRemove(row, ma);
                }


            }
        });
    }
    

        private JPanel getPopUpUpdateNV(){
            Font font_16_plain = new Font("Monospaced", Font.PLAIN, 16);
            Font font_16_bold = new Font("Monospaced", Font.BOLD, 16);
            
            tenNV.setFont(font_16_plain);
            namSinh.setFont(font_16_plain);
            gioiTinh.setFont(font_16_plain);
            sDT.setFont(font_16_plain);
            luong.setFont(font_16_plain);
            soNgayDaNghi.setFont(font_16_plain);
            vaiTro.setFont(font_16_plain);
            
            JLabel tenNVLabel = new JLabel("Tên nhân viên : ");
            tenNVLabel.setFont(font_16_bold);

            JLabel namSinhLabel = new JLabel("Năm sinh : ");
            namSinhLabel.setFont(font_16_bold);

            JLabel gioiTinhLabel = new JLabel("Giới tính : ");
            gioiTinhLabel.setFont(font_16_bold);
            
            JLabel sDTLabel = new JLabel("Số điện thoại: ");
            sDTLabel.setFont(font_16_bold);

            JLabel luongLabel = new JLabel("Mức lương: ");
            luongLabel.setFont(font_16_bold);

            JLabel soNgayDaNghiLabel = new JLabel("Số ngày đã nghỉ: ");
            soNgayDaNghiLabel.setFont(font_16_bold);

            JLabel vaiTroLabel = new JLabel("Vai trò: ");
            vaiTroLabel.setFont(font_16_bold);
            
            JPanel containerPanel = new JPanel();
            JPanel tenPanel = new JPanel();
            JPanel namsinhPanel = new JPanel();
            JPanel gioitinhPanel = new JPanel();
            JPanel sdtPanel = new JPanel();
            JPanel luongPanel = new JPanel();
            JPanel sndnPanel = new JPanel();
            JPanel vaitroPanel = new JPanel();
            
            containerPanel.setLayout(new GridLayout(3, 3, 10, 10));
            tenPanel.setLayout(new BorderLayout());
            namsinhPanel.setLayout(new BorderLayout());
            gioitinhPanel.setLayout(new BorderLayout());
            sdtPanel.setLayout(new BorderLayout());
            luongPanel.setLayout(new BorderLayout());
            sndnPanel.setLayout(new BorderLayout());
            vaitroPanel.setLayout(new BorderLayout());
            
            
            tenPanel.add(tenNVLabel, BorderLayout.NORTH);
            tenPanel.add(tenNV, BorderLayout.CENTER);
            
            namsinhPanel.add(namSinhLabel, BorderLayout.NORTH);
            namsinhPanel.add(namSinh, BorderLayout.CENTER);
            
            gioitinhPanel.add(gioiTinhLabel, BorderLayout.NORTH);
            gioitinhPanel.add(gioiTinh, BorderLayout.CENTER);
            
            sdtPanel.add(sDTLabel, BorderLayout.NORTH);
            sdtPanel.add(sDT, BorderLayout.CENTER);
            
            luongPanel.add(luongLabel, BorderLayout.NORTH);
            luongPanel.add(luong, BorderLayout.CENTER);
            
            sndnPanel.add(soNgayDaNghiLabel, BorderLayout.NORTH);
            sndnPanel.add(soNgayDaNghi, BorderLayout.CENTER);
            
            vaitroPanel.add(vaiTroLabel, BorderLayout.NORTH);
            vaitroPanel.add(vaiTro, BorderLayout.CENTER);
            
            containerPanel.add(tenPanel);
            containerPanel.add(namsinhPanel);
            containerPanel.add(gioitinhPanel);
            containerPanel.add(sdtPanel);
            containerPanel.add(luongPanel);
            containerPanel.add(sndnPanel);
            containerPanel.add(vaitroPanel);
            
            return containerPanel;
            
        }
        
        private long checkInputNumberValue(String value, String name) {
                long num;
                if (value == null)
                        return -1;

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

        private boolean validateValueAddNV() {
                String tennv = tenNVInput.getText();
                String namsinh = namSinhInput.getText();
                String gioitinh = (String) gioiTinhInput.getSelectedItem();
                String sdt = sDTInput.getText();
                String lg = luongInput.getText();
                String songaydanghi = soNgayDaNghiInput.getText();
                String vaitro = (String) vaiTroInput.getSelectedItem();

                if ("".equals(tennv) || "".equals(namsinh) || "".equals(gioitinh) || "".equals(sdt)
                                || "".equals(lg) || "".equals(songaydanghi) || "".equals(vaitro))

                {
                        JOptionPane.showMessageDialog(this, "Không được để trống bất kỳ trường nào","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                }

                if (namsinh.matches("[0-9]\\d{1,}") == false) {
                        JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                }

                if (sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b") == false) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                }

                int lgInt = (int) checkInputNumberValue(lg, "Lương");
                if (lgInt < 0) {
                        return false;
                }

                int sndnInt = (int) checkInputNumberValue(songaydanghi, "Số ngày đã nghỉ");
                if (sndnInt < 0) {
                        return false;
                }

                return true;
        }
        
         private void setNVTable() {
            int manv;
            String tennv;
            int namsinh;
            String gioitinh;
            String sdt;
            long lg;
            int sndn;
            String vaitro;



            ArrayList<NhanVienDTO> NVList = nhanVienBLL.getAll();
            DefaultTableModel modelNV = (DefaultTableModel) NVTable.getModel();
            modelNV.setRowCount(0);

            for (NhanVienDTO nv : NVList) {
                manv = nv.getMaNhanVien();
                tennv = nv.getTen();
                namsinh = nv.getNamSinh();
                gioitinh = nv.getGioiTinh();
                sdt = nv.getSoDienThoai();
                lg = nv.getLuong();
                sndn = nv.getSoNgayNghi();
                vaitro = nv.getVaiTro();
                
                modelNV.addRow(new Object[]{manv, tennv, namsinh, gioitinh, sdt, lg, sndn, vaitro, "O", "X"});
            }
        }
        
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tenNVInput = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        sDTInput = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        luongInput = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        gioiTinhInput = new javax.swing.JComboBox<String>(new String[] {"Nam","Nữ"});
        jLabel18 = new javax.swing.JLabel();
        vaiTroInput = new javax.swing.JComboBox<String>(new String[] {"Nhân viên bán hàng", "Nhân viên nhập hàng","Quản lý"});
        soNgayDaNghiInput = new javax.swing.JTextField();
        namSinhInput = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        inputSearch = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        NVTable = new javax.swing.JTable();
        condition = new javax.swing.JComboBox<>();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        exportExcel = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        dateTimeLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setPreferredSize(new java.awt.Dimension(442, 551));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thêm Nhân Viên");
        jLabel9.setPreferredSize(new java.awt.Dimension(130, 33));

        jLabel10.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel10.setText("Số điện thoại     :");
        jLabel10.setPreferredSize(new java.awt.Dimension(213, 25));

        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel11.setText("Tên nhân viên     :");
        jLabel11.setPreferredSize(new java.awt.Dimension(213, 25));

        jLabel12.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel12.setText("Năm sinh          :");
        jLabel12.setPreferredSize(new java.awt.Dimension(213, 25));

        tenNVInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        tenNVInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenNVInputActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel13.setText("Giới tính         :");
        jLabel13.setPreferredSize(new java.awt.Dimension(213, 25));

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

        sDTInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        sDTInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sDTInputActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel16.setText("Lương             :");
        jLabel16.setPreferredSize(new java.awt.Dimension(213, 25));

        luongInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        luongInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luongInputActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel17.setText("Số ngày đã nghỉ   :");
        jLabel17.setPreferredSize(new java.awt.Dimension(213, 25));

        gioiTinhInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gioiTinhInputActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel18.setText("Vai trò           :");
        jLabel18.setPreferredSize(new java.awt.Dimension(213, 25));

        vaiTroInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaiTroInputActionPerformed(evt);
            }
        });

        soNgayDaNghiInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        soNgayDaNghiInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soNgayDaNghiInputActionPerformed(evt);
            }
        });

        namSinhInput.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        namSinhInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namSinhInputActionPerformed(evt);
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
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vaiTroInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soNgayDaNghiInput))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(luongInput))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sDTInput))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gioiTinhInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(namSinhInput))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tenNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenNVInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namSinhInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gioiTinhInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sDTInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(luongInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soNgayDaNghiInput, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vaiTroInput))
                .addGap(66, 66, 66)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
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
        jLabel14.setText("Tìm nhân viên :");

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

        NVTable.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        NVTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Năm Sinh", "Giới Tính", "SĐT", "Lương", "Ngày đã nghỉ", "Vai Trò", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        NVTable.setUpdateSelectionOnSort(false);
        NVTable.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(NVTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );

        condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nv", "Tên nv", "SĐT" }));
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
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

        username.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

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
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(exportExcel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 153));

        dateTimeLabel.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeLabel.setText("jLabel8");
        dateTimeLabel.setPreferredSize(new java.awt.Dimension(158, 33));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/back.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(70, 70, 70))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sDTInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sDTInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sDTInputActionPerformed

    private void tenNVInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenNVInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenNVInputActionPerformed

    private void luongInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luongInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_luongInputActionPerformed

    private void soNgayDaNghiInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soNgayDaNghiInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soNgayDaNghiInputActionPerformed

    private void vaiTroInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaiTroInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vaiTroInputActionPerformed

    private void gioiTinhInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gioiTinhInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gioiTinhInputActionPerformed

    private void namSinhInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namSinhInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namSinhInputActionPerformed

    private void conditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conditionActionPerformed

    private void inputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSearchActionPerformed

    private void inputSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int con =  condition.getSelectedIndex();
            String value = inputSearch.getText();
            int manv;
            String tennv;
            int namsinh;
            String gioitinh;
            String sdt;
            long lg;
            int sndn;
            String vaitro;
            
            ArrayList<NhanVienDTO> NVList = new ArrayList<>();
            
            if(con == 0){
                NVList = nhanVienBLL.getByCondition("maNhanVien LIKE '%" + value + "%'");
            }else if(con==1){
                NVList = nhanVienBLL.getByCondition("tenNhanVien LIKE '%" + value + "%'");
            }else{
                NVList = nhanVienBLL.getByCondition("soDienThoai LIKE '%" + value + "%'");
            }
            
            DefaultTableModel modelNV = (DefaultTableModel) NVTable.getModel();
            modelNV.setRowCount(0);

            if (NVList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, value + " không tồn tại  hoặc điều kiện kiện tìm kiếm không đúng","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setNVTable();
            } else {
                for (NhanVienDTO s : NVList) {
                    manv = s.getMaNhanVien();
                    tennv = s.getTen();
                    namsinh = s.getNamSinh();
                    gioitinh = s.getGioiTinh();
                    sdt = s.getSoDienThoai();
                    lg = s.getLuong();
                    sndn = s.getSoNgayNghi();
                    vaitro = s.getVaiTro();
                    
                    
                    modelNV.addRow(new Object[]{manv,tennv,namsinh,gioitinh,sdt,lg,sndn,vaitro, "O", "X"});
                }
            }
        }
    }//GEN-LAST:event_inputSearchKeyPressed

    private void inputSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusGained
        // TODO add your handling code here:
               inputSearch.setText("");
        inputSearch.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputSearchFocusGained

    private void inputSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusLost
        // TODO add your handling code here:
        inputSearch.setText("Tìm kiếm ...");
        inputSearch.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_inputSearchFocusLost

    private void exportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportExcelMouseClicked
        ArrayList<NhanVienDTO> nvList = nhanVienBLL.getAll();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("nhanvien");

            XSSFRow row = null;
            XSSFCell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã nhân viên");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên nhân viên");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Năm sinh");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Giới tính");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Lương");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Ngày đã nghỉ");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Vai trò");

            int i = 1;
            for (NhanVienDTO nv : nvList) {
                row = sheet.createRow(0 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(nv.getMaNhanVien());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(nv.getTen());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(nv.getNamSinh());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(nv.getGioiTinh());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(nv.getSoDienThoai());
                
                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(nv.getLuong());
                
                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(nv.getSoNgayNghi());
                
                cell = row.createCell(8, CellType.NUMERIC);
                cell.setCellValue(nv.getVaiTro());

                i++;
            }

            File f = new File("D://nhanvien.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                workbook.write(fis);
                fis.close();
                JOptionPane.showMessageDialog(rootPane, "Xuất file thành công: D:/nhanvien.xlsx","Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportExcelMouseClicked

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

        private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField1ActionPerformed

        private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField2ActionPerformed

        private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField3ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField3ActionPerformed

        private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_addBtnMouseClicked
                if (validateValueAddNV() == false)
                        return;

                String tennv = tenNVInput.getText();
                int namsinh = Integer.parseInt(namSinhInput.getText());
                String gioitinh = (String) gioiTinhInput.getSelectedItem();
                String sdt = sDTInput.getText();
                long lg = Long.parseLong(luongInput.getText());
                int sndn = Integer.parseInt(soNgayDaNghiInput.getText());
                String vaitro = (String) vaiTroInput.getSelectedItem();

                NhanVienDTO nv = new NhanVienDTO(-1,tennv,namsinh,gioitinh,sdt,lg,sndn,vaitro);
                
                int manv = nhanVienBLL.insert(nv);
                
                if (manv >= 1) {
                    tenNVInput.setText("");
                    namSinhInput.setText("");
                    gioiTinhInput.setSelectedIndex(0);
                    sDTInput.setText("");
                    luongInput.setText("");
                    soNgayDaNghiInput.setText("");
                    vaiTroInput.setSelectedIndex(0);
                        

                    DefaultTableModel modelNV = (DefaultTableModel) NVTable.getModel();
                    modelNV.addRow(new Object[] {manv,tennv,namsinh,gioitinh,sdt,lg,sndn,vaitro, "O", "X" });

                    JOptionPane.showMessageDialog(rootPane, "Thêm nhân viên thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
        }// GEN-LAST:event_addBtnMouseClicked

        private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_resetBtnMouseClicked
                if (JOptionPane.showConfirmDialog(this, "Bạn chắc chứ?", "Thông báo", 2) == JOptionPane.OK_OPTION) {
                        tenNVInput.setText("");
                        namSinhInput.setText("");
                        gioiTinhInput.setSelectedIndex(0);
                        sDTInput.setText("");
                        luongInput.setText("");
                        soNgayDaNghiInput.setText("");
                        vaiTroInput.setSelectedIndex(0);
                }
        }// GEN-LAST:event_resetBtnMouseClicked

        private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_resetBtnActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_resetBtnActionPerformed

        
        

        private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addBtnActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_addBtnActionPerformed

        private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField4ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField4ActionPerformed

        private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField5ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jTextField5ActionPerformed

        private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBox2ActionPerformed

        private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_jComboBox1ActionPerformed

        /**
         * @param args the command line arguments
         */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable NVTable;
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> condition;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel exportExcel;
    private javax.swing.JComboBox<String> gioiTinhInput;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JTextField luongInput;
    private javax.swing.JTextField namSinhInput;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField sDTInput;
    private javax.swing.JTextField soNgayDaNghiInput;
    private javax.swing.JTextField tenNVInput;
    private javax.swing.JLabel username;
    private javax.swing.JComboBox<String> vaiTroInput;
    // End of variables declaration//GEN-END:variables
}
