package com.booking.models;

public class Peserta {
    private String nama;
    private String noTelepon;
    private String email;

    public Peserta(String nama, String noTelepon, String email) {
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public String getEmail() {
        return email;
    }
}
