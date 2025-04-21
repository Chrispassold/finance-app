package com.chrispassold.askbuddy.ui.components.buttons

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.ui.theme.AppTheme

@Composable
fun LinkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        LinkButton(
            text = "Create a new account",
            onClick = {},
            modifier = Modifier,
        )
    }
}