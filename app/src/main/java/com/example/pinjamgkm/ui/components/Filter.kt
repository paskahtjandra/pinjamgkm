package com.example.pinjamgkm.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinjamgkm.ui.theme.PinjamgkmTheme

@Composable
fun Filters(
    label: String,
    isActive: Boolean,
    onCLick: () -> Unit
) {
    val notPressed = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.secondary
    )
    val pressed = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = Color.White
    )

    Button(
        onClick = {
            onCLick()
        },
        colors = if (isActive) pressed else notPressed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Material3OutlinedTextFieldPreview() {
    PinjamgkmTheme {
        var text by remember { mutableStateOf("") }
        Column {
            Filters(
                label = "Label Text",
                isActive = true,
                {}
            )
        }
    }
}
