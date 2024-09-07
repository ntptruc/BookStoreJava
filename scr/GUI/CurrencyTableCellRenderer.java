/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hung
 */
public class CurrencyTableCellRenderer extends DefaultTableCellRenderer {
    private static final Locale lc = new Locale("nv","VN"); //Định dạng locale việt nam
    private static final NumberFormat FORMAT = NumberFormat.getInstance(lc);

    @Override
    public final Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        final Component result = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        if (value instanceof Number) {
            setHorizontalAlignment(JLabel.RIGHT);
            setText(FORMAT.format(value));
        } else {
            setText("");
        }
        return result;
    }
}
