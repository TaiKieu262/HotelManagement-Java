package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Hệ thống Quản lý Khách sạn");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng menu chính là tắt app
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnService = new JButton("Quản lý Dịch vụ");
        btnService.setFont(new Font("Arial", Font.BOLD, 16));
        btnService.addActionListener(e -> {
            // Mở form Service
            new ServiceForm().setVisible(true);
        });

        JButton btnPayment = new JButton("Thanh toán");
        btnPayment.setFont(new Font("Arial", Font.BOLD, 16));
        btnPayment.addActionListener(e -> {
            // Mở form Payment
            new PaymentForm().setVisible(true);
        });

        add(btnService);
        add(btnPayment);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}