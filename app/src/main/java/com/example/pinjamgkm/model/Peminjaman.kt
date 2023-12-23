package com.example.pinjamgkm.model

data class Peminjaman(
    val id: Int,
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

data class Ruangan(
    val ruang: String,
    val status: String,
)

data class StatusRequest(
    val status: String,
)

data class DefaultSuccessPeminjamanResponse(
    val message: String,
)

data class BackendResponsePeminjaman(
    val peminjamans: List<Peminjaman>
)

data class BackendResponseRuangan(
    val ruangans: List<Ruangan>
)