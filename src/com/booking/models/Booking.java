package com.booking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private static int counter = 0;

    private String bookingId;
    private Peserta peserta;
    private KelasMasak kelas;
    private LocalDateTime waktuBooking;

    public Booking(Peserta peserta, KelasMasak kelas) {
        counter++;
        this.bookingId = "BK" + String.format("%04d", counter);
        this.peserta = peserta;
        this.kelas = kelas;
        this.waktuBooking = LocalDateTime.now();
    }

    public String getBookingId() {
        return bookingId;
    }

    public Peserta getPeserta() {
        return peserta;
    }

    public KelasMasak getKelas() {
        return kelas;
    }

    public LocalDateTime getWaktuBooking() {
        return waktuBooking;
    }

    public String getRingkasan() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== BOOKING BERHASIL ===\n");
        sb.append("ID Booking: ").append(bookingId).append("\n");
        sb.append("Nama: ").append(peserta.getNama()).append("\n");
        sb.append("Kelas: ").append(kelas.getNamaPaket()).append("\n");
        sb.append("Menu: ").append(kelas.getMenuString()).append("\n");
        sb.append("Tanggal: ").append(kelas.getJadwalFormatted()).append("\n");
        sb.append("Sisa Kuota: ").append(kelas.getKuotaTersisa());
        return sb.toString();
    }
}
