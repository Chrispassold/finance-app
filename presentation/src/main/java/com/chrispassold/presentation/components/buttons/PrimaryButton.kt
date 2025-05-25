package com.chrispassold.presentation.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.components.progress.CircularIndicatorSize
import com.chrispassold.presentation.components.progress.IndeterminateCircularIndicator
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        enabled = enabled && !isLoading,
        onClick = onClick,
        modifier = modifier,
    ) {
        if (isLoading) {
            IndeterminateCircularIndicator(
                size = CircularIndicatorSize.SMALL,
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
            )
        }

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
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PrimaryButton(modifier = Modifier.fillMaxWidth(), text = "Login", onClick = {})
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Login",
                onClick = {},
                isLoading = true,
            )
        }
    }
}