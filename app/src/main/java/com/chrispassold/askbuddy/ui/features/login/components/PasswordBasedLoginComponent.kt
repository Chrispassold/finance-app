package com.chrispassold.askbuddy.ui.features.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.chrispassold.askbuddy.ui.components.buttons.PrimaryButton
import com.chrispassold.askbuddy.ui.components.inputs.PasswordInput
import com.chrispassold.askbuddy.ui.components.inputs.TextInput
import com.chrispassold.askbuddy.ui.components.texts.TextLink
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.ui.theme.AppTheme

@Composable
fun PasswordBasedLoginComponent(
    buttonText: String,
    onSubmit: () -> Unit,
    onForgetPassword: (() -> Unit)? = null,
) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.login_username_label),
            value = username,
            onValueChange = { username = it },
        )
        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.login_password_label),
            value = password,
            onValueChange = { password = it },
        )
        if (onForgetPassword != null){
            TextLink(
                text = stringResource(R.string.ask_forgot_password),
                modifier = Modifier.align(Alignment.End),
                onClick = onForgetPassword,
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = buttonText,
            onClick = onSubmit,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        Column(modifier = Modifier.padding(24.dp)) {
            PasswordBasedLoginComponent(
                onForgetPassword = {},
                onSubmit = {},
                buttonText = stringResource(R.string.sign_in),
            )
        }
    }
}