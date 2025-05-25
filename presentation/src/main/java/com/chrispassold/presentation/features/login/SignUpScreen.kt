package com.chrispassold.presentation.features.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.LoginOption
import com.chrispassold.domain.models.SocialMediaOption
import com.chrispassold.presentation.R
import com.chrispassold.presentation.components.containers.ScreenContainer
import com.chrispassold.presentation.components.separators.SeparatorWithText
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.features.login.components.LoginFooterMessage
import com.chrispassold.presentation.features.login.components.PasswordBasedLoginComponent
import com.chrispassold.presentation.features.login.components.SocialMediaGroup
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onAlreadyHaveAccount: () -> Unit,
    navigateOnSignUp: () -> Unit,
) {
    InternalScreen(
        onAlreadyHaveAccount = onAlreadyHaveAccount,
        onSignUp = {
            viewModel.signUp(it) {
                navigateOnSignUp()
            }
        },
    )
}

@Composable
private fun InternalScreen(
    onAlreadyHaveAccount: () -> Unit,
    onSignUp: (LoginOption) -> Unit,
) {
    ScreenContainer {
        Text(
            text = stringResource(R.string.sign_up_greeting),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.sign_up_greeting_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.padding(24.dp))
        SocialMediaGroup(
            textFormat = stringResource(R.string.sign_up_with),
            socialMediaOptions = listOf(
                SocialMediaOption.Google,
                SocialMediaOption.Apple,
                SocialMediaOption.Facebook,
            ),
            onSocialMediaClick = {
                // todo: improve, maybe not use domain model here
                onSignUp(LoginOption.SocialMedia(it))
            },
        )
        Spacer(modifier = Modifier.padding(12.dp))
        SeparatorWithText(text = stringResource(R.string.or))
        Spacer(modifier = Modifier.padding(12.dp))
        PasswordBasedLoginComponent(
            onSubmit = {
                // todo: improve, maybe not use domain model here
                onSignUp(LoginOption.Account(it.email, it.password))
            },
            buttonText = stringResource(R.string.sign_up),
        )
        Spacer(modifier = Modifier.weight(1f))
        LoginFooterMessage(
            message = "Already have an account?",
            link = "Sign in",
            onLinkClick = onAlreadyHaveAccount,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        InternalScreen(
            onAlreadyHaveAccount = {},
            onSignUp = {},
        )
    }
}