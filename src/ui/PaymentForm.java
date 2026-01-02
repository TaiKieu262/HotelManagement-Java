package ui;

import dao.PaymentDAO;
import model.Payment;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class PaymentForm extends javax.swing.JFrame {

    public PaymentForm() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        loadData();
        
        // Mặc định ngày hiện tại
        txtNgay.setText(new java.sql.Date(System.currentTimeMillis()).toString());
        
        // Thêm sự kiện: Khi nhập xong Booking ID (rời chuột hoặc Tab đi), tự động lấy giá phòng
        txtBookingId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (!txtBookingId.getText().trim().isEmpty()) {
                    loadBookingInfo();
                }
            }
        });
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        PaymentDAO dao = new PaymentDAO();
        List<Payment> list = dao.getAll();
        for (Payment p : list) {
            model.addRow(new Object[]{
                p.getId(),
                p.getBookingId(),
                p.getAmountHienThi(),
                p.getPaymentDate(),
                p.getStatusHienThi()
            });
        }
    }

    // Hàm tìm kiếm thông tin Booking và điền giá phòng
    private void loadBookingInfo() {
        try {
            int bookingId = Integer.parseInt(txtBookingId.getText().trim());
            PaymentDAO dao = new PaymentDAO();
            double price = dao.getBookingRoomPrice(bookingId);
            
            if (price > 0) {
                txtTienPhong.setText(String.format("%.0f", price));
            }
        } catch (NumberFormatException e) {
            // Bỏ qua nếu nhập sai định dạng số
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBookingId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTienPhong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTienDichVu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnTinh = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý Thanh Toán");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jLabel1.setText("THANH TOÁN");

        jLabel2.setText("Mã thanh toán (ID):");
        jLabel3.setText("Mã đặt phòng (Booking ID):");
        jLabel4.setText("Tiền phòng:");
        jLabel5.setText("Tiền dịch vụ:");
        
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jLabel6.setText("TỔNG TIỀN:");
        
        txtTongTien.setEditable(false); // Không cho sửa tay tổng tiền
        txtTongTien.setBackground(new java.awt.Color(240, 240, 240));

        btnTinh.setText("Tính Tổng");
        btnTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhActionPerformed(evt);
            }
        });

        jLabel7.setText("Ngày thanh toán (yyyy-mm-dd):");
        jLabel8.setText("Trạng thái:");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unpaid", "Paid" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Booking ID", "Tổng tiền", "Ngày TT", "Trạng thái" }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnThem.setText("Thanh toán (Lưu)");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        // Layout code (được rút gọn để dễ nhìn, NetBeans sẽ tự sinh chi tiết hơn)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBookingId)
                            .addComponent(txtId)
                            .addComponent(txtTienPhong)
                            .addComponent(txtTienDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgay)
                            .addComponent(cboStatus, 0, 150, Short.MAX_VALUE)
                            .addComponent(txtTongTien))
                        .addGap(18, 18, 18)
                        .addComponent(btnTinh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThoat)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBookingId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTinh))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnThoat))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // Nút Tính Tổng: Cộng tiền phòng và tiền dịch vụ
    private void btnTinhActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            double tienPhong = txtTienPhong.getText().isEmpty() ? 0 : Double.parseDouble(txtTienPhong.getText().trim().replace(",", ""));
            double tienDichVu = txtTienDichVu.getText().isEmpty() ? 0 : Double.parseDouble(txtTienDichVu.getText().trim().replace(",", ""));
            
            double tong = tienPhong + tienDichVu;
            txtTongTien.setText(String.format("%.0f", tong));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho Tiền phòng và Tiền dịch vụ!");
        }
    }

    // Nút Thêm (Thanh toán)
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty() || txtBookingId.getText().isEmpty() || txtTongTien.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ID, Booking ID và Tính tổng tiền!");
            return;
        }
        try {
            Payment p = new Payment();
            p.setId(Integer.parseInt(txtId.getText().trim()));
            p.setBookingId(Integer.parseInt(txtBookingId.getText().trim()));
            p.setAmount(Double.parseDouble(txtTongTien.getText().trim().replace(",", "")));
            p.setPaymentDate(Date.valueOf(txtNgay.getText().trim())); // Định dạng yyyy-mm-dd
            p.setStatus(cboStatus.getSelectedItem().toString().toLowerCase());

            PaymentDAO dao = new PaymentDAO();
            if (dao.insert(p)) {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi: BookingID không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + e.getMessage());
        }
    }

    // Nút Sửa
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để sửa!");
            return;
        }
        try {
            Payment p = new Payment();
            p.setId(Integer.parseInt(txtId.getText().trim())); // ID dùng để tìm dòng update
            p.setBookingId(Integer.parseInt(txtBookingId.getText().trim()));
            p.setAmount(Double.parseDouble(txtTongTien.getText().trim().replace(",", "")));
            p.setPaymentDate(Date.valueOf(txtNgay.getText().trim()));
            p.setStatus(cboStatus.getSelectedItem().toString().toLowerCase());

            PaymentDAO dao = new PaymentDAO();
            if (dao.update(p)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + e.getMessage());
        }
    }

    // Nút Xóa
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
            PaymentDAO dao = new PaymentDAO();
            if (dao.delete(id)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
                clearForm();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa!");
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        txtId.setText(jTable1.getValueAt(row, 0).toString());
        txtBookingId.setText(jTable1.getValueAt(row, 1).toString());
        txtTongTien.setText(jTable1.getValueAt(row, 2).toString().replace(",", ""));
        txtNgay.setText(jTable1.getValueAt(row, 3).toString());
        
        String status = jTable1.getValueAt(row, 4).toString();
        if (status.equalsIgnoreCase("Đã thanh toán") || status.equalsIgnoreCase("paid")) {
            cboStatus.setSelectedItem("Paid");
        } else {
            cboStatus.setSelectedItem("Unpaid");
        }
    }
    
    private void clearForm() {
        txtId.setText("");
        txtBookingId.setText("");
        txtTienPhong.setText("");
        txtTienDichVu.setText("");
        txtTongTien.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTinh;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBookingId;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtTienDichVu;
    private javax.swing.JTextField txtTienPhong;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration
}