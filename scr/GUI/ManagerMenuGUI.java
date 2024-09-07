
package GUI;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ManagerMenuGUI extends javax.swing.JFrame {
    private TaiKhoanDTO tk ;
    int width = 169;
    int height = 700;
    public ManagerMenuGUI(TaiKhoanDTO tk) {
        this.tk = tk;
        initComponents();
        jLabel3.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        scaleImage();
    }
    // Phương thức gắn 3 sách vô lable
    public void scaleImage(){
        ImageIcon icon1 = new ImageIcon(getClass().getResource("book1.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("book2.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("book3.png"));
        Image img1 = icon1.getImage();
        Image img2 = icon2.getImage();
        Image img3 = icon3.getImage();
        Image imgScale1 = img1.getScaledInstance(jLabel7.getWidth(), jLabel7.getHeight(), Image.SCALE_SMOOTH);
        Image imgScale2 = img2.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
        Image imgScale3 = img3.getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon1 = new ImageIcon(imgScale1);
        ImageIcon scaleIcon2 = new ImageIcon(imgScale2);
        ImageIcon scaleIcon3 = new ImageIcon(imgScale3);
        jLabel7.setIcon(scaleIcon1);
        jLabel8.setIcon(scaleIcon2);
        jLabel9.setIcon(scaleIcon3);
    }
    // Phương thức để mở menu 
    private void openMenu() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    for (int i = 0 ; i <= height ;i++){
                    Menu.setSize(width, i);  
                    Thread.sleep((long) 0.9);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ManagerMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    // Phương thức để đóng menu 
    void closeMenu() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    for (int i = height ; i > 0 ;i--){
                    Menu.setSize(width, i);
                    Thread.sleep((long) 0.5);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ManagerMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sach_btn = new com.k33ptoo.components.KButton();
        kButton4 = new com.k33ptoo.components.KButton();
        kButton5 = new com.k33ptoo.components.KButton();
        kButton6 = new com.k33ptoo.components.KButton();
        kButton7 = new com.k33ptoo.components.KButton();
        kButton8 = new com.k33ptoo.components.KButton();
        kButton9 = new com.k33ptoo.components.KButton();
        kButton10 = new com.k33ptoo.components.KButton();
        kButton11 = new com.k33ptoo.components.KButton();
        sach_btn1 = new com.k33ptoo.components.KButton();
        sach_btn2 = new com.k33ptoo.components.KButton();
        sach_btn3 = new com.k33ptoo.components.KButton();
        jLabel6 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 249, 108));
        setBounds(new java.awt.Rectangle(10, 10, 40, 43));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 600));

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 153));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/openmenuicon.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logout.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(59, 58, 58));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu");

        Menu.setBackground(new java.awt.Color(255, 255, 51));
        Menu.setEnabled(false);
        Menu.setOpaque(false);
        Menu.setPreferredSize(new java.awt.Dimension(169, 700));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/closemenuicon.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(59, 58, 58));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Menu");

        sach_btn.setBorder(null);
        sach_btn.setText("Sách");
        sach_btn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sach_btn.setkBorderRadius(30);
        sach_btn.setkEndColor(new java.awt.Color(255, 255, 153));
        sach_btn.setkForeGround(new java.awt.Color(0, 0, 0));
        sach_btn.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        sach_btn.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        sach_btn.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        sach_btn.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        sach_btn.setkPressedColor(new java.awt.Color(20, 61, 89));
        sach_btn.setkStartColor(new java.awt.Color(204, 102, 0));
        sach_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sach_btnMouseClicked(evt);
            }
        });

        kButton4.setBorder(null);
        kButton4.setText("Tác Giả");
        kButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton4.setkBorderRadius(30);
        kButton4.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton4.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton4.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton4.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton4.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton4.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton4.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton4.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton4MouseClicked(evt);
            }
        });

        kButton5.setBorder(null);
        kButton5.setText("Thể Loại");
        kButton5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton5.setkBorderRadius(30);
        kButton5.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton5.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton5.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton5.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton5.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton5.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton5.setkPressedColor(new java.awt.Color(204, 204, 204));
        kButton5.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton5MouseClicked(evt);
            }
        });

        kButton6.setBorder(null);
        kButton6.setText("Khách Hàng");
        kButton6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton6.setkBorderRadius(30);
        kButton6.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton6.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton6.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton6.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton6.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton6.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton6.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton6.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton6MouseClicked(evt);
            }
        });

        kButton7.setBorder(null);
        kButton7.setText("Nhân Viên");
        kButton7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton7.setkBorderRadius(30);
        kButton7.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton7.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton7.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton7.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton7.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton7.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton7.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton7.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton7MouseClicked(evt);
            }
        });

        kButton8.setBorder(null);
        kButton8.setText("Thống Kê");
        kButton8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton8.setkBorderRadius(30);
        kButton8.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton8.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton8.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton8.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton8.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton8.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton8.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton8.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton8MouseClicked(evt);
            }
        });
        kButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton8ActionPerformed(evt);
            }
        });

        kButton9.setBorder(null);
        kButton9.setText("Nhà Cung Cấp");
        kButton9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton9.setkBorderRadius(30);
        kButton9.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton9.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton9.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton9.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton9.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton9.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton9.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton9.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton9MouseClicked(evt);
            }
        });

        kButton10.setBorder(null);
        kButton10.setText("Phiếu Nhập");
        kButton10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton10.setkBorderRadius(30);
        kButton10.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton10.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton10.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton10.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton10.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton10.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton10.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton10.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton10MouseClicked(evt);
            }
        });
        kButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton10ActionPerformed(evt);
            }
        });

        kButton11.setBorder(null);
        kButton11.setText("Phiếu Bán");
        kButton11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton11.setkBorderRadius(30);
        kButton11.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton11.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton11.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton11.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton11.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton11.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton11.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton11.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton11MouseClicked(evt);
            }
        });
        kButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton11ActionPerformed(evt);
            }
        });

        sach_btn1.setBorder(null);
        sach_btn1.setText("Khuyến Mãi");
        sach_btn1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sach_btn1.setkBorderRadius(30);
        sach_btn1.setkEndColor(new java.awt.Color(255, 255, 153));
        sach_btn1.setkForeGround(new java.awt.Color(0, 0, 0));
        sach_btn1.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        sach_btn1.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        sach_btn1.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        sach_btn1.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        sach_btn1.setkPressedColor(new java.awt.Color(20, 61, 89));
        sach_btn1.setkStartColor(new java.awt.Color(204, 102, 0));
        sach_btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sach_btn1MouseClicked(evt);
            }
        });

        sach_btn2.setBorder(null);
        sach_btn2.setText("Tài Khoản");
        sach_btn2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sach_btn2.setkBorderRadius(30);
        sach_btn2.setkEndColor(new java.awt.Color(255, 255, 153));
        sach_btn2.setkForeGround(new java.awt.Color(0, 0, 0));
        sach_btn2.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        sach_btn2.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        sach_btn2.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        sach_btn2.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        sach_btn2.setkPressedColor(new java.awt.Color(20, 61, 89));
        sach_btn2.setkStartColor(new java.awt.Color(204, 102, 0));
        sach_btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sach_btn2MouseClicked(evt);
            }
        });
        sach_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_btn2ActionPerformed(evt);
            }
        });

        sach_btn3.setBorder(null);
        sach_btn3.setText("Nhà Xuất Bản");
        sach_btn3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        sach_btn3.setkBorderRadius(30);
        sach_btn3.setkEndColor(new java.awt.Color(255, 255, 153));
        sach_btn3.setkForeGround(new java.awt.Color(0, 0, 0));
        sach_btn3.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        sach_btn3.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        sach_btn3.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        sach_btn3.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        sach_btn3.setkPressedColor(new java.awt.Color(20, 61, 89));
        sach_btn3.setkStartColor(new java.awt.Color(204, 102, 0));
        sach_btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sach_btn3MouseClicked(evt);
            }
        });
        sach_btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sach_btn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sach_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sach_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sach_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sach_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(sach_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sach_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sach_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sach_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jLabel6.setFont(new java.awt.Font("#9Slide03 AmpleSoft", 3, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cửa Hàng Sách");

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 153));
        kGradientPanel1.setkGradientFocus(2000);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 153, 0));

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 3, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("\"Việc đọc rất quan trọng.");

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 3, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(" Nếu bạn biết cách đọc , cả thế giới sẽ mở ra cho bạn\" ");

        jLabel12.setFont(new java.awt.Font("Segoe UI Variable", 3, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("- Barack Obama -");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)))
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(114, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(259, 259, 259))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        Menu.getAccessibleContext().setAccessibleDescription("");
        Menu.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton10ActionPerformed

    private void kButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton8ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        jLabel3.setVisible(true);
        jLabel4.setVisible(false);
        jLabel2.setVisible(true);
        closeMenu();

    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        jLabel3.setVisible(false);
        jLabel4.setVisible(true);
        jLabel2.setVisible(false);
        openMenu();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void kButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton11ActionPerformed

    private void sach_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sach_btnMouseClicked
        this.dispose();
        new BookGUI(tk);
    }//GEN-LAST:event_sach_btnMouseClicked

    private void kButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton4MouseClicked
        this.dispose();
        new AuthorGUI(tk);
    }//GEN-LAST:event_kButton4MouseClicked

    private void kButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton5MouseClicked
        this.dispose();
        new TypeGUI(tk);
    }//GEN-LAST:event_kButton5MouseClicked

    private void kButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton6MouseClicked
        this.dispose();
        new CustomerGUI(tk);
    }//GEN-LAST:event_kButton6MouseClicked

    private void kButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton7MouseClicked
        this.dispose();
        new EmployeeGUI(tk);
    }//GEN-LAST:event_kButton7MouseClicked

    private void kButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton8MouseClicked
        this.dispose();
        new StatiticsGUI(tk);
    }//GEN-LAST:event_kButton8MouseClicked

    private void kButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton9MouseClicked
        this.dispose();
        new SupplierGUI(tk);
    }//GEN-LAST:event_kButton9MouseClicked

    private void kButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton10MouseClicked
        this.dispose();
        new ImportInvoiceGUI(tk);
    }//GEN-LAST:event_kButton10MouseClicked

    private void kButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton11MouseClicked
        this.dispose();
        new SellInvoiceGUI(tk);
    }//GEN-LAST:event_kButton11MouseClicked

    private void sach_btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sach_btn1MouseClicked
        this.dispose();
        new SaleGUI(tk);
    }//GEN-LAST:event_sach_btn1MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
        new LoginGUI();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void sach_btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sach_btn2MouseClicked
        this.dispose();
        new AccountGUI(tk);
    }//GEN-LAST:event_sach_btn2MouseClicked

    private void sach_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sach_btn2ActionPerformed

    private void sach_btn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sach_btn3MouseClicked
        this.dispose();
        new PublisherGUI(tk);
    }//GEN-LAST:event_sach_btn3MouseClicked

    private void sach_btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sach_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sach_btn3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.k33ptoo.components.KButton kButton10;
    private com.k33ptoo.components.KButton kButton11;
    private com.k33ptoo.components.KButton kButton4;
    private com.k33ptoo.components.KButton kButton5;
    private com.k33ptoo.components.KButton kButton6;
    private com.k33ptoo.components.KButton kButton7;
    private com.k33ptoo.components.KButton kButton8;
    private com.k33ptoo.components.KButton kButton9;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KButton sach_btn;
    private com.k33ptoo.components.KButton sach_btn1;
    private com.k33ptoo.components.KButton sach_btn2;
    private com.k33ptoo.components.KButton sach_btn3;
    // End of variables declaration//GEN-END:variables

    
}
