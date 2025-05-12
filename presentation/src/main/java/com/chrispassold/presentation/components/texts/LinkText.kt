package com.chrispassold.presentation.components.texts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.extensions.PreviewDarkMode
import com.chrispassold.presentation.extensions.PreviewLightMode
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun TextLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.labelLarge,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
            color = color,
            style = style.copy(textAlign = TextAlign.Center),
        )
    }
}

@PreviewLightMode
@PreviewDarkMode
@Composable
private fun TextLinkPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            TextLink(text = "Click me", onClick = {})
        }
    }
}

@PreviewLightMode
@PreviewDarkMode
@Composable
private fun TextLinkLongTextPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            TextLink(text = "Create an account with this email address", onClick = {})
        }
    }
}