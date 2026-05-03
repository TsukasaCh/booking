package com.booking.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class KelasMasak {
    private String kodeKelas;
    private String namaPaket;
    private String[] menuMasakan;
    private LocalDate jadwal;
    private int kuotaAwal;
    private int kuotaTersisa;

    public KelasMasak(String kodeKelas, String namaPaket, String[] menuMasakan, LocalDate jadwal, int kuota) {
        this.kodeKelas = kodeKelas;
        this.namaPaket = namaPaket;
        this.menuMasakan = menuMasakan;
        this.jadwal = jadwal;
        this.kuotaAwal = kuota;
        this.kuotaTersisa = kuota;
    }

    public boolean isKuotaTersedia() {
        return kuotaTersisa > 0;
    }

    public boolean kurangiKuota() {
        if (kuotaTersisa > 0) {
            kuotaTersisa--;
            return true;
        }
        return false;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public String[] getMenuMasakan() {
        return menuMasakan;
    }

    public LocalDate getJadwal() {
        return jadwal;
    }

    public String getJadwalFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id", "ID"));
        return jadwal.format(formatter);
    }

    public int getKuotaAwal() {
        return kuotaAwal;
    }

    public int getKuotaTersisa() {
        return kuotaTersisa;
    }

    public String getMenuString() {
        return String.join(", ", menuMasakan);
    }

    public String getStatusKuota() {
        if (kuotaTersisa == 0) {
            return "0 (PENUH)";
        }
        return String.valueOf(kuotaTersisa);
    }

    @Override
    public String toString() {
        return namaPaket;
    }
}
