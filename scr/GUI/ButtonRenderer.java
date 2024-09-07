/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.*;
 
/**
 * @version 1.0 11/09/98
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    
    public ButtonRenderer() {
        setOpaque(true);
    }
    
  
    public Component getTableCellRendererComponent(JTable table, Object value,
                     boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else{
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        
        setText( (value ==null) ? "" : value.toString() );
        return this;
    }
}
