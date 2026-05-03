# Booking Kelas Memasak

Aplikasi desktop Java untuk booking kelas memasak menggunakan Java Swing.

## Paket Kelas

| Kode | Nama Paket | Menu | Kuota |
|------|-----------|------|-------|
| A | Paket Pasta & Pizza | Spaghetti Carbonara, Fettuccine Alfredo, Pizza Margherita | 5 |
| B | Paket Sushi & Ramen | Salmon Sushi Roll, Miso Ramen, Gyoza | 3 |
| C | Paket Dessert | Tiramisu, Creme Brulee, Chocolate Lava Cake | 4 |

## Struktur Project

```
src/
└── com/booking/
    ├── main/AppBooking.java      ← Entry point
    ├── models/
    │   ├── KelasMasak.java
    │   ├── Peserta.java
    │   └── Booking.java
    └── views/BookingView.java
```

## Cara Menjalankan

### 1. Compile
```bash
javac -d out src/com/booking/models/*.java src/com/booking/views/*.java src/com/booking/main/*.java
```

### 2. Jalankan
```bash
java -cp out com.booking.main.AppBooking
```

### Dengan IDE (NetBeans / IntelliJ)
1. Buka project, set folder `src` sebagai **Source Packages**
2. Jalankan `AppBooking.java`

## Fitur
- Input data peserta (nama, telepon, email)
- Pilih paket kelas dari dropdown
- Kuota otomatis berkurang setiap booking berhasil
- Status kuota real-time (tampil **PENUH** jika 0)
- Riwayat booking dalam tabel
- Validasi form dan notifikasi dialog
