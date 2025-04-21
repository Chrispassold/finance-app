package com.chrispassold.askbuddy.ui.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.ui.components.avatars.Avatar
import com.chrispassold.askbuddy.ui.components.avatars.AvatarImage
import com.chrispassold.askbuddy.ui.components.avatars.AvatarSize
import com.chrispassold.askbuddy.ui.components.containers.ScreenContainer
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    onNavigateToBankAccounts: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToPersonalInformation: () -> Unit,
    onNavigateToCreditCards: () -> Unit,
    onExitApp: () -> Unit,
) {
    ScreenContainer {
        Header()
        Content()
    }
}


@Composable
private fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Row {
            Avatar(
                image = AvatarImage.Icon(icon = Icons.Filled.Person),
                avatarSize = AvatarSize.Large,
            )
        }
        Row {
            Text(
                text = "John Doe",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .background(Color.Blue, RoundedCornerShape(8.dp)),
    ) {

        Spacer(modifier = Modifier.padding(50.dp))

    }
}


@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ProfileScreen(
            onNavigateToBankAccounts = {},
            onNavigateToCategories = {},
            onNavigateToPersonalInformation = {},
            onNavigateToCreditCards = {},
            onExitApp = {},
        )
    }
}