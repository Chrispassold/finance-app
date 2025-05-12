package com.chrispassold.presentation.features.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.components.texts.TextLink
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun LoginFooterMessage(
    message: String,
    link: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        TextLink(
            text = link,
            onClick = onLinkClick,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        Column(modifier = Modifier.padding(24.dp)) {
            LoginFooterMessage(
                message = "Don't have an account?",
                link = "Sign up",
                onLinkClick = {},
            )
        }
    }
}