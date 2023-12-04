package com.example.pinjamgkm

import DropDown
import Material3OutlinedTextField
import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pinjamgkm.ui.screen.DetailPeminjaman
import com.example.pinjamgkm.ui.screen.HomePage

@Composable
fun Navigation(snackbarHostState: SnackbarHostState) {
    Log.d("Navigation", "Test")
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ScreenHomePage.route) {
        composable(route = Screen.ScreenHomePage.route) {
            HomePage(navController = navController, snackbarHostState = snackbarHostState)
        }
        composable(route = Screen.ScreenDetailPeminjaman.route,) {
            DetailPeminjaman(navController = navController, snackbarHostState = snackbarHostState)
        }
    }
}

