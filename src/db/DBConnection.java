
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

private static final String URL =
"jdbc:mysql://localhost:3307/hotel_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
private static final String USER = "hotel_user";
private static final String PASSWORD = "123456";



    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
