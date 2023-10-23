package com.example.pinjamgkm

sealed class Screen(val route: String) {
    object ScreenHomePage : Screen("homepage")
    object ScreenDetailPeminjaman : Screen("detailpeminjaman")

}
