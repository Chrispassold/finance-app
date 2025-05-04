package com.chrispassold.askbuddy.presentation.components.inputs

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.chrispassold.askbuddy.domain.models.Money
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.theme.AppTheme
import kotlin.math.max

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
    val currentValue by remember { mutableStateOf(TextFieldValue(value.toString())) }

    TextField(
        value = currentValue,
        onValueChange = { newValue: TextFieldValue ->
            onValueChange(Money.fromString(newValue.text))
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(label, style = MaterialTheme.typography.labelLarge) },
        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
        isError = isError,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = CurrencyAmountInputVisualTransformation(),
    )
}

private class CurrencyAmountInputVisualTransformation(
    private val fixedCursorAtTheEnd: Boolean = true,
    private val numberOfDecimals: Int = 2,
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val inputText = text.text

        val newText = AnnotatedString(
            text = inputText,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles,
        )

        val offsetMapping = if (fixedCursorAtTheEnd) {
            FixedCursorOffsetMapping(
                contentLength = inputText.length,
                formattedContentLength = inputText.length,
            )
        } else {
            MovableCursorOffsetMapping(
                unmaskedText = text.toString(),
                maskedText = newText.toString(),
                decimalDigits = numberOfDecimals,
            )
        }

        return TransformedText(newText, offsetMapping)
    }

    private class FixedCursorOffsetMapping(
        private val contentLength: Int,
        private val formattedContentLength: Int,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = formattedContentLength
        override fun transformedToOriginal(offset: Int): Int = contentLength
    }

    private class MovableCursorOffsetMapping(
        private val unmaskedText: String,
        private val maskedText: String,
        private val decimalDigits: Int,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = when {
            unmaskedText.length <= decimalDigits -> {
                maskedText.length - (unmaskedText.length - offset)
            }

            else -> {
                offset + offsetMaskCount(offset, maskedText)
            }
        }

        override fun transformedToOriginal(offset: Int): Int = when {
            unmaskedText.length <= decimalDigits -> {
                max(unmaskedText.length - (maskedText.length - offset), 0)
            }

            else -> {
                offset - maskedText.take(offset).count { !it.isDigit() }
            }
        }

        private fun offsetMaskCount(offset: Int, maskedText: String): Int {
            var maskOffsetCount = 0
            var dataCount = 0
            for (maskChar in maskedText) {
                if (!maskChar.isDigit()) {
                    maskOffsetCount++
                } else if (++dataCount > offset) {
                    break
                }
            }
            return maskOffsetCount
        }
    }
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