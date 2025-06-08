package com.chrispassold.presentation.components.inputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.chrispassold.core.DEFAULT_CURRENCY_LOCALE
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.formatters.MoneyFormatter
import com.chrispassold.presentation.theme.AppThemePreview
import java.math.BigDecimal
import java.util.Locale

@Composable
fun MoneyInput(
    value: BigDecimal,
    onValueChange: (BigDecimal) -> Unit,
    modifier: Modifier = Modifier,
    locale: Locale = DEFAULT_CURRENCY_LOCALE,
    label: String? = null
) {
    var rawInput by remember { mutableStateOf("") }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = MoneyFormatter.format(value, locale),
                selection = TextRange(MoneyFormatter.format(value, locale).length)
            )
        )
    }

    LaunchedEffect(value) {
        val digitsOnly = value.movePointRight(2).toPlainString()
        if (digitsOnly != rawInput) {
            rawInput = digitsOnly
            val newText = MoneyFormatter.format(value, locale)
            textFieldValue = TextFieldValue(
                text = newText, selection = TextRange(newText.length)
            )
        }
    }

    TextField(
        value = textFieldValue,
        onValueChange = { tfv ->
            val digits = tfv.text.filter { it.isDigit() }
            rawInput = digits
            val newBigDecimal = try {
                if (digits.isEmpty()) BigDecimal.ZERO
                else BigDecimal(digits).movePointLeft(2)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val newFormatted = MoneyFormatter.format(newBigDecimal, locale)
            textFieldValue = TextFieldValue(
                text = newFormatted, selection = TextRange(newFormatted.length)
            )
            onValueChange(newBigDecimal)
        },
        modifier = modifier.fillMaxWidth(),
        placeholder = label?.let { { Text(label, style = MaterialTheme.typography.labelLarge) } },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}


private class PreviewParameters : PreviewParameterProvider<BigDecimal> {
    override val values: Sequence<BigDecimal>
        get() = sequenceOf(
            BigDecimal.ZERO,
            BigDecimal(0.05),
            BigDecimal(0.50),
            BigDecimal(0.56),
            BigDecimal(0.566),
            BigDecimal(0.564),
            BigDecimal(1.0),
            BigDecimal(10.0),
            BigDecimal(100.0),
            BigDecimal(1_000.0),
            BigDecimal(10_000.0),
            BigDecimal(100_000.0),
            BigDecimal(1_000_000.0),
            BigDecimal(1.5),
            BigDecimal(10.5),
            BigDecimal(100.5),
            BigDecimal(1_000.5),
            BigDecimal(10_000.5),
            BigDecimal(100_000.5),
            BigDecimal(1_000_000.5),
        )
}

@PreviewUiModes
@Composable
private fun Preview(@PreviewParameter(PreviewParameters::class) money: BigDecimal) {
    AppThemePreview {
        var amount by remember { mutableStateOf(money) }
        MoneyInput(
            value = amount,
            onValueChange = { amount = it },
            label = "Amount",
            locale = DEFAULT_CURRENCY_LOCALE,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = amount.toString(), style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}