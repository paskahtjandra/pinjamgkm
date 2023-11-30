package com.example.pinjamgkm.ui.screen

import Material3OutlinedTextField
import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pinjamgkm.ARGUMENT_PEMINJAMANS
import com.example.pinjamgkm.Screen
import com.example.pinjamgkm.customShape
import com.example.pinjamgkm.model.Peminjaman
import com.example.pinjamgkm.ui.components.DetailCard
import com.example.pinjamgkm.ui.components.TimeCard
import com.example.pinjamgkm.ui.peminjamanList
import com.google.gson.Gson
import java.net.URLDecoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun parseTime(timeString: String): String {
    val formats = arrayOf("hh:mm:ss a", "HH:mm:ss", "hh:mm:ss", "HH:mm")

    for (format in formats) {
        try {
            val parser = SimpleDateFormat(format, Locale.US)
            val parsedTime = parser.parse(timeString)
            val formatter = SimpleDateFormat("HH:mm", Locale.US)
            return formatter.format(parsedTime!!)
        } catch (e: ParseException) {
            // If parsing fails, try the next format
        }
    }

    // Return the original string if none of the formats match
    return timeString
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPeminjaman(navController: NavController) {
    val view = LocalView.current

    val gson = Gson()
    val encodedPeminjamansJson = rememberSaveable {
        navController.currentBackStackEntry?.arguments?.getString(ARGUMENT_PEMINJAMANS.PEMINJAMANS_JSON) ?: ""
    }
    val decodedPeminjamansJson = URLDecoder.decode(encodedPeminjamansJson, "UTF-8")
    val peminjamans = gson.fromJson(decodedPeminjamansJson, Peminjaman::class.java)

    val jamPinjamFormatted = parseTime(peminjamans.jam_pinjam)
    val jamSelesaiFormatted = parseTime(peminjamans.jam_selesai)

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
            DetailCard(navController, peminjamans)
            TimeCard(
                jamPinjam = jamPinjamFormatted,
                jamSelesai = jamSelesaiFormatted,
            )
            Material3OutlinedTextField(
                label = "Keterangan Kegiatan",
                value = peminjamans.keperluan,
                onValueChange = {  }
            )
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                ){
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                ) {
                    Text(
                        text = " Buka Ruangan ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Button(
                    onClick = { },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                ) {
                    Text(
                        text = " Kunci Ruangan ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            ) {
                Text(
                    text = "Update Status",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}