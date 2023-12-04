package com.example.pinjamgkm.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pinjamgkm.Screen
import com.example.pinjamgkm.model.Peminjaman
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

@Composable
fun Cards(
    navController: NavController,
    peminjaman: Peminjaman
) {
    val jamPinjamFormatted = parseTime(peminjaman.jam_pinjam)
    val jamSelesaiFormatted = parseTime(peminjaman.jam_selesai)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.ScreenDetailPeminjaman.passPeminjamans(peminjaman)) {
                    popUpTo(Screen.ScreenDetailPeminjaman.route) {
                        inclusive = true
                    }
                }
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(color = Color(0xFFE2E8F0), width = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = peminjaman.nama,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = peminjaman.kodeRuang,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Text(
                text = peminjaman.tanggalPinjam,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = "${jamPinjamFormatted} - ${jamSelesaiFormatted}",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )

            Spacer(modifier = Modifier.height(8.dp))
            val textColor = when (peminjaman.status) {
                "Dalam Peminjaman" -> Color(0xFFF1C411)
                "Sudah Selesai" -> Color(0XFF2CB44D)
                "Belum Dipinjam" -> Color.Black
                else -> MaterialTheme.colorScheme.secondary // Default color
            }
            Text(
                text = peminjaman.status,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = textColor,
            )
        }
    }
}

@Composable
fun DetailCard(
    navController: NavController,
    peminjaman: Peminjaman,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = false) {},
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(color = Color(0xFFE2E8F0), width = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = peminjaman.nama,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = peminjaman.kodeRuang,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Text(
                text = peminjaman.nim,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = peminjaman.noTelp,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = peminjaman.tanggalPinjam,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Spacer(modifier = Modifier.height(8.dp))

            val textColor = when (peminjaman.status) {
                "Dalam Peminjaman" -> Color(0xFFF1C411)
                "Sudah Selesai" -> Color(0XFF2CB44D)
                "Belum Dipinjam" -> Color.Black
                else -> MaterialTheme.colorScheme.secondary // Default color
            }
            Text(
                text = peminjaman.status,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = textColor,
            )
        }
    }
}

