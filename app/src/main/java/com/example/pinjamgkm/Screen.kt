package com.example.pinjamgkm

sealed class Screen(val route: String) {
    object ScreenDaftarMahasiswa : Screen("daftarmahasiswa")
    object ScreenDaftarPeminjaman : Screen("daftarpeminjaman")

}
