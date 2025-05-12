package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.R
import com.chrispassold.domain.models.Money
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.components.containers.ScreenContainer
import com.chrispassold.presentation.components.tags.Tag
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun ListBankAccountsScreen(
    onNewBankAccount: () -> Unit,
    onBack: () -> Unit,
) {
    ScreenContainer(
        appBarTitle = stringResource(R.string.bank_accounts_screen_title),
        onBack = onBack,
        rightActionIcon = Icons.Filled.Add,
        rightActionIconContentDescription = "Add",
        onRightAction = onNewBankAccount,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TotalAmountAccounts(
                Money(100_001.50),
            )
            BankAccount(
                bankName = "My Bank",
                category = "Savings",
                value = Money(100_000.0),
                icon = Icons.Filled.Home,
            )
            BankAccount(
                bankName = "Other Bank",
                category = "Checking",
                value = Money(1.50),
                icon = Icons.Filled.FoodBank,
            )
        }
    }
}

@Composable
private fun BankAccount(
    bankName: String,
    category: String,
    value: Money,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        headlineContent = {
            Text(text = bankName, style = MaterialTheme.typography.titleMedium)
        },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        supportingContent = {
            Tag(text = category)
        },
        trailingContent = {
            Text(text = value.format(), style = MaterialTheme.typography.labelLarge)
        },
    )
}

@Composable
private fun TotalAmountAccounts(
    totalAmount: Money,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.total_amount_in_accounts),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(text = totalAmount.format(), style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ListBankAccountsScreen(
            onNewBankAccount = {},
            onBack = {},
        )
    }
}