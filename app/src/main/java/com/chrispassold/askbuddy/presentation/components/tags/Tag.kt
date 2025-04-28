package com.chrispassold.askbuddy.presentation.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.theme.AppTheme
import com.chrispassold.askbuddy.presentation.theme.customColorScheme

object TagDefaults {
    data class Colors(
        val backgroundColor: Color,
        val onBackgroundColor: Color,
    )

    @Composable
    fun colors(
        backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        onBackgroundColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    ): Colors = Colors(
        backgroundColor = backgroundColor,
        onBackgroundColor = onBackgroundColor,
    )
}

@Composable
fun Tag(
    text: String,
    modifier: Modifier = Modifier,
    colors: TagDefaults.Colors = TagDefaults.colors(),
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = colors.backgroundColor,
    ) {
        Text(
            text = text,
            color = colors.onBackgroundColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Tag(text = "Default")
            Tag(
                text = "Checkings",
                colors = TagDefaults.colors(
                    backgroundColor = MaterialTheme.customColorScheme.checkingsBackground,
                    onBackgroundColor = MaterialTheme.customColorScheme.onCheckingsBackground,
                ),
            )
            Tag(
                text = "Savings",
                colors = TagDefaults.colors(
                    backgroundColor = MaterialTheme.customColorScheme.savingsBackground,
                    onBackgroundColor = MaterialTheme.customColorScheme.onSavingsBackground,
                ),
            )
        }
    }
}