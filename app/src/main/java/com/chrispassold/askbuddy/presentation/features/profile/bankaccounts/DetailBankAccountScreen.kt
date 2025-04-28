package com.chrispassold.askbuddy.presentation.features.profile.bankaccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.components.containers.ScreenContainer
import com.chrispassold.askbuddy.presentation.theme.AppTheme

@Composable
fun DetailBankAccountScreen(
    onBack: () -> Unit,
) {
    ScreenContainer(
        appBarTitle = stringResource(R.string.bank_accounts_screen_title),
        onBack = onBack,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Detail")
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