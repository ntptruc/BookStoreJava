
package GUI;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SellEmployeeMenuGUI extends javax.swing.JFrame {
    private TaiKhoanDTO tk ;
    int width = 169;
    int height = 516;
    public SellEmployeeMenuGUI(TaiKhoanDTO tk) {
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
                    Logger.getLogger(SellEmployeeMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(SellEmployeeMenuGUI.class.getName()).log(Level.SEVERE, null, ex);
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
        kButton1 = new com.k33ptoo.components.KButton();
        kButton6 = new com.k33ptoo.components.KButton();
        kButton8 = new com.k33ptoo.components.KButton();
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
        Menu.setPreferredSize(new java.awt.Dimension(169, 516));

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

        kButton1.setBorder(null);
        kButton1.setText("Bán Hàng");
        kButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        kButton1.setkBorderRadius(30);
        kButton1.setkEndColor(new java.awt.Color(255, 255, 153));
        kButton1.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton1.setkHoverEndColor(new java.awt.Color(20, 61, 89));
        kButton1.setkHoverForeGround(new java.awt.Color(244, 180, 26));
        kButton1.setkHoverStartColor(new java.awt.Color(20, 61, 89));
        kButton1.setkIndicatorColor(new java.awt.Color(20, 61, 89));
        kButton1.setkPressedColor(new java.awt.Color(20, 61, 89));
        kButton1.setkStartColor(new java.awt.Color(204, 102, 0));
        kButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton1MouseClicked(evt);
            }
        });
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
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

        kButton8.setBorder(null);
        kButton8.setText("Phiếu Bán ");
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
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
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
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addContainerGap(285, Short.MAX_VALUE)
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

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton1ActionPerformed

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

    private void kButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton1MouseClicked
        this.dispose();
        new SellBookGUI(tk);
    }//GEN-LAST:event_kButton1MouseClicked

    private void kButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton6MouseClicked
        this.dispose();
        new CustomerGUI(tk);
    }//GEN-LAST:event_kButton6MouseClicked

    private void kButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton8MouseClicked
        this.dispose();
        new SellInvoiceGUI(tk);
    }//GEN-LAST:event_kButton8MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
        new LoginGUI();
    }//GEN-LAST:event_jLabel1MouseClicked

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
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KButton kButton6;
    private com.k33ptoo.components.KButton kButton8;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    // End of variables declaration//GEN-END:variables

    
}
