package com.chrispassold.askbuddy.features.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.features.login.components.OnSocialMediaButtonClick
import com.chrispassold.askbuddy.features.login.components.SocialMedia
import com.chrispassold.askbuddy.features.login.components.SocialMediaButton
import com.chrispassold.askbuddy.ui.theme.AppTheme
import com.chrispassold.askbuddy.ui.uikit.inputs.PasswordInput
import com.chrispassold.askbuddy.ui.uikit.inputs.TextInput

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
        )
        Text(
            text = stringResource(R.string.login_welcome_description),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.padding(24.dp))
        SocialMediaContent(onSocialMediaLogin = onSocialMediaLogin)
        Spacer(modifier = Modifier.padding(24.dp))
        EmailLoginContent(
            onForgetPassword = onForgetPassword,
            onSubmit = onSubmit,
        )
    }
}

@Composable
private fun EmailLoginContent(
    onForgetPassword: () -> Unit,
    onSubmit: () -> Unit,
) {
    val username by remember { mutableStateOf(TextFieldValue()) }
    val password by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextInput(
            label = stringResource(R.string.login_username_label),
            value = username,
            onValueChange = {},
        )
        Spacer(modifier = Modifier.padding(8.dp))
        PasswordInput(
            label = stringResource(R.string.login_password_label),
            value = password,
            onValueChange = {},
        )
        // todo componentizar botao
        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            contentPadding = PaddingValues(
                horizontal = 24.dp,
                vertical = 12.dp,
            ),
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
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

@Preview(showBackground = true)
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