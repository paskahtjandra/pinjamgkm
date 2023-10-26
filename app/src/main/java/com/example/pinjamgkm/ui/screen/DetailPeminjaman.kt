package com.example.pinjamgkm.ui.screen

import Material3OutlinedTextField
import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.pinjamgkm.Screen
import com.example.pinjamgkm.customShape
import com.example.pinjamgkm.model.Peminjaman
import com.example.pinjamgkm.ui.components.DetailCard
import com.example.pinjamgkm.ui.components.TimeCard
import com.example.pinjamgkm.ui.peminjamanList
import com.google.gson.Gson
import java.net.URLDecoder


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

//    val gson = Gson()
//    val encodedNewsJson = rememberSaveable {
//        navController.currentBackStackEntry?.arguments?.getString("peminjaman") ?: ""
//    }
//    val decodedNewsJson = URLDecoder.decode(encodedNewsJson, "UTF-8")
//    val peminjaman = gson.fromJson(decodedNewsJson, Peminjaman::class.java)

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
                                navController.navigate(Screen.ScreenHomePage.route) {
                                    popUpTo(Screen.ScreenHomePage.route) {
                                        inclusive = true
                                    }
                                }
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
            DetailCard(navController, peminjamanList[0])
            TimeCard()
            TimeCard()
            Material3OutlinedTextField(
                label = "Keterangan Kegiatan",
                placeholder = "Ex: Rapat Besar Bersama",
                value = peminjamanList[0].keterangan,
                onValueChange = { kegiatan = it },
            )

        }
    }
}