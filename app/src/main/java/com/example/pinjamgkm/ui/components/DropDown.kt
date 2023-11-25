import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.pinjamgkm.ui.components.PinjamGKM
import com.example.pinjamgkm.ui.peminjamanList
import com.example.pinjamgkm.ui.theme.PinjamgkmTheme

val all = peminjamanList.map {it.nama}

val dropDownOptions = mutableStateOf(all)
val textFieldValue = mutableStateOf(TextFieldValue())
val dropDownExpanded = mutableStateOf(false)

@Composable
fun DropDown(onNameEntered: (String) -> Unit) {
    fun onDropdownDismissRequest() {
        dropDownExpanded.value = false
    }

    fun onValueChanged(value: TextFieldValue) {
        dropDownExpanded.value = true
        textFieldValue.value = value
        dropDownOptions.value = all
            .filter { it.toLowerCase().startsWith(value.text.toLowerCase()) && it != value.text }
            .distinct()
            .take(3)

        // Invoke the callback with the entered name
        onNameEntered(value.text)
    }

    TextFieldWithDropdown(
        value = textFieldValue.value,
        setValue = ::onValueChanged,
        onDismissRequest = ::onDropdownDismissRequest,
        dropDownExpanded = dropDownExpanded.value,
        list = dropDownOptions.value,
        label = "Nama"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDropdown(
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    dropDownExpanded: Boolean,
    list: List<String>,
    label: String = ""
) {
    Box() {
        var expanded by remember { mutableStateOf(dropDownExpanded) }

        OutlinedTextField(
            singleLine = true,
            colors =  OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
            placeholder = {
                Text(
                    text = "Cari Mahasiswa",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    onDismissRequest()
                    setValue(TextFieldValue(value.text))
                    expanded = true
                })
                {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null,tint = Color.Black)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(22.dp)
                )
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused)
                        onDismissRequest()
                },
            value = value,
            onValueChange = setValue,
            shape = MaterialTheme.shapes.medium,
            textStyle = MaterialTheme.typography.bodySmall,
        )
        DropdownMenu(
            expanded = dropDownExpanded,
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = onDismissRequest
        ) {
            list.forEach { text ->
                DropdownMenuItem({
                    Text(text = text, style = MaterialTheme.typography.bodySmall)
                }, onClick = {
                    setValue(
                        TextFieldValue(
                            text,
                            TextRange(text.length)
                        )
                    )
                })
            }
        }
    }
}
