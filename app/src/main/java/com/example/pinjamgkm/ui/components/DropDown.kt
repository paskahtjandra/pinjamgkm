import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DropDown(
//    label: String,
//    placeholder: String,
//    value: String,
//    onValueChange: (String) -> Unit
//) {
//    val options = listOf("Bambang Herlambang", "Baba Sugita", "Hendry Margono")
//    var expanded by remember { mutableStateOf(false) }
//    var selectedOptionText by remember { mutableStateOf(options[0]) }
//    var textFieldValue by remember { mutableStateOf("") }
//    val filteredOptions = options.filter { it.contains(textFieldValue, ignoreCase = true) }
//
//    Text(
//        label,
//        Modifier
//            .padding(start = 15.dp)
//            .offset(y = 5.dp),
//        style = MaterialTheme.typography.labelMedium,
//        fontWeight = FontWeight.Medium,
//    )
//
//    OutlinedTextField(
//        onValueChange = {newtext ->
//            textFieldValue = newtext
//            expanded = true
//        },
////        #tetap menyerah dan jangansmgt
//        trailingIcon = {
//            IconButton(onClick = { expanded = true }) {
//                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
//            }
//        },
//        value = textFieldValue,
//        textStyle = MaterialTheme.typography.bodySmall,
//        placeholder = {
//            Text(
//                text = placeholder,
//                style = MaterialTheme.typography.bodySmall,
//                color = MaterialTheme.colorScheme.tertiary
//            )
//        },
//
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                Color.White,
//                shape = RoundedCornerShape(22.dp)
//            ),
//        shape = MaterialTheme.shapes.medium
//    )
//
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = { expanded = false },
//        modifier = Modifier.fillMaxWidth()
//    ) {
////        options.filter { it.contains(textFieldValue, ignoreCase = true) }
////        options.forEach { selectionOption ->
////            DropdownMenuItem(
////                text = { Text(text = selectionOption) },
////                onClick = {
////                    selectedOptionText = selectionOption
////                    expanded = false
////                }
////            )
////        }
//        if (filteredOptions.isEmpty()) {
//            DropdownMenuItem(
//                text = { Text(text = "No options found") },
//                onClick = {})
//        } else {
//            filteredOptions.forEach { selectionOption ->
//                DropdownMenuItem(
//                    text = { Text(text = selectionOption) },
//                    onClick = {
//                        selectedOptionText = selectionOption
//                        textFieldValue = selectionOption
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}

val all = listOf("aaa", "baa", "aab", "abb", "bab","ababa","abbaaabb")

val dropDownOptions = mutableStateOf(listOf<String>())
val textFieldValue = mutableStateOf(TextFieldValue())
val dropDownExpanded = mutableStateOf(false)
fun onDropdownDismissRequest() {
    dropDownExpanded.value = false
}

fun onValueChanged(value: TextFieldValue) {
    dropDownExpanded.value = true
    textFieldValue.value = value
    dropDownOptions.value = all.filter { it.startsWith(value.text) && it != value.text }.take(3)
}

@Composable
fun DropDown() {
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
    Text(
        label,
        Modifier
            .padding(start = 15.dp)
            .offset(y = 5.dp),
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Medium,
    )
    Box() {
        var expanded by remember { mutableStateOf(dropDownExpanded) }

        OutlinedTextField(
            trailingIcon = {
                IconButton(onClick = {
                    onDismissRequest()
                    setValue(TextFieldValue(value.text))
                    expanded = true
                }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
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
                    Text(text = text)
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