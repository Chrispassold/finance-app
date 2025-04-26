package com.chrispassold.askbuddy.presentation.features.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.presentation.features.login.components.LoginFooterMessage
import com.chrispassold.askbuddy.presentation.features.login.components.PasswordBasedLoginComponent
import com.chrispassold.askbuddy.presentation.features.login.components.SocialMediaGroup
import com.chrispassold.askbuddy.presentation.components.containers.ScreenContainer
import com.chrispassold.askbuddy.presentation.components.separators.SeparatorWithText
import com.chrispassold.askbuddy.extensions.PreviewUiModes
import com.chrispassold.askbuddy.presentation.theme.AppTheme

@Composable
fun SignInScreen(
    onNavigateToSignUp: () -> Unit,
    onForgetPassword: () -> Unit,
    onSignIn: () -> Unit,
) {
    ScreenContainer {
        Text(
            text = stringResource(R.string.sign_in_greeting),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.sign_in_greeting_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.padding(24.dp))
        SocialMediaGroup(
            textFormat = stringResource(R.string.sign_in_with),
            onSocialMediaClick = {/*todo*/ },
        )
        Spacer(modifier = Modifier.padding(12.dp))
        SeparatorWithText(text = stringResource(R.string.or))
        Spacer(modifier = Modifier.padding(12.dp))
        PasswordBasedLoginComponent(
            onForgetPassword = onForgetPassword,
            onSubmit = onSignIn,
            buttonText = stringResource(R.string.sign_in),
        )
        Spacer(modifier = Modifier.weight(1f))
        LoginFooterMessage(
            message = stringResource(R.string.do_not_have_an_account),
            link = stringResource(R.string.sign_up),
            onLinkClick = onNavigateToSignUp,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        SignInScreen(
            onNavigateToSignUp = {}, onForgetPassword = {},
            onSignIn = {},
        )
    }
}