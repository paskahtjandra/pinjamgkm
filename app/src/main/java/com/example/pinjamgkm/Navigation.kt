package com.example.pinjamgkm

import DatePickers
import DropDown
import Material3OutlinedTextField
import TimePickers
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pinjamgkm.ui.components.Cards
import com.example.pinjamgkm.ui.components.TimeCard
import com.example.pinjamgkm.ui.components.TimeCardPreview

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ScreenHomePage.route){
        composable(route = Screen.ScreenHomePage.route){
            HomePage(navController = navController)
        }
        composable(route = Screen.ScreenDetailPeminjaman.route){
            DetailPeminjaman(navController = navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    var presses by remember { mutableStateOf(0) }

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
                                // Handle navigation button click here
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

            Cards()
            TimeCard()
            TimeCard()
            Material3OutlinedTextField(
                label = "Keterangan Kegiatan",
                placeholder = "Ex: Rapat Besar Bersama",
                value = kegiatan,
                onValueChange = { kegiatan = it },
            )

            Button(
                onClick = {
                          navController.navigate(Screen.ScreenDetailPeminjaman.route)
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = "Daftar",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPeminjaman(navController: NavController) {
    var presses by remember { mutableStateOf(0) }

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
                                // Handle navigation button click here
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
                            text = "Detail Peminjaman",
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

            var lembaga by remember { mutableStateOf("") }

            DropDown()
            DatePickers()
            TimePickers()
            TimePickers()
            Material3OutlinedTextField(
                label = "Keterangan Kegiatan",
                placeholder = "Ex: Rapat",
                value = lembaga,
                onValueChange = { lembaga = it },
            )

            Button(
                onClick = {
                    navController.navigate(Screen.ScreenHomePage.route)
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = "Daftar",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}