/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import DTO.TaiKhoanDTO;
import GUI.AccountGUI;
import GUI.BookGUI;
import GUI.CustomerGUI;
import GUI.EmployeeGUI;
import GUI.ImportBookGUI;
import GUI.ImportInvoiceGUI;
import GUI.LoginGUI;
import GUI.PublisherGUI;
import GUI.SaleGUI;
import GUI.SellBookGUI;
import GUI.SellInvoiceGUI;
import GUI.StatiticsGUI;
import GUI.SupplierGUI;
import GUI.TypeGUI;

/**
 *
 * @author Hung
 */
import javax.swing.UIManager;

public class test {

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("OptionPane.cancelButtonText", "Hủy");
                    UIManager.put("OptionPane.okButtonText", "Đồng ý");
                    new LoginGUI();
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
