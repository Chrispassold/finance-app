package com.chrispassold.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.chrispassold.presentation.extensions.LocalUiMode
import com.chrispassold.presentation.extensions.UiMode
import com.chrispassold.presentation.extensions.ifTrue

private val lightScheme = lightColorScheme(
    primary = com.chrispassold.presentation.theme.primaryLight,
    onPrimary = com.chrispassold.presentation.theme.onPrimaryLight,
    primaryContainer = com.chrispassold.presentation.theme.primaryContainerLight,
    onPrimaryContainer = com.chrispassold.presentation.theme.onPrimaryContainerLight,
    secondary = com.chrispassold.presentation.theme.secondaryLight,
    onSecondary = com.chrispassold.presentation.theme.onSecondaryLight,
    secondaryContainer = com.chrispassold.presentation.theme.secondaryContainerLight,
    onSecondaryContainer = com.chrispassold.presentation.theme.onSecondaryContainerLight,
    tertiary = com.chrispassold.presentation.theme.tertiaryLight,
    onTertiary = com.chrispassold.presentation.theme.onTertiaryLight,
    tertiaryContainer = com.chrispassold.presentation.theme.tertiaryContainerLight,
    onTertiaryContainer = com.chrispassold.presentation.theme.onTertiaryContainerLight,
    error = com.chrispassold.presentation.theme.errorLight,
    onError = com.chrispassold.presentation.theme.onErrorLight,
    errorContainer = com.chrispassold.presentation.theme.errorContainerLight,
    onErrorContainer = com.chrispassold.presentation.theme.onErrorContainerLight,
    background = com.chrispassold.presentation.theme.backgroundLight,
    onBackground = com.chrispassold.presentation.theme.onBackgroundLight,
    surface = com.chrispassold.presentation.theme.surfaceLight,
    onSurface = com.chrispassold.presentation.theme.onSurfaceLight,
    surfaceVariant = com.chrispassold.presentation.theme.surfaceVariantLight,
    onSurfaceVariant = com.chrispassold.presentation.theme.onSurfaceVariantLight,
    outline = com.chrispassold.presentation.theme.outlineLight,
    outlineVariant = com.chrispassold.presentation.theme.outlineVariantLight,
    scrim = com.chrispassold.presentation.theme.scrimLight,
    inverseSurface = com.chrispassold.presentation.theme.inverseSurfaceLight,
    inverseOnSurface = com.chrispassold.presentation.theme.inverseOnSurfaceLight,
    inversePrimary = com.chrispassold.presentation.theme.inversePrimaryLight,
    surfaceDim = com.chrispassold.presentation.theme.surfaceDimLight,
    surfaceBright = com.chrispassold.presentation.theme.surfaceBrightLight,
    surfaceContainerLowest = com.chrispassold.presentation.theme.surfaceContainerLowestLight,
    surfaceContainerLow = com.chrispassold.presentation.theme.surfaceContainerLowLight,
    surfaceContainer = com.chrispassold.presentation.theme.surfaceContainerLight,
    surfaceContainerHigh = com.chrispassold.presentation.theme.surfaceContainerHighLight,
    surfaceContainerHighest = com.chrispassold.presentation.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = com.chrispassold.presentation.theme.primaryDark,
    onPrimary = com.chrispassold.presentation.theme.onPrimaryDark,
    primaryContainer = com.chrispassold.presentation.theme.primaryContainerDark,
    onPrimaryContainer = com.chrispassold.presentation.theme.onPrimaryContainerDark,
    secondary = com.chrispassold.presentation.theme.secondaryDark,
    onSecondary = com.chrispassold.presentation.theme.onSecondaryDark,
    secondaryContainer = com.chrispassold.presentation.theme.secondaryContainerDark,
    onSecondaryContainer = com.chrispassold.presentation.theme.onSecondaryContainerDark,
    tertiary = com.chrispassold.presentation.theme.tertiaryDark,
    onTertiary = com.chrispassold.presentation.theme.onTertiaryDark,
    tertiaryContainer = com.chrispassold.presentation.theme.tertiaryContainerDark,
    onTertiaryContainer = com.chrispassold.presentation.theme.onTertiaryContainerDark,
    error = com.chrispassold.presentation.theme.errorDark,
    onError = com.chrispassold.presentation.theme.onErrorDark,
    errorContainer = com.chrispassold.presentation.theme.errorContainerDark,
    onErrorContainer = com.chrispassold.presentation.theme.onErrorContainerDark,
    background = com.chrispassold.presentation.theme.backgroundDark,
    onBackground = com.chrispassold.presentation.theme.onBackgroundDark,
    surface = com.chrispassold.presentation.theme.surfaceDark,
    onSurface = com.chrispassold.presentation.theme.onSurfaceDark,
    surfaceVariant = com.chrispassold.presentation.theme.surfaceVariantDark,
    onSurfaceVariant = com.chrispassold.presentation.theme.onSurfaceVariantDark,
    outline = com.chrispassold.presentation.theme.outlineDark,
    outlineVariant = com.chrispassold.presentation.theme.outlineVariantDark,
    scrim = com.chrispassold.presentation.theme.scrimDark,
    inverseSurface = com.chrispassold.presentation.theme.inverseSurfaceDark,
    inverseOnSurface = com.chrispassold.presentation.theme.inverseOnSurfaceDark,
    inversePrimary = com.chrispassold.presentation.theme.inversePrimaryDark,
    surfaceDim = com.chrispassold.presentation.theme.surfaceDimDark,
    surfaceBright = com.chrispassold.presentation.theme.surfaceBrightDark,
    surfaceContainerLowest = com.chrispassold.presentation.theme.surfaceContainerLowestDark,
    surfaceContainerLow = com.chrispassold.presentation.theme.surfaceContainerLowDark,
    surfaceContainer = com.chrispassold.presentation.theme.surfaceContainerDark,
    surfaceContainerHigh = com.chrispassold.presentation.theme.surfaceContainerHighDark,
    surfaceContainerHighest = com.chrispassold.presentation.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = com.chrispassold.presentation.theme.primaryLightMediumContrast,
    onPrimary = com.chrispassold.presentation.theme.onPrimaryLightMediumContrast,
    primaryContainer = com.chrispassold.presentation.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = com.chrispassold.presentation.theme.onPrimaryContainerLightMediumContrast,
    secondary = com.chrispassold.presentation.theme.secondaryLightMediumContrast,
    onSecondary = com.chrispassold.presentation.theme.onSecondaryLightMediumContrast,
    secondaryContainer = com.chrispassold.presentation.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = com.chrispassold.presentation.theme.onSecondaryContainerLightMediumContrast,
    tertiary = com.chrispassold.presentation.theme.tertiaryLightMediumContrast,
    onTertiary = com.chrispassold.presentation.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = com.chrispassold.presentation.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = com.chrispassold.presentation.theme.onTertiaryContainerLightMediumContrast,
    error = com.chrispassold.presentation.theme.errorLightMediumContrast,
    onError = com.chrispassold.presentation.theme.onErrorLightMediumContrast,
    errorContainer = com.chrispassold.presentation.theme.errorContainerLightMediumContrast,
    onErrorContainer = com.chrispassold.presentation.theme.onErrorContainerLightMediumContrast,
    background = com.chrispassold.presentation.theme.backgroundLightMediumContrast,
    onBackground = com.chrispassold.presentation.theme.onBackgroundLightMediumContrast,
    surface = com.chrispassold.presentation.theme.surfaceLightMediumContrast,
    onSurface = com.chrispassold.presentation.theme.onSurfaceLightMediumContrast,
    surfaceVariant = com.chrispassold.presentation.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = com.chrispassold.presentation.theme.onSurfaceVariantLightMediumContrast,
    outline = com.chrispassold.presentation.theme.outlineLightMediumContrast,
    outlineVariant = com.chrispassold.presentation.theme.outlineVariantLightMediumContrast,
    scrim = com.chrispassold.presentation.theme.scrimLightMediumContrast,
    inverseSurface = com.chrispassold.presentation.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = com.chrispassold.presentation.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = com.chrispassold.presentation.theme.inversePrimaryLightMediumContrast,
    surfaceDim = com.chrispassold.presentation.theme.surfaceDimLightMediumContrast,
    surfaceBright = com.chrispassold.presentation.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = com.chrispassold.presentation.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = com.chrispassold.presentation.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = com.chrispassold.presentation.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = com.chrispassold.presentation.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = com.chrispassold.presentation.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = com.chrispassold.presentation.theme.primaryLightHighContrast,
    onPrimary = com.chrispassold.presentation.theme.onPrimaryLightHighContrast,
    primaryContainer = com.chrispassold.presentation.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = com.chrispassold.presentation.theme.onPrimaryContainerLightHighContrast,
    secondary = com.chrispassold.presentation.theme.secondaryLightHighContrast,
    onSecondary = com.chrispassold.presentation.theme.onSecondaryLightHighContrast,
    secondaryContainer = com.chrispassold.presentation.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = com.chrispassold.presentation.theme.onSecondaryContainerLightHighContrast,
    tertiary = com.chrispassold.presentation.theme.tertiaryLightHighContrast,
    onTertiary = com.chrispassold.presentation.theme.onTertiaryLightHighContrast,
    tertiaryContainer = com.chrispassold.presentation.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = com.chrispassold.presentation.theme.onTertiaryContainerLightHighContrast,
    error = com.chrispassold.presentation.theme.errorLightHighContrast,
    onError = com.chrispassold.presentation.theme.onErrorLightHighContrast,
    errorContainer = com.chrispassold.presentation.theme.errorContainerLightHighContrast,
    onErrorContainer = com.chrispassold.presentation.theme.onErrorContainerLightHighContrast,
    background = com.chrispassold.presentation.theme.backgroundLightHighContrast,
    onBackground = com.chrispassold.presentation.theme.onBackgroundLightHighContrast,
    surface = com.chrispassold.presentation.theme.surfaceLightHighContrast,
    onSurface = com.chrispassold.presentation.theme.onSurfaceLightHighContrast,
    surfaceVariant = com.chrispassold.presentation.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = com.chrispassold.presentation.theme.onSurfaceVariantLightHighContrast,
    outline = com.chrispassold.presentation.theme.outlineLightHighContrast,
    outlineVariant = com.chrispassold.presentation.theme.outlineVariantLightHighContrast,
    scrim = com.chrispassold.presentation.theme.scrimLightHighContrast,
    inverseSurface = com.chrispassold.presentation.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = com.chrispassold.presentation.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = com.chrispassold.presentation.theme.inversePrimaryLightHighContrast,
    surfaceDim = com.chrispassold.presentation.theme.surfaceDimLightHighContrast,
    surfaceBright = com.chrispassold.presentation.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = com.chrispassold.presentation.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = com.chrispassold.presentation.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = com.chrispassold.presentation.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = com.chrispassold.presentation.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = com.chrispassold.presentation.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = com.chrispassold.presentation.theme.primaryDarkMediumContrast,
    onPrimary = com.chrispassold.presentation.theme.onPrimaryDarkMediumContrast,
    primaryContainer = com.chrispassold.presentation.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = com.chrispassold.presentation.theme.onPrimaryContainerDarkMediumContrast,
    secondary = com.chrispassold.presentation.theme.secondaryDarkMediumContrast,
    onSecondary = com.chrispassold.presentation.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = com.chrispassold.presentation.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = com.chrispassold.presentation.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = com.chrispassold.presentation.theme.tertiaryDarkMediumContrast,
    onTertiary = com.chrispassold.presentation.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = com.chrispassold.presentation.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = com.chrispassold.presentation.theme.onTertiaryContainerDarkMediumContrast,
    error = com.chrispassold.presentation.theme.errorDarkMediumContrast,
    onError = com.chrispassold.presentation.theme.onErrorDarkMediumContrast,
    errorContainer = com.chrispassold.presentation.theme.errorContainerDarkMediumContrast,
    onErrorContainer = com.chrispassold.presentation.theme.onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color, val onColor: Color, val colorContainer: Color, val onColorContainer: Color,
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified,
)


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    CompositionLocalProvider(
        LocalUiMode provides if (darkTheme) UiMode.Dark else UiMode.Light,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content,
        )
    }
}

@Composable
fun AppThemePreview(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    AppTheme {
        Column(
            modifier = Modifier
                .ifTrue(paddingValues != null) {
                    padding(paddingValues!!)
                }
                .then(modifier),
            content = content,
        )
    }
}

