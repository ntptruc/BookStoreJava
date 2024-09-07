/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

/**
 *
 * @author Hung
 */
public class ClockLabel extends Thread{
    private JLabel label;
    
    public ClockLabel(JLabel label) {
        this.label = label;
    }
    
    @Override
    public void run() {
        while (true) {
            Calendar ca = new GregorianCalendar();
            
            int day = ca.get(Calendar.DAY_OF_MONTH);
            int month = ca.get(Calendar.MONTH) + 1;
            int year = ca.get(Calendar.YEAR);
            int hour = ca.get(Calendar.HOUR);
            int minute = ca.get(Calendar.MINUTE);
            int second = ca.get(Calendar.SECOND);
            int PM_AM = ca.get(Calendar.AM_PM);
            
            String day_night = PM_AM == 1 ? "PM" : "AM";
            
            label.setText(day + "/" + month + "/" + year + " - " + hour + ":" + minute + ":" + second + " " + day_night);
        }
    }
}
