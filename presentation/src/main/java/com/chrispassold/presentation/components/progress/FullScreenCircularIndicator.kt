package com.chrispassold.presentation.components.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FullScreenCircularIndicator(
    modifier: Modifier = Modifier,
    indicatorSize: CircularIndicatorSize = CircularIndicatorSize.MEDIUM,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        IndeterminateCircularIndicator(
            size = indicatorSize,
        )
    }
}