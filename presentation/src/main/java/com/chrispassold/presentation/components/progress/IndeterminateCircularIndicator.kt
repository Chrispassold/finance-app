package com.chrispassold.presentation.components.progress

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.components.progress.CircularIndicatorSize.LARGE
import com.chrispassold.presentation.components.progress.CircularIndicatorSize.MEDIUM
import com.chrispassold.presentation.components.progress.CircularIndicatorSize.SMALL
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppThemePreview

/**
 * Defines standard sizes for circular progress indicators based on common Material Design 3 usage.
 * These sizes represent the overall diameter of the indicator.
 *
 * - [SMALL]: Suitable for inline loading states or compact spaces.
 * - [MEDIUM]: A common default size for general loading indication.
 * - [LARGE]: For more prominent loading states where the indicator is a key focal point.
 */
enum class CircularIndicatorSize(val dp: Dp) {
    /**
     * Small size, typically for inline or compact indicators.
     * Default: 24.dp
     */
    SMALL(24.dp),

    /**
     * Medium size, a common default for general use.
     * Default: 48.dp
     */
    MEDIUM(48.dp),

    /**
     * Large size, for prominent loading indications.
     * Default: 64.dp
     */
    LARGE(64.dp);
}

@Composable
fun IndeterminateCircularIndicator(
    size: CircularIndicatorSize = MEDIUM,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
) {
    CircularProgressIndicator(
        modifier = Modifier.size(size.dp),
        color = indicatorColor,
        trackColor = trackColor,
        strokeWidth = if (size == SMALL) 2.dp else 4.dp,
    )
}

class PreviewParameters : PreviewParameterProvider<CircularIndicatorSize> {
    override val values: Sequence<CircularIndicatorSize>
        get() = CircularIndicatorSize.entries.asSequence()

}

@PreviewUiModes
@Composable
private fun Preview(
    @PreviewParameter(PreviewParameters::class) size: CircularIndicatorSize,
) {
    AppThemePreview {
        IndeterminateCircularIndicator(size = size)
    }
}