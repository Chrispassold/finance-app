package com.chrispassold.askbuddy.features.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.chrispassold.askbuddy.features.login.components.OnSocialMediaButtonClick
import com.chrispassold.askbuddy.features.login.components.SocialMedia
import com.chrispassold.askbuddy.features.login.components.SocialMediaButton
import com.chrispassold.askbuddy.ui.components.buttons.PrimaryButton
import com.chrispassold.askbuddy.ui.components.inputs.PasswordInput
import com.chrispassold.askbuddy.ui.components.inputs.TextInput
import com.chrispassold.askbuddy.ui.components.separators.SeparatorWithText
import com.chrispassold.askbuddy.ui.components.texts.TextLink
import com.chrispassold.askbuddy.ui.extensions.PreviewUiModes
import com.chrispassold.askbuddy.ui.theme.AppTheme

@Composable
fun LoginComponent(
    onCreateAccount: () -> Unit,
    onForgetPassword: () -> Unit,
    onSocialMediaLogin: OnSocialMediaButtonClick,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Text(
            text = stringResource(R.string.login_welcome),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.login_welcome_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.padding(24.dp))
        SocialMediaContent(onSocialMediaLogin = onSocialMediaLogin)
        Spacer(modifier = Modifier.padding(12.dp))
        SeparatorWithText(text = stringResource(R.string.or))
        Spacer(modifier = Modifier.padding(12.dp))
        EmailLoginContent(
            onForgetPassword = onForgetPassword,
            onSubmit = onSubmit,
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            TextLink(
                text = stringResource(R.string.ask_create_a_new_account),
                onClick = onCreateAccount,
            )
        }
    }
}

@Composable
private fun EmailLoginContent(
    onForgetPassword: () -> Unit,
    onSubmit: () -> Unit,
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
        TextLink(
            text = stringResource(R.string.ask_forgot_password),
            modifier = Modifier.align(Alignment.End),
            onClick = onForgetPassword,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onClick = onSubmit,
        )
    }
}

@Composable
private fun SocialMediaContent(
    onSocialMediaLogin: OnSocialMediaButtonClick,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SocialMediaButton(socialMedia = SocialMedia.Google, onClick = onSocialMediaLogin)
        Spacer(modifier = Modifier.padding(8.dp))
        SocialMediaButton(socialMedia = SocialMedia.Apple, onClick = onSocialMediaLogin)
        Spacer(modifier = Modifier.padding(8.dp))
        SocialMediaButton(socialMedia = SocialMedia.Facebook, onClick = onSocialMediaLogin)
    }
}

@PreviewUiModes
@Composable
private fun PreviewLoginComponent() {
    AppTheme {
        Column {
            LoginComponent(
                onCreateAccount = {}, onForgetPassword = {},
                onSocialMediaLogin = {}, onSubmit = {},
            )
        }
    }
}