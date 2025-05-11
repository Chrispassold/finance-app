package com.chrispassold.askbuddy.presentation.features.profile.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.components.containers.Widget
import com.chrispassold.askbuddy.presentation.theme.AppThemePreview

@Composable
fun CategoryWidget(
    icon: ImageVector,
    iconContentDescription: String,
    subCategoriesCount: Int,
    name: String,
    onClick: (name: String) -> Unit,
) {
    Widget(
        onClick = {
            onClick(name)
        },
    ) {
        Column(
            modifier = Modifier
                .width(120.dp)
                .wrapContentSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), // Make the Row take up the available width
                horizontalArrangement = Arrangement.SpaceBetween, // Arrange items with space between
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f),
                            CircleShape,
                        )
                        .padding(4.dp),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = iconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (subCategoriesCount > 0) {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ) {
                        Text("$subCategoriesCount", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppThemePreview {
        CategoryWidget(
            icon = Icons.Filled.House,
            iconContentDescription = "House",
            subCategoriesCount = 5,
            name = "House",
            onClick = {},
        )
    }
}