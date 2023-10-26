package com.example.pinjamgkm.ui.components

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
import com.example.pinjamgkm.ui.peminjamanList

@Composable
fun Cards(
    navController: NavController,
    peminjaman: Peminjaman,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable() {
                navController.navigate(
                    "detailpeminjaman?peminjaman=kontol"
                )

                navController.navigate(Screen.ScreenDetailPeminjaman.route) {
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
                    text = peminjaman.ruangan,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Text(
                text = peminjaman.tanggal,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = peminjaman.jam_masuk,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = peminjaman.status,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
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
            .clickable(enabled = false){},
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
                    text = peminjaman.ruangan,
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
                text = peminjaman.lembaga,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = peminjaman.no_telp,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = peminjaman.tanggal,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = peminjaman.status,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

