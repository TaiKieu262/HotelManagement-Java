package model;

public class Service {
    private String maDV;
    private String tenDV;
    private double giaTien;

    public Service() {
    }

    public Service(String maDV, String tenDV, double giaTien) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaTien = giaTien;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
    
    // Hàm tiện ích: Trả về giá tiền đã định dạng số nguyên (VD: 10000)
    public String getGiaTienHienThi() {
        return String.format("%.0f", giaTien);
    }
}