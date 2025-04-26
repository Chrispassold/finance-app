package com.chrispassold.askbuddy.presentation.features.profile.bankaccounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.domain.models.Money
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.components.containers.ScreenContainer
import com.chrispassold.askbuddy.presentation.theme.AppTheme

@Composable
fun BankAccountsScreen(
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
        Column {
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
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
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
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                tint = Color.White,
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
private fun Tag(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.padding(2.dp),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Composable
private fun TotalAmountAccounts(
    totalAmount: Money,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
        BankAccountsScreen(
            onNewBankAccount = {},
            onBack = {},
        )
    }
}