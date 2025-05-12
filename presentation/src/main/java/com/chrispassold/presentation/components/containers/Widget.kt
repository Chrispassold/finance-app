package com.chrispassold.presentation.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun Widget(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(enabled = onClick != null, onClick = { onClick?.invoke() })
            .background(
                MaterialTheme.colorScheme.surfaceContainer,
                MaterialTheme.shapes.small,
            )
            .padding(8.dp),
        content = content,
    )
}

@PreviewUiModes
@Composable
private fun Preview() {
    val widgets = listOf(
        "Widget 1",
        "Widget 2",
        "Widget 3",
        "Widget 4",
    )

    AppTheme {
        FlowRow(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            widgets.forEach {
                Widget {
                    Text(text = "Content", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}