package dao;

import model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.DBConnection;

public class PaymentDAO {

    // 1. Lấy danh sách thanh toán (getAll)
    public List<Payment> getAll() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getInt("id"));
                p.setBookingId(rs.getInt("booking_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentDate(rs.getDate("payment_date"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm thanh toán mới (insert)
    public boolean insert(Payment p) {
        String sql = "INSERT INTO payments(id, booking_id, amount, payment_date, status) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, p.getId());
            pstmt.setInt(2, p.getBookingId());
            pstmt.setDouble(3, p.getAmount());
            pstmt.setDate(4, p.getPaymentDate());
            pstmt.setString(5, p.getStatus());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Cập nhật thanh toán (update)
    public boolean update(Payment p) {
        String sql = "UPDATE payments SET booking_id=?, amount=?, payment_date=?, status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, p.getBookingId());
            pstmt.setDouble(2, p.getAmount());
            pstmt.setDate(3, p.getPaymentDate());
            pstmt.setString(4, p.getStatus());
            pstmt.setInt(5, p.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa thanh toán (delete)
    public boolean delete(int id) {
        String sql = "DELETE FROM payments WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 5. Tìm kiếm theo ID (findById)
    public Payment findById(int id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getInt("id"));
                p.setBookingId(rs.getInt("booking_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentDate(rs.getDate("payment_date"));
                p.setStatus(rs.getString("status"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 6. Hàm hỗ trợ: Lấy giá phòng theo ID phòng (từ bảng rooms)
    public double getRoomPrice(int roomId) {
        String sql = "SELECT price FROM rooms WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, roomId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Không tìm thấy hoặc lỗi
    }

    // 6. Lấy giá phòng dựa trên Booking ID (Join bảng bookings và rooms)
    public double getBookingRoomPrice(int bookingId) {
        // Tính tổng tiền phòng = Giá phòng * (Ngày check_out - Ngày check_in)
        // Sử dụng hàm DATEDIFF của MySQL để lấy khoảng cách ngày
        String sql = "SELECT r.price * DATEDIFF(b.check_out, b.check_in) AS total_price FROM bookings b JOIN rooms r ON b.room_id = r.id WHERE b.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}