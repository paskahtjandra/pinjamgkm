package com.example.pinjamgkm.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pinjamgkm.ui.theme.PinjamgkmTheme

@Composable
fun Cards() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
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
                    text = "Bambang Suprapto",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Rg 4.1",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Text(
                text = "195150701111001",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = "BEM Fakultas Ilmu Komputer",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Text(
                text = "081234567890",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Dalam Peminjaman",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsPreview() {
    PinjamgkmTheme {
        Column {
            Cards()
        }
    }
}