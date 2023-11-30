package com.example.pinjamgkm

import DropDown
import Material3OutlinedTextField
import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pinjamgkm.ui.components.Cards
import com.example.pinjamgkm.ui.components.DetailCard
import com.example.pinjamgkm.ui.components.Filters
import com.example.pinjamgkm.ui.components.PinjamGKM
import com.example.pinjamgkm.ui.components.TimeCard
import com.example.pinjamgkm.ui.peminjamanList
import com.example.pinjamgkm.ui.screen.DetailPeminjaman
import com.example.pinjamgkm.ui.screen.HomePage

@Composable
fun Navigation(snackbarHostState: SnackbarHostState) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ScreenHomePage.route) {
        composable(route = Screen.ScreenHomePage.route) {
            HomePage(navController = navController, snackbarHostState = snackbarHostState)
        }
        composable(route = Screen.ScreenDetailPeminjaman.route,) {
            DetailPeminjaman(navController = navController)
        }
    }
}

