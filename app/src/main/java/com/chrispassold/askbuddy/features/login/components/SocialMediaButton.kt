package com.chrispassold.askbuddy.features.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.ui.extensions.LocalUiMode
import com.chrispassold.askbuddy.ui.extensions.PreviewDarkMode
import com.chrispassold.askbuddy.ui.extensions.PreviewLightMode
import com.chrispassold.askbuddy.ui.extensions.choose
import com.chrispassold.askbuddy.ui.theme.AppTheme

// todo: turn this into private and use some domain model to represent the social media
enum class SocialMedia(
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
    )
}

typealias OnSocialMediaButtonClick = (SocialMedia) -> Unit

@Composable
fun SocialMediaButton(
    socialMedia: SocialMedia,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    onClick: OnSocialMediaButtonClick,
) {
    Button(
        onClick = { onClick(socialMedia) },
        modifier = modifier,
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
            if (showLabel) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.sign_in_with, socialMedia.label),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

private class SocialMediaParameters : PreviewParameterProvider<SocialMedia> {
    override val values: Sequence<SocialMedia>
        get() = SocialMedia.entries.asSequence()
}

@PreviewLightMode
@PreviewDarkMode
@Composable
private fun PreviewShowingLabel(
    @PreviewParameter(SocialMediaParameters::class) socialMedia: SocialMedia,
) {
    AppTheme {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            SocialMediaButton(
                socialMedia = socialMedia,
                showLabel = true,
                onClick = {},
            )
        }
    }
}

@PreviewLightMode
@PreviewDarkMode
@Composable
private fun PreviewHiddingLabel(
    @PreviewParameter(SocialMediaParameters::class) socialMedia: SocialMedia,
) {
    AppTheme {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            SocialMediaButton(
                socialMedia = socialMedia,
                showLabel = false,
                onClick = {},
            )
        }
    }
}