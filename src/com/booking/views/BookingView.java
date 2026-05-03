package com.booking.views;

import com.booking.models.Booking;
import com.booking.models.KelasMasak;
import com.booking.models.Peserta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class BookingView extends javax.swing.JFrame {

    private List<KelasMasak> daftarKelas;
    private List<Booking> daftarBooking;
    private DefaultTableModel modelKelas;
    private DefaultTableModel modelBooking;

    public BookingView() {
        initComponents();
        initData();
        setLocationRelativeTo(null);
    }

    private void initData() {
        daftarKelas = new ArrayList<>();
        daftarKelas.add(new KelasMasak("A", "Paket Pasta & Pizza",
                new String[]{"Spaghetti Carbonara", "Fettuccine Alfredo", "Pizza Margherita"},
                LocalDate.of(2026, 4, 10), 5));
        daftarKelas.add(new KelasMasak("B", "Paket Sushi & Ramen",
                new String[]{"Salmon Sushi Roll", "Miso Ramen", "Gyoza"},
                LocalDate.of(2026, 4, 15), 3));
        daftarKelas.add(new KelasMasak("C", "Paket Dessert",
                new String[]{"Tiramisu", "Creme Brulee", "Chocolate Lava Cake"},
                LocalDate.of(2026, 4, 20), 4));

        daftarBooking = new ArrayList<>();

        for (KelasMasak k : daftarKelas) {
            cmbKelas.addItem(k);
        }

        modelKelas = new DefaultTableModel(
                new Object[]{"Kode", "Nama Paket", "Jadwal", "Kuota"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblKelas.setModel(modelKelas);

        modelBooking = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Kelas", "Menu", "Tanggal", "Sisa Kuota"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblBooking.setModel(modelBooking);

        refreshTabelKelas();
        updateDetailKelas();
    }

    private void updateDetailKelas() {
        KelasMasak kelas = (KelasMasak) cmbKelas.getSelectedItem();
        if (kelas != null) {
            lblMenu.setText(kelas.getMenuString());
            lblJadwal.setText(kelas.getJadwalFormatted());
            lblKuota.setText(kelas.getStatusKuota());
            lblKuota.setForeground(kelas.isKuotaTersedia()
                    ? new java.awt.Color(0, 153, 0)
                    : new java.awt.Color(204, 0, 0));
        }
    }

    private void prosesBooking() {
        String nama = txtNama.getText().trim();
        String telepon = txtTelepon.getText().trim();
        String email = txtEmail.getText().trim();

        if (nama.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nama peserta harus diisi!", "Validasi", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtNama.requestFocus();
            return;
        }
        if (telepon.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No. telepon harus diisi!", "Validasi", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtTelepon.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Email harus diisi!", "Validasi", javax.swing.JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return;
        }

        KelasMasak kelas = (KelasMasak) cmbKelas.getSelectedItem();
        if (kelas == null) return;

        if (!kelas.isKuotaTersedia()) {
            String pesan = "=== GAGAL ===\n\nMaaf, kuota kelas " + kelas.getNamaPaket()
                    + " sudah penuh!\nSilakan pilih kelas lain.";
            javax.swing.JOptionPane.showMessageDialog(this, pesan, "Booking Gagal", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        Peserta peserta = new Peserta(nama, telepon, email);
        kelas.kurangiKuota();
        Booking booking = new Booking(peserta, kelas);
        daftarBooking.add(booking);

        modelBooking.addRow(new Object[]{
            booking.getBookingId(),
            peserta.getNama(),
            kelas.getNamaPaket(),
            kelas.getMenuString(),
            kelas.getJadwalFormatted(),
            kelas.getKuotaTersisa()
        });

        refreshTabelKelas();
        updateDetailKelas();

        javax.swing.JOptionPane.showMessageDialog(this, booking.getRingkasan(),
                "Booking Berhasil", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        resetForm();
    }

    private void refreshTabelKelas() {
        modelKelas.setRowCount(0);
        for (KelasMasak kelas : daftarKelas) {
            modelKelas.addRow(new Object[]{
                kelas.getKodeKelas(),
                kelas.getNamaPaket(),
                kelas.getJadwalFormatted(),
                kelas.getStatusKuota()
            });
        }
    }

    private void resetForm() {
        txtNama.setText("");
        txtTelepon.setText("");
        txtEmail.setText("");
        cmbKelas.setSelectedIndex(0);
        updateDetailKelas();
        txtNama.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbKelas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblJadwal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblKuota = new javax.swing.JLabel();
        btnBooking = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pnlKelas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKelas = new javax.swing.JTable();
        pnlRiwayat = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Booking Kelas Memasak");
        setPreferredSize(new java.awt.Dimension(900, 700));

        pnlHeader.setBackground(new java.awt.Color(52, 73, 94));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("BOOKING KELAS MEMASAK");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Booking"));

        jLabel2.setText("Nama Peserta:");
        jLabel3.setText("No. Telepon:");
        jLabel4.setText("Email:");
        jLabel5.setText("Pilih Kelas:");
        jLabel6.setText("Menu:");
        jLabel7.setText("Jadwal:");
        jLabel8.setText("Kuota:");

        lblMenu.setText("-");
        lblJadwal.setText("-");
        lblKuota.setText("-");
        lblKuota.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnBooking.setBackground(new java.awt.Color(39, 174, 96));
        btnBooking.setForeground(new java.awt.Color(255, 255, 255));
        btnBooking.setText("Booking Sekarang");
        btnBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(192, 57, 43));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        cmbKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKelasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNama)
                            .addComponent(txtTelepon)
                            .addComponent(txtEmail)
                            .addComponent(cmbKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblJadwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblKuota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addComponent(btnBooking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblMenu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblJadwal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblKuota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBooking)
                    .addComponent(btnReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlKelas.setBackground(new java.awt.Color(255, 255, 255));
        pnlKelas.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar Kelas"));

        tblKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Kode", "Nama Paket", "Jadwal", "Kuota"}
        ));
        jScrollPane1.setViewportView(tblKelas);

        javax.swing.GroupLayout pnlKelasLayout = new javax.swing.GroupLayout(pnlKelas);
        pnlKelas.setLayout(pnlKelasLayout);
        pnlKelasLayout.setHorizontalGroup(
            pnlKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlKelasLayout.setVerticalGroup(
            pnlKelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        pnlRiwayat.setBorder(javax.swing.BorderFactory.createTitledBorder("Riwayat Booking"));

        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nama", "Kelas", "Menu", "Tanggal", "Sisa Kuota"}
        ));
        jScrollPane2.setViewportView(tblBooking);

        javax.swing.GroupLayout pnlRiwayatLayout = new javax.swing.GroupLayout(pnlRiwayat);
        pnlRiwayat.setLayout(pnlRiwayatLayout);
        pnlRiwayatLayout.setHorizontalGroup(
            pnlRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiwayatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRiwayatLayout.setVerticalGroup(
            pnlRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiwayatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRiwayat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {
        prosesBooking();
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        resetForm();
    }

    private void cmbKelasActionPerformed(java.awt.event.ActionEvent evt) {
        updateDetailKelas();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnBooking;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<KelasMasak> cmbKelas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblJadwal;
    private javax.swing.JLabel lblKuota;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlKelas;
    private javax.swing.JPanel pnlRiwayat;
    private javax.swing.JTable tblBooking;
    private javax.swing.JTable tblKelas;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTelepon;
    // End of variables declaration
}
