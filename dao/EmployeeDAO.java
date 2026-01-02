package dao;

import db.DBConnection;
import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // 1. Lấy danh sách toàn bộ nhân viên
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("position")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Tìm kiếm (Lưu ý: Cast ID sang String nếu DB nghiêm ngặt)
    public List<Employee> searchAllFields(String keyword) {
        List<Employee> list = new ArrayList<>();
        // Sử dụng CAST(id AS CHAR) nếu là MySQL để dùng LIKE với cột số
        String sql = "SELECT * FROM employees WHERE CAST(id AS CHAR) LIKE ? OR name LIKE ? OR phone LIKE ? OR email LIKE ? OR position LIKE ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            String searchKey = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, searchKey);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("position")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Thêm nhân viên
    public boolean insert(Employee e) {
        String sql = "INSERT INTO employees(id, name, phone, email, position) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getPhone());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPosition());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 4. Cập nhật nhân viên
    public boolean update(Employee e) {
        String sql = "UPDATE employees SET name=?, phone=?, email=?, position=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getPhone());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPosition());
            ps.setInt(5, e.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // 5. Xóa nhân viên
    public boolean deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6. Kiểm tra ID trùng (Nằm bên trong Class)
    public boolean checkDuplicateId(int id) {
        String sql = "SELECT COUNT(*) FROM employees WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} // Kết thúc Class ở đây