package com.booking.views;

import com.booking.models.Booking;
import com.booking.models.KelasMasak;
import com.booking.models.Peserta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingView extends JFrame {

    private final List<KelasMasak> daftarKelas;
    private final List<Booking> daftarBooking;

    private JTextField txtNama, txtTelepon, txtEmail;
    private JComboBox<KelasMasak> cmbKelas;
    private JLabel lblMenu, lblJadwal, lblKuota;
    private JTable tblKelas, tblBooking;
    private DefaultTableModel modelKelas, modelBooking;

    public BookingView() {
        daftarKelas = initKelas();
        daftarBooking = new ArrayList<>();
        initUI();
        refreshTabelKelas();
    }

    private List<KelasMasak> initKelas() {
        List<KelasMasak> list = new ArrayList<>();
        list.add(new KelasMasak("A", "Paket Pasta & Pizza",
                new String[]{"Spaghetti Carbonara", "Fettuccine Alfredo", "Pizza Margherita"},
                LocalDate.of(2026, 4, 10), 5));
        list.add(new KelasMasak("B", "Paket Sushi & Ramen",
                new String[]{"Salmon Sushi Roll", "Miso Ramen", "Gyoza"},
                LocalDate.of(2026, 4, 15), 3));
        list.add(new KelasMasak("C", "Paket Dessert",
                new String[]{"Tiramisu", "Creme Brulee", "Chocolate Lava Cake"},
                LocalDate.of(2026, 4, 20), 4));
        return list;
    }

    private void initUI() {
        setTitle("Booking Kelas Memasak");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 250));

        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(52, 73, 94));
        header.setPreferredSize(new Dimension(0, 60));
        header.setLayout(new GridBagLayout());

        JLabel title = new JLabel("BOOKING KELAS MEMASAK");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        return header;
    }

    private JPanel createCenterPanel() {
        JPanel center = new JPanel(new GridLayout(1, 2, 10, 0));
        center.setOpaque(false);
        center.add(createFormPanel());
        center.add(createKelasPanel());
        return center;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 1),
                "Form Booking");
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.setBorder(border);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

        // Nama
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        JLabel lbl1 = new JLabel("Nama Peserta:");
        lbl1.setFont(labelFont);
        panel.add(lbl1, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        txtNama = new JTextField(15);
        txtNama.setFont(fieldFont);
        panel.add(txtNama, gbc);

        // Telepon
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel lbl2 = new JLabel("No. Telepon:");
        lbl2.setFont(labelFont);
        panel.add(lbl2, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        txtTelepon = new JTextField(15);
        txtTelepon.setFont(fieldFont);
        panel.add(txtTelepon, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        JLabel lbl3 = new JLabel("Email:");
        lbl3.setFont(labelFont);
        panel.add(lbl3, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        txtEmail = new JTextField(15);
        txtEmail.setFont(fieldFont);
        panel.add(txtEmail, gbc);

        // Pilih Kelas
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        JLabel lbl4 = new JLabel("Pilih Kelas:");
        lbl4.setFont(labelFont);
        panel.add(lbl4, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        cmbKelas = new JComboBox<>(daftarKelas.toArray(new KelasMasak[0]));
        cmbKelas.setFont(fieldFont);
        cmbKelas.addActionListener(e -> updateDetailKelas());
        panel.add(cmbKelas, gbc);

        // Detail Kelas
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JPanel detailPanel = new JPanel(new GridLayout(3, 1, 0, 4));
        detailPanel.setBackground(new Color(248, 249, 250));
        detailPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(8, 10, 8, 10)));

        lblMenu = new JLabel();
        lblMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblJadwal = new JLabel();
        lblJadwal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblKuota = new JLabel();
        lblKuota.setFont(new Font("Segoe UI", Font.BOLD, 12));

        detailPanel.add(lblMenu);
        detailPanel.add(lblJadwal);
        detailPanel.add(lblKuota);

        panel.add(detailPanel, gbc);
        gbc.gridwidth = 1;

        // Tombol
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 6, 10);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setOpaque(false);

        JButton btnBooking = createStyledButton("Booking Sekarang", new Color(39, 174, 96));
        btnBooking.addActionListener(e -> prosesBooking());

        JButton btnReset = createStyledButton("Reset", new Color(192, 57, 43));
        btnReset.addActionListener(e -> resetForm());

        btnPanel.add(btnBooking);
        btnPanel.add(btnReset);
        panel.add(btnPanel, gbc);

        updateDetailKelas();
        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(150, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel createKelasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 1),
                "Daftar Kelas");
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.setBorder(border);

        String[] kolom = {"Kode", "Nama Paket", "Jadwal", "Kuota"};
        modelKelas = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblKelas = new JTable(modelKelas);
        tblKelas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblKelas.setRowHeight(28);
        tblKelas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblKelas.getTableHeader().setBackground(new Color(52, 73, 94));
        tblKelas.getTableHeader().setForeground(Color.WHITE);
        tblKelas.setSelectionBackground(new Color(174, 214, 241));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblKelas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblKelas.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblKelas.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        tblKelas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblKelas.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblKelas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblKelas.getColumnModel().getColumn(3).setPreferredWidth(60);

        JScrollPane scroll = new JScrollPane(tblKelas);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 200));
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 1),
                "Riwayat Booking");
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.setBorder(border);

        String[] kolom = {"ID", "Nama Peserta", "Kelas", "Menu", "Tanggal", "Sisa Kuota"};
        modelBooking = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblBooking = new JTable(modelBooking);
        tblBooking.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblBooking.setRowHeight(25);
        tblBooking.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblBooking.getTableHeader().setBackground(new Color(52, 73, 94));
        tblBooking.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblBooking.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblBooking.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblBooking.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(tblBooking);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void updateDetailKelas() {
        KelasMasak kelas = (KelasMasak) cmbKelas.getSelectedItem();
        if (kelas != null) {
            lblMenu.setText("Menu: " + kelas.getMenuString());
            lblJadwal.setText("Jadwal: " + kelas.getJadwalFormatted());

            String statusKuota = kelas.getStatusKuota();
            lblKuota.setText("Kuota: " + statusKuota);
            if (!kelas.isKuotaTersedia()) {
                lblKuota.setForeground(new Color(192, 57, 43));
            } else {
                lblKuota.setForeground(new Color(39, 174, 96));
            }
        }
    }

    private void prosesBooking() {
        String nama = txtNama.getText().trim();
        String telepon = txtTelepon.getText().trim();
        String email = txtEmail.getText().trim();

        if (nama.isEmpty()) {
            showError("Nama peserta harus diisi!");
            txtNama.requestFocus();
            return;
        }

        if (telepon.isEmpty()) {
            showError("No. telepon harus diisi!");
            txtTelepon.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            showError("Email harus diisi!");
            txtEmail.requestFocus();
            return;
        }

        KelasMasak kelas = (KelasMasak) cmbKelas.getSelectedItem();
        if (kelas == null) {
            showError("Pilih kelas terlebih dahulu!");
            return;
        }

        if (!kelas.isKuotaTersedia()) {
            String pesan = "=== GAGAL ===\n\n"
                    + "Maaf, kuota kelas " + kelas.getNamaPaket() + " sudah penuh!\n"
                    + "Silakan pilih kelas lain.";
            JOptionPane.showMessageDialog(this, pesan, "Booking Gagal",
                    JOptionPane.WARNING_MESSAGE);
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

        JOptionPane.showMessageDialog(this, booking.getRingkasan(),
                "Booking Berhasil", JOptionPane.INFORMATION_MESSAGE);

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

    private void showError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Validasi", JOptionPane.ERROR_MESSAGE);
    }
}
