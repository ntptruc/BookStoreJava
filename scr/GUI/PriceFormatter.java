/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Hung
 */
public class PriceFormatter {

    public static String format(double num) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmoney = NumberFormat.getCurrencyInstance(vietnam);

        return fmoney.format(num);
    }
}
