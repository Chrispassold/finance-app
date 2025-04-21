package com.chrispassold.askbuddy.extensions

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF19120C,
)
annotation class PreviewDarkMode

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFF8F5,
)
annotation class PreviewLightMode

@PreviewDarkMode
@PreviewLightMode
annotation class PreviewUiModes