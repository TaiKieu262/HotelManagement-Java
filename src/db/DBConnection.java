package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // CẤU HÌNH KẾT NỐI
    // 1. Tên database: Thay 'hotel_management' bằng tên DB của bạn
    // 2. Port: Mặc định là 3306
    // 3. useSSL=false, allowPublicKeyRetrieval=true: Tránh lỗi bảo mật SSL
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_management?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    
    private static final String USER = "root"; // User mặc định của XAMPP
    private static final String PASS = "";     // Pass mặc định của XAMPP là rỗng

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Nạp driver (Dùng com.mysql.cj.jdbc.Driver cho bản mới, hoặc com.mysql.jdbc.Driver cho bản cũ)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Thiếu thư viện MySQL JDBC Driver! Hãy add jar vào Libraries.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối SQL: " + e.getMessage());
            System.out.println("Vui lòng kiểm tra: 1. XAMPP đã bật MySQL chưa? 2. Tên DB có đúng không?");
        }
        return conn;
    }
    
    // Chạy file này (Shift+F6) để test kết nối riêng
    public static void main(String[] args) {
        if (getConnection() != null) {
            System.out.println("Kết nối thành công!");
        }
    }
}