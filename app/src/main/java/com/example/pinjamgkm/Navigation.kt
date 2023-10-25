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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pinjamgkm.ui.components.Cards
import com.example.pinjamgkm.ui.components.PinjamGKM
import com.example.pinjamgkm.ui.components.TimeCard

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ScreenHomePage.route) {
        composable(route = Screen.ScreenHomePage.route) {
            HomePage(navController = navController)
        }
        composable(route = Screen.ScreenDetailPeminjaman.route) {
            DetailPeminjaman(navController = navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPeminjaman(navController: NavController) {
    var presses by remember { mutableStateOf(0) }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color(0xFF002647).toArgb()
        }
    }
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .clip(customShape)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                TopAppBar(
                    modifier = Modifier
                        .align(Alignment.Center),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    navigationIcon = {

                        IconButton(
                            modifier = Modifier
                                .padding(top = 15.dp, start = 10.dp),
                            onClick = {
                                navController.navigate(Screen.ScreenHomePage.route)
                            },
                            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary),

                            ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        // An empty Icon without an IconButton
                        Box(Modifier.sizeIn(40.dp))
                    },

                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            text = "HomePage",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }
        },

        ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding() + 12.dp, start = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            var kegiatan by remember { mutableStateOf("") }
            Cards(navController)
            TimeCard()
            TimeCard()
            Material3OutlinedTextField(
                label = "Keterangan Kegiatan",
                placeholder = "Ex: Rapat Besar Bersama",
                value = kegiatan,
                onValueChange = { kegiatan = it },
            )

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    var presses by remember { mutableStateOf(0) }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color(0xFFFFFAF6).toArgb()
        }
    }
    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
    ) {

        var lembaga by remember { mutableStateOf("") }
        PinjamGKM()
        DropDown()
        LazyColumn( Modifier.padding(top = 8.dp),verticalArrangement = Arrangement.spacedBy(8.dp),){
            items(5){ index->
                Cards(navController)
            }
        }
    }
}