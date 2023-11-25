package com.example.pinjamgkm

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pinjamgkm.model.Peminjaman
import com.google.gson.Gson
import java.net.URLEncoder

object ARGUMENT_PEMINJAMANS {
    const val PEMINJAMANS_JSON = "peminjamans_json"
}
sealed class Screen(val route: String, val arguments: List<NamedNavArgument>? = null) {
    object ScreenHomePage : Screen("homepage")
    object ScreenDetailPeminjaman : Screen(
        route = "peminjaman_detail_screen?peminjamans={${ARGUMENT_PEMINJAMANS.PEMINJAMANS_JSON}}",
        arguments = listOf(
            navArgument(ARGUMENT_PEMINJAMANS.PEMINJAMANS_JSON){
                type = NavType.StringType
                defaultValue = ""
            })
        ){
            fun passPeminjamans(peminjaman: Peminjaman):String{
                val gson = Gson()
                val peminjamansJson = gson.toJson(peminjaman)
                val encodedPeminjamansJson = URLEncoder.encode(peminjamansJson, "UTF-8")
                return "peminjaman_detail_screen?peminjamans=$encodedPeminjamansJson"
            }

        }
}
