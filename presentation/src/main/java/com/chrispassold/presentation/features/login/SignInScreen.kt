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
import com.chrispassold.presentation.components.containers.AppScaffold
import com.chrispassold.presentation.components.separators.SeparatorWithText
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.features.login.components.LoginFooterMessage
import com.chrispassold.presentation.features.login.components.PasswordBasedLoginComponent
import com.chrispassold.presentation.features.login.components.SocialMediaGroup
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    navigateToSignUp: () -> Unit,
    navigateToForgetPassword: () -> Unit,
    navigateToHome: () -> Unit,
) {
    InternalScreen(
        navigateToSignUp = navigateToSignUp,
        navigateToForgetPassword = navigateToForgetPassword,
        onSubmit = {
            viewModel.signIn(it) {
                navigateToHome()
            }
        },
    )
}

@Composable
private fun InternalScreen(
    navigateToSignUp: () -> Unit,
    navigateToForgetPassword: () -> Unit,
    onSubmit: (LoginOption) -> Unit,
) {
    AppScaffold {
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
            socialMediaOptions = listOf(
                SocialMediaOption.Google,
                SocialMediaOption.Apple,
                SocialMediaOption.Facebook,
            ),
            onSocialMediaClick = {
                // todo: improve, maybe not use domain model here
                onSubmit(LoginOption.SocialMedia(it))
            },
        )
        Spacer(modifier = Modifier.padding(12.dp))
        SeparatorWithText(text = stringResource(R.string.or))
        Spacer(modifier = Modifier.padding(12.dp))
        PasswordBasedLoginComponent(
            onForgetPassword = navigateToForgetPassword,
            onSubmit = {
                // todo: improve, maybe not use domain model here
                onSubmit(LoginOption.Account(it.email, it.password))
            },
            buttonText = stringResource(R.string.sign_in),
        )
        Spacer(modifier = Modifier.weight(1f))
        LoginFooterMessage(
            message = stringResource(R.string.do_not_have_an_account),
            link = stringResource(R.string.sign_up),
            onLinkClick = navigateToSignUp,
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        InternalScreen(
            navigateToSignUp = {},
            navigateToForgetPassword = {},
            onSubmit = {},
        )
    }
}
