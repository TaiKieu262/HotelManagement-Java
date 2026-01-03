package model;

import java.sql.Date;

public class Payment {
    private int id;
    private int bookingId;
    private double amount; // Tổng tiền
    private Date paymentDate;
    private String status; // paid | unpaid
    private int roomId; // Thêm trường này để hiển thị Room ID

    public Payment() {
    }

    public Payment(int id, int bookingId, double amount, Date paymentDate, String status) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    
    // Hàm hiển thị trạng thái tiếng Việt
    public String getStatusHienThi() {
        if ("paid".equalsIgnoreCase(status)) return "Đã thanh toán";
        if ("unpaid".equalsIgnoreCase(status)) return "Chưa thanh toán";
        return status;
    }
    
    public String getAmountHienThi() {
        return String.format("%.0f", amount);
    }
}