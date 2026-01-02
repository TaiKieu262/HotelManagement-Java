package main;

import ui.CustomerForm;
import ui.EmployeeForm;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            // Mở form Khách hàng
            new CustomerForm().setVisible(true);
            
            
        });
    }
}