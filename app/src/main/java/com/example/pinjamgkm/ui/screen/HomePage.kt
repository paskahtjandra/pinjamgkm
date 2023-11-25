package com.example.pinjamgkm.ui.screen

import DropDown
import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pinjamgkm.ui.components.Cards
import com.example.pinjamgkm.ui.components.Filters
import com.example.pinjamgkm.ui.components.PinjamGKM
import com.example.pinjamgkm.ui.peminjamanList
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    var lazyColumnItems by remember { mutableStateOf(peminjamanList) }
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

        var activeButton by remember { mutableStateOf(0) }
        PinjamGKM()
        DropDown()
        val filters = listOf("Hari ini", "Besok", "Belum Dipinjam", "Dalam Peminjaman")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(filters.size) { index ->
                Filters(
                    label = filters[index],
                    isActive = activeButton == index
                ) {
                    activeButton = index

                    lazyColumnItems = when (filters[index]) {
                        "Belum Dipinjam" -> {
                            // Filter the items based on the selected filter
                            peminjamanList.filter { it.status == "Belum Dipinjam" }
                        }

                        "Dalam Peminjaman" -> {
                            // Filter the items based on the selected filter
                            peminjamanList.filter { it.status == "Dalam Peminjaman" }
                        }

                        else -> {
                            // Handle other filters or default case
                            peminjamanList
                        }
                    }
                }
            }
        }
        LazyColumn(
            Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lazyColumnItems.size) { index ->
                Cards(navController, lazyColumnItems[index])
            }
        }
    }
}