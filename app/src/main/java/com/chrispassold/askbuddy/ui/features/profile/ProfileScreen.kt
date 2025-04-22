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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.extensions.whenPreview
import com.chrispassold.askbuddy.ui.components.avatars.Avatar
import com.chrispassold.askbuddy.ui.components.avatars.AvatarImage
import com.chrispassold.askbuddy.ui.components.avatars.AvatarSize
import com.chrispassold.askbuddy.ui.components.containers.ScreenContainer
import com.chrispassold.askbuddy.ui.components.items.ListItem
import com.chrispassold.askbuddy.ui.components.texts.TextLink
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
        Spacer(modifier = Modifier.padding(24.dp))
        Header()
        Content(
            modifier = Modifier.weight(1f),
            onNavigateToBankAccounts = onNavigateToBankAccounts,
            onNavigateToCategories = onNavigateToCategories,
            onNavigateToPersonalInformation = onNavigateToPersonalInformation,
            onNavigateToCreditCards = onNavigateToCreditCards,
        )
        Footer(
            onExitApp = onExitApp,
        )
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
                image = whenPreview(
                    ifPreview = { AvatarImage.Drawable(id = com.chrispassold.askbuddy.R.drawable.ic_google_login) },
                    ifNotPreview = { AvatarImage.Uri("https://picsum.photos/50/50".toUri()) },
                ),
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
private fun Content(
    modifier: Modifier = Modifier,
    onNavigateToBankAccounts: () -> Unit,
    onNavigateToCategories: () -> Unit,
    onNavigateToPersonalInformation: () -> Unit,
    onNavigateToCreditCards: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .background(Color.Transparent, RoundedCornerShape(8.dp)),
    ) {

        Column {
            ListItem(
                icon = Icons.Filled.Person,
                text = "Personal Information",
                onClick = onNavigateToPersonalInformation,
            )
            ListItem(
                icon = Icons.Filled.AccountBalance,
                text = "Bank Accounts",
                onClick = onNavigateToBankAccounts,
            )
            ListItem(
                icon = Icons.Filled.Category,
                text = "Categories",
                onClick = onNavigateToCategories,
            )
            ListItem(
                icon = Icons.Filled.CreditCard,
                text = "Credit Cards",
                onClick = onNavigateToCreditCards,
                enabled = false,
            )
        }

    }
}

@Composable
fun Footer(
    onExitApp: () -> Unit,
    modifier: Modifier = Modifier,
    appVersion: String = "1.0.0",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
    ) {
        TextLink(
            text = "SAIR",
            onClick = onExitApp,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            text = appVersion,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )
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