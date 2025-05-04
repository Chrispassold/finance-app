package com.chrispassold.askbuddy.presentation.features.profile.bankaccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.domain.models.Money
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.components.avatars.Avatar
import com.chrispassold.askbuddy.presentation.components.avatars.AvatarImage
import com.chrispassold.askbuddy.presentation.components.avatars.AvatarSize
import com.chrispassold.askbuddy.presentation.components.containers.ScreenContainer
import com.chrispassold.askbuddy.presentation.components.inputs.MoneyInput
import com.chrispassold.askbuddy.presentation.components.inputs.TextInput
import com.chrispassold.askbuddy.presentation.theme.AppTheme

@Composable
fun DetailBankAccountScreen(
    onBack: () -> Unit,
) {
    var bankAccountName by remember { mutableStateOf(TextFieldValue("")) }
    var initialValue by remember { mutableStateOf(Money.zero()) }

    ScreenContainer(
        appBarTitle = stringResource(R.string.bank_accounts_screen_title),
        onBack = onBack,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Avatar(
                image = AvatarImage.Icon(icon = Icons.Filled.Home),
                avatarSize = AvatarSize.Large,
            )
            TextInput(
                label = "Bank Account Name",
                value = bankAccountName,
                onValueChange = { bankAccountName = it },
            )
            MoneyInput(
                label = "Initial value",
                value = initialValue,
                onValueChange = { initialValue = it },
            )

        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        DetailBankAccountScreen(
            onBack = {},
        )
    }
}