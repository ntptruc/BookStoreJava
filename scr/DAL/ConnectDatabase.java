/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Hung
 */
public class ConnectDatabase {
    private static String URL = "jdbc:mySQL://localhost:3306/qlchs";
    private static String USERNAME = "root";
    private static String PASSWORD = "hung";
    
    public static Connection openConnection() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connect = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connect;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return connect;
    }
    
    public static void closeConnection(Connection connect) {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
        }
    }
}
