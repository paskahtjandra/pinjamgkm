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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pinjamgkm.api.RetrofitInstance
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.example.pinjamgkm.model.Peminjaman
import com.example.pinjamgkm.ui.PeminjamanViewModel
import com.example.pinjamgkm.ui.PeminjamanViewModelFactory
import com.example.pinjamgkm.ui.components.Cards
import com.example.pinjamgkm.ui.components.Filters
import com.example.pinjamgkm.ui.components.PinjamGKM
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, snackbarHostState: SnackbarHostState) {
    val registerViewModelFactory =
        PeminjamanViewModelFactory( snackbarHostState) as ViewModelProvider.Factory
    val peminjamanViewModel: PeminjamanViewModel = viewModel(factory = registerViewModelFactory)
    val listPeminjamans by peminjamanViewModel.peminjaman.observeAsState(initial = emptyList())
    var lazyColumnItems by remember { mutableStateOf(listPeminjamans) }
    val enteredName by remember { mutableStateOf("") }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color(0xFFFFFAF6).toArgb()
        }
    }

    LaunchedEffect(Unit) {
        peminjamanViewModel.fetchPeminjaman()
    }

    lazyColumnItems = listPeminjamans.filter {it.namaGedung == "Gedung Kemahasiswaan" && it.kodeRuang.contains(".")}

    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 12.dp, end = 12.dp),
    ) {

        var activeButton by remember { mutableStateOf(0) }
        PinjamGKM()
        DropDown { enteredName ->
            // Filter the items based on the entered name
            lazyColumnItems = listPeminjamans.filter { it.nama.contains(enteredName, ignoreCase = true) && it.namaGedung == "Gedung Kemahasiswaan"}
        }
        val filters = listOf("Semua","Hari ini", "Besok", "Belum Dipinjam", "Dalam Peminjaman", "Sudah Selesai")
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
                            listPeminjamans.filter { it.status == "Belum Dipinjam" }
                        }

                        "Dalam Peminjaman" -> {
                            // Filter the items based on the selected filter
                            listPeminjamans.filter { it.status == "Dalam Peminjaman" }
                        }

                        "Sudah Selesai" -> {
                            // Filter the items based on the selected filter
                            listPeminjamans.filter { it.status == "Sudah Selesai" }
                        }

                        "Hari ini" -> {
                            // Filter the items based on the current date
                            val currentDate = getCurrentDate()
                            listPeminjamans.filter { it.tanggalPinjam == currentDate }
                        }

                        "Besok" -> {
                            // Filter the items based on tomorrow's date
                            val tomorrowDate = getTomorrowDate()
                            listPeminjamans.filter { it.tanggalPinjam == tomorrowDate }
                        }

                        else -> {
                            // Handle other filters or default case
                            listPeminjamans.filter {it.namaGedung == "Gedung Kemahasiswaan"}
                        }
                    }
                }
            }
        }
        LazyColumn(
            Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lazyColumnItems.sortedBy { it.tanggalPinjam }.size) { index ->
                Cards(navController, lazyColumnItems.sortedByDescending { it.tanggalPinjam }[index])
            }
        }
    }
}

private fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

private fun getTomorrowDate(): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 1)
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
}