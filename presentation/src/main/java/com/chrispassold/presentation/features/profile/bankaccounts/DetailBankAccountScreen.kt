package com.chrispassold.presentation.features.profile.bankaccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.BankAccountType
import com.chrispassold.domain.models.Money
import com.chrispassold.presentation.R
import com.chrispassold.presentation.components.avatars.Avatar
import com.chrispassold.presentation.components.avatars.AvatarImage
import com.chrispassold.presentation.components.avatars.AvatarSize
import com.chrispassold.presentation.components.buttons.PrimaryButton
import com.chrispassold.presentation.components.containers.ScreenContainer
import com.chrispassold.presentation.components.inputs.MoneyInput
import com.chrispassold.presentation.components.inputs.TextInput
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.formatters.BankAccountTypeFormatter
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun DetailBankAccountScreen(
    state: DetailBankAccountUiState,
    onEvent: (DetailBankAccountUiEvent) -> Unit,
    effect: DetailBankAccountUiEffect?,
    onBack: () -> Unit,
) {
    val bankAccountName =
        remember(state.bankAccountName) { TextFieldValue(state.bankAccountName ?: "") }
    val initialValueMoney = remember(state.initialValue) { Money(state.initialValue) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {
        when (effect) {
            DetailBankAccountUiEffect.NavigateBack -> {
                onBack()
            }

            is DetailBankAccountUiEffect.ShowSnackBar -> {
                snackbarHostState.showSnackbar(effect.message)
            }

            else -> Unit
        }
    }

    ScreenContainer(
        appBarTitle = stringResource(R.string.bank_accounts_screen_title),
        onBack = {
            onEvent(DetailBankAccountUiEvent.OnBackClicked)
        },
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
                label = stringResource(R.string.label_bank_account_name),
                value = bankAccountName,
                onValueChange = {
                    onEvent(DetailBankAccountUiEvent.BankAccountNameChanged(it.text))
                },
            )
            MoneyInput(
                label = stringResource(R.string.label_initial_value),
                value = initialValueMoney,
                onValueChange = {
                    onEvent(DetailBankAccountUiEvent.InitialValueChanged(it.amount))
                },
            )
            BankAccountTypeChooser(
                options = BankAccountType.entries,
                selected = state.type,
                onChange = {
                    onEvent(DetailBankAccountUiEvent.TypeChanged(it))
                },
            )
            TextWithSwitch(
                label = stringResource(R.string.hide_from_balance),
                checked = state.hideFromBalanceCheck,
                onCheckedChange = {
                    onEvent(DetailBankAccountUiEvent.HideFromBalanceCheckChanged(it))
                },
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.save),
                onClick = {
                    onEvent(DetailBankAccountUiEvent.Submit)
                },
            )
        }
    }
}

@Composable
private fun BankAccountTypeChooser(
    options: List<BankAccountType>,
    selected: BankAccountType,
    onChange: (BankAccountType) -> Unit,
) {
    var selectedIndex by remember(selected) { mutableIntStateOf(options.indexOf(selected)) }

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEachIndexed { index, type ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size,
                ),
                onClick = {
                    onChange(options[index])
                },
                selected = index == selectedIndex,
                label = { Text(BankAccountTypeFormatter.format(type)) },
            )
        }
    }
}

@Composable
private fun TextWithSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        DetailBankAccountScreen(
            state = DetailBankAccountUiState(),
            onEvent = {},
            effect = null,
            onBack = {},
        )
    }
}