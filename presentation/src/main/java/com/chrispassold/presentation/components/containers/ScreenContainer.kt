package com.chrispassold.presentation.components.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    appBarTitle: String? = null,
    onBack: (() -> Unit)? = null,
    onRightAction: (() -> Unit)? = null,
    rightActionIcon: ImageVector? = null,
    rightActionIconContentDescription: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (appBarTitle != null) {
                    AppBar(
                        appBarTitle = appBarTitle,
                        onBack = onBack,
                        onRightAction = onRightAction,
                        rightActionIcon = rightActionIcon,
                        rightActionIconContentDescription = rightActionIconContentDescription,
                    )
                }
            },
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                content = content,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    appBarTitle: String,
    onBack: (() -> Unit)? = null,
    onRightAction: (() -> Unit)? = null,
    rightActionIcon: ImageVector? = null,
    rightActionIconContentDescription: String? = null,
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = appBarTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        },
        actions = {
            if (onRightAction != null) {
                if (rightActionIcon != null) {
                    IconButton(onClick = onRightAction) {
                        Icon(
                            imageVector = rightActionIcon,
                            contentDescription = rightActionIconContentDescription,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }
        },
    )
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ScreenContainer(
            appBarTitle = "My App Screen",
            onBack = { /* Handle back navigation */ },
            onRightAction = { /* Handle right action */ },
            rightActionIcon = Icons.Filled.Info,
            rightActionIconContentDescription = "Info",
        ) {
            Text("Screen content here")
        }
    }
}