package com.example.pinjamgkm.model

data class Peminjaman(
    val id: String,
    val nama: String,
    val nim: String,
    val noTelp: String,
    val keperluan: String,
    val jaminan: String,
    val namaGedung: String,
    val kodeRuang: String,
    val tanggalPinjam: String,
    val tanggalSelesai: String,
    val jam_pinjam: String,
    val jam_selesai: String,
    val status: String,
)
