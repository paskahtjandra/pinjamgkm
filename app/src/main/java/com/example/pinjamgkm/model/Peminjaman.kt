package com.example.pinjamgkm.model

data class Peminjaman(
    val nama: String,
    val nim: String,
    val lembaga: String,
    val no_telp: String,
    val status: String,
    val ruangan: String,
    val tanggal: String,
    val jam_masuk: String,
    val jam_keluar: String,
    val keterangan: String,
)
