package com.example.pinjamgkm

import com.example.pinjamgkm.model.Peminjaman
import com.google.gson.Gson
import java.net.URLEncoder

sealed class Screen(val route: String) {
    object ScreenHomePage : Screen("homepage")
    object ScreenDetailPeminjaman : Screen("detailpeminjaman"){
        fun passPeminjaman(peminjaman: Peminjaman): String {
            val gson = Gson()
            // Converting the News object to a JSON string with proper encoding
            val peminjamanJson = gson.toJson(peminjaman)
            // Encoding the JSON string to make it URL-safe
            val encodedPeminjamanJson = URLEncoder.encode(peminjamanJson, "UTF-8")
            val string: String = "kontol"
            return "detailpeminjaman?peminjaman=${string}"
        }
    }

}
