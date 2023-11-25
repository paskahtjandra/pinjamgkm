import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinjamgkm.ui.theme.PinjamgkmTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3OutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(
        label,
        Modifier
            .padding(start = 15.dp)
            .offset(y = 5.dp),
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Medium,
    )

    OutlinedTextField(
        readOnly = true,
        value = value,
        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black, unfocusedTextColor = Color.Black),
        textStyle = MaterialTheme.typography.bodySmall,
        onValueChange = {
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(22.dp)
            ),
        shape = MaterialTheme.shapes.medium
    )

}

@Preview(showBackground = true)
@Composable
fun Material3OutlinedTextFieldPreview() {
    PinjamgkmTheme {
        var text by remember { mutableStateOf("") }
        Column {
            Material3OutlinedTextField(
                label = "Label Text",
                value = text,
                onValueChange = { text = it }
            )
        }
    }
}
