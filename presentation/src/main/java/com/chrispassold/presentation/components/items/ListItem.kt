package com.chrispassold.presentation.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme


@Composable
fun ListItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val backgroundColor: Color
    val textColor: Color
    val iconColor: Color
    when (enabled) {
        true -> {
            backgroundColor = MaterialTheme.colorScheme.surface
            textColor = MaterialTheme.colorScheme.onSurface
            iconColor = MaterialTheme.colorScheme.onSurface
        }

        false -> {
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            textColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            iconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        }
    }
    ListItem(
        modifier = modifier
            .background(color = backgroundColor)
            .clickable(enabled = enabled, onClick = onClick),
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp),
            )
        },
        headlineContent = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor,
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Go to",
                tint = iconColor,
                modifier = Modifier.size(24.dp),
            )
        },
    )
}

@PreviewUiModes
@Composable
private fun PreviewEnabled() {
    AppTheme {
        ListItem(
            enabled = true,
            icon = Icons.Filled.Info,
            text = "Example Item",
            modifier = Modifier.padding(16.dp),
            onClick = {},
        )
    }
}

@PreviewUiModes
@Composable
private fun PreviewDisabled() {
    AppTheme {
        ListItem(
            enabled = false,
            icon = Icons.Filled.Info,
            text = "Example Item",
            modifier = Modifier.padding(16.dp),
            onClick = {},
        )
    }
}