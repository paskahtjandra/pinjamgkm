package com.example.pinjamgkm.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.pinjamgkm.ui.theme.PinjamgkmTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TimeCard(
    jamPinjam: String,
    jamSelesai: String,
) {
//    // Change The Jam Pinjam Format
//    val parser = SimpleDateFormat("hh:mm:ss a", Locale.US)
//    val parsedTime = parser.parse(jamPinjam)
//
//    // Format the Jam Pinjam
//    val formatter = SimpleDateFormat("HH:mm", Locale.US)
//    val jamPinjamFormatted = formatter.format(parsedTime)
//
//    // Change The Jam Pinjam Format
//    val parser2 = SimpleDateFormat("hh:mm:ss a", Locale.US)
//    val parsedTime2 = parser2.parse(jamSelesai)
//
//    // Format the Jam Pinjam
//    val formatter2 = SimpleDateFormat("HH:mm", Locale.US)
//    val jamSelesaiFormatted = formatter2.format(parsedTime2)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(color = Color(0xFFE2E8F0), width = 1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

        ) {
            Column(
            ) {
                Text(
                    text = "Jam Masuk",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = jamPinjam,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(Color(0xFFFF9434))
            )
            Column(
            ) {
                Text(
                    text = "Jam Keluar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = jamSelesai,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

