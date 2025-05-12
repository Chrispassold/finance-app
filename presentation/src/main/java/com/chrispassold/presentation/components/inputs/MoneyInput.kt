package com.chrispassold.presentation.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.chrispassold.domain.models.Money
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyInput(
    label: String,
    value: Money,
    onValueChange: (Money) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
) {
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = if (value.isZero()) "" else value.toString(),
            ),
        )
    }
    TextField(
        value = textFieldValueState,
        onValueChange = { newValue: TextFieldValue ->
            val filteredValue = newValue.text.filter { it.isDigit() || it == '.' || it == ',' }
            textFieldValueState = newValue.copy(text = filteredValue)
            val newMoneyValue = try {
                Money.fromString(filteredValue, locale = value.locale)
            } catch (e: Exception) {
                Timber.e(e, "Error parsing filtered value to Money: $filteredValue")
                value
            }
            onValueChange(newMoneyValue)
        },
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(label, style = MaterialTheme.typography.labelLarge) },
        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
        isError = isError,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword), // Use Number or NumberPassword
        visualTransformation = MoneyInputVisualTransformation(locale = value.locale),
    )
}

private class PreviewParameters : PreviewParameterProvider<Money> {
    override val values: Sequence<Money>
        get() = sequenceOf(
            Money.zero(),
            Money(1.0),
            Money(10.0),
            Money(100.0),
            Money(1_000.0),
            Money(10_000.0),
            Money(100_000.0),
            Money(1_000_000.0),
            Money(1.5),
            Money(10.5),
            Money(100.5),
            Money(1_000.5),
            Money(10_000.5),
            Money(100_000.5),
            Money(1_000_000.5),
        )
}

@PreviewUiModes
@Composable
private fun Preview(@PreviewParameter(PreviewParameters::class) money: Money) {
    AppTheme {
        var amount by remember { mutableStateOf(money) }
        MoneyInput(
            value = amount,
            onValueChange = { amount = it },
            label = "Amount",
        )
    }
}