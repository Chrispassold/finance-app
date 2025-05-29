package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.models.Money
import com.chrispassold.presentation.R
import com.chrispassold.presentation.components.containers.ScreenContainer
import com.chrispassold.presentation.components.tags.Tag
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.formatters.BankAccountTypeFormatter
import com.chrispassold.presentation.formatters.MoneyFormatter
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun ListBankAccountsScreen(
    state: ListBankAccountUiState,
    onEvent: (ListBankAccountUiEvent) -> Unit,
    effect: ListBankAccountUiEffect?,
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {
        when (effect) {
            is ListBankAccountUiEffect.ShowSnackBar -> {
                snackbarHostState.showSnackbar(effect.message)
            }

            else -> Unit
        }
    }

    ScreenContainer(
        appBarTitle = stringResource(R.string.bank_accounts_screen_title),
        onBack = {
            onEvent(ListBankAccountUiEvent.OnBackClicked)
        },
        rightActionIcon = Icons.Filled.Add,
        rightActionIconContentDescription = "Add",
        onRightAction = {
            onEvent(ListBankAccountUiEvent.OnNewBankAccountClicked)
        },
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TotalAmountAccounts(state.totalAmount)
            state.bankAccounts.forEach {
                BankAccount(
                    bankName = it.name,
                    type = it.type,
                    value = it.initialAmount,
                    icon = Icons.Filled.Home, //todo add icon from domain
                    onClick = {
                        onEvent(ListBankAccountUiEvent.OnBankAccountClicked(it))
                    },
                )
            }
        }
    }
}

@Composable
private fun BankAccount(
    bankName: String,
    type: BankAccountType,
    value: Money,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
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
            Tag(text = BankAccountTypeFormatter.format(type))
        },
        trailingContent = {
            Text(text = MoneyFormatter.format(value), style = MaterialTheme.typography.labelLarge)
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
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.total_amount_in_accounts),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = MoneyFormatter.format(totalAmount),
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ListBankAccountsScreen(
            state = ListBankAccountUiState(),
            onEvent = {},
            effect = null,
        )
    }
}