package dao;

import model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.DBConnection;

public class ServiceDAO {
    
    // 1. Lấy danh sách dịch vụ (getAll)
    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT * FROM services"; // Tên bảng: services
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Service s = new Service();
                s.setMaDV(rs.getString("id"));      // Cột: id
                s.setTenDV(rs.getString("name"));   // Cột: name
                s.setGiaTien(rs.getDouble("price")); // Cột: price
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm dịch vụ mới (insert)
    public boolean insert(Service s) {
        String sql = "INSERT INTO services(id, name, price) VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, s.getMaDV());
            pstmt.setString(2, s.getTenDV());
            pstmt.setDouble(3, s.getGiaTien());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Sửa dịch vụ (update)
    public boolean update(Service s) {
        String sql = "UPDATE services SET name=?, price=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, s.getTenDV());
            pstmt.setDouble(2, s.getGiaTien());
            pstmt.setString(3, s.getMaDV());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa dịch vụ (delete)
    public boolean delete(String maDV) {
        String sql = "DELETE FROM services WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, maDV);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 5. Tìm kiếm theo ID (findById)
    public Service findById(String id) {
        String sql = "SELECT * FROM services WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Service s = new Service();
                s.setMaDV(rs.getString("id"));
                s.setTenDV(rs.getString("name"));
                s.setGiaTien(rs.getDouble("price"));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}