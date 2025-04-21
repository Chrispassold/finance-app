package com.chrispassold.askbuddy.ui.components.items

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.ui.theme.AppTheme

@Composable
fun ListItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp),
            )
        },
        headlineContent = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Go to",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp),
            )
        },
    )
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ListItem(
            icon = Icons.Filled.Info,
            text = "Example Item",
            modifier = Modifier.padding(16.dp),
        )
    }
}