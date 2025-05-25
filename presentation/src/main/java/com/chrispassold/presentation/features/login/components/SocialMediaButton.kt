package com.chrispassold.presentation.features.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.SocialMediaOption
import com.chrispassold.presentation.R
import com.chrispassold.presentation.extensions.LocalUiMode
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.extensions.choose
import com.chrispassold.presentation.extensions.ifTrue
import com.chrispassold.presentation.theme.AppTheme

private enum class SocialMedia(
    val imageLight: Int,
    val imageDark: Int,
    val label: String,
) {
    Google(
        imageLight = R.drawable.ic_google_login,
        imageDark = R.drawable.ic_google_login,
        label = "Google",
    ),
    Apple(
        imageLight = R.drawable.ic_apple_login_light,
        imageDark = R.drawable.ic_apple_login_dark,
        label = "Apple",
    ),
    Facebook(
        imageLight = R.drawable.ic_facebook_login,
        imageDark = R.drawable.ic_facebook_login,
        label = "Facebook",
    );

    companion object {
        fun fromSocialMediaOptions(socialMediaOption: SocialMediaOption): SocialMedia =
            when (socialMediaOption) {
                SocialMediaOption.Apple -> Apple
                SocialMediaOption.Facebook -> Facebook
                SocialMediaOption.Google -> Google
            }
    }
}

private typealias OnSocialMediaButtonClick = (SocialMediaOption) -> Unit

@Composable
fun SocialMediaGroup(
    textFormat: String,
    socialMediaOptions: List<SocialMediaOption>,
    onSocialMediaClick: OnSocialMediaButtonClick,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        socialMediaOptions.forEach {
            SocialMediaButton(
                textFormat = textFormat,
                socialMediaOption = it,
                onClick = onSocialMediaClick,
            )
        }
    }
}

@Composable
private fun SocialMediaButton(
    socialMediaOption: SocialMediaOption,
    modifier: Modifier = Modifier,
    onClick: OnSocialMediaButtonClick,
    textFormat: String? = null,
    showLabel: Boolean = true,
) {
    val socialMedia = SocialMedia.fromSocialMediaOptions(socialMediaOption)
    Button(
        onClick = { onClick(socialMediaOption) },
        modifier = modifier.ifTrue(showLabel) { fillMaxWidth() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant, // Example background color
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant, // Example content color
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp,
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(
                    id = LocalUiMode.current.choose(
                        onDarkMode = { socialMedia.imageDark },
                        onLightMode = { socialMedia.imageLight },
                    ),
                ),
                contentDescription = socialMedia.label,
                modifier = Modifier.size(24.dp),
            )
            if (showLabel && textFormat?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = textFormat.format(socialMedia.label),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

private class SocialMediaOptionsParameters : PreviewParameterProvider<SocialMediaOption> {
    override val values: Sequence<SocialMediaOption>
        get() = sequenceOf(
            SocialMediaOption.Google,
            SocialMediaOption.Apple,
            SocialMediaOption.Facebook,
        )
}

@PreviewUiModes
@Composable
private fun PreviewShowingLabel(
    @PreviewParameter(SocialMediaOptionsParameters::class) socialMediaOption: SocialMediaOption,
) {
    AppTheme {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            SocialMediaButton(
                socialMediaOption = socialMediaOption,
                showLabel = true,
                onClick = {},
                textFormat = stringResource(R.string.sign_in_with),
            )
        }
    }
}

@PreviewUiModes
@Composable
private fun PreviewHiddingLabel(
    @PreviewParameter(SocialMediaOptionsParameters::class) socialMediaOption: SocialMediaOption,
) {
    AppTheme {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            SocialMediaButton(
                socialMediaOption = socialMediaOption,
                showLabel = false,
                onClick = {},
                textFormat = null,
            )
        }
    }
}