package com.chrispassold.presentation.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
        ) {
            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Login", onClick = {})
        }
    }
}