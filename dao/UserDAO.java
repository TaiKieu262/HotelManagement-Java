package dao;

import db.DBConnection;
import model.User;
import java.sql.*;

public class UserDAO {

    public User checkLogin(String username, String password, String role) {

        String sql = """
        SELECT * FROM users
        WHERE username = ?
          AND password = ?
          AND role = ?
          AND status = 'ACTIVE'
    """;

        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                return null;
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Đổi mật khẩu
    public boolean changePassword(int userId, String oldPass, String newPass) {

        String checkSql = "SELECT id FROM users WHERE id = ? AND password = ?";
        String updateSql = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            // 1. kiểm tra mật khẩu cũ
            PreparedStatement psCheck = conn.prepareStatement(checkSql);
            psCheck.setInt(1, userId);
            psCheck.setString(2, oldPass);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                return false; // mật khẩu cũ sai
            }

            // 2. cập nhật mật khẩu mới
            PreparedStatement psUpdate = conn.prepareStatement(updateSql);
            psUpdate.setString(1, newPass);
            psUpdate.setInt(2, userId);

            return psUpdate.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
