package com.chrispassold.askbuddy.ui.components.texts

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.chrispassold.askbuddy.extensions.PreviewDarkMode
import com.chrispassold.askbuddy.extensions.PreviewLightMode
import com.chrispassold.askbuddy.ui.theme.AppTheme

@Composable
fun TextLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier.clickable(onClick = onClick),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelLarge.copy(textAlign = TextAlign.Center),
    )
}

@PreviewLightMode
@PreviewDarkMode
@Composable
private fun TextLinkPreview() {
    AppTheme {
        TextLink(text = "Click me", onClick = {})
    }
}

@PreviewLightMode
@PreviewDarkMode
@Composable
private fun TextLinkLongTextPreview() {
    AppTheme {
        TextLink(text = "Create an account with this email address", onClick = {})
    }
}