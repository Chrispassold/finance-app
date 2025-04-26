package com.chrispassold.askbuddy.presentation.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerInput(
    label: String,
    onDateSelected: (timestamp: Long) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val inputValue by remember {
        derivedStateOf {
            TextFieldValue(convertMillisToDate(datePickerState.selectedDateMillis))
        }
    }

    Column {
        TextInput(
            label = label,
            value = inputValue,
            onValueChange = {},
            modifier = Modifier.onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    showDatePicker = true
                }
            },
        )
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                    onDismissRequest()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                            onDateSelected(datePickerState.selectedDateMillis ?: 0L)
                        },
                        enabled = datePickerState.selectedDateMillis != null,
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                            onDismissRequest()
                        },
                    ) {
                        Text("Cancel")
                    }
                },
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

private fun convertMillisToDate(millis: Long?): String {
    if (millis == null) return ""
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}