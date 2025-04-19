package com.chrispassold.askbuddy.ui.components.avatars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.rememberAsyncImagePainter
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.ui.extensions.PreviewUiModes
import com.chrispassold.askbuddy.ui.theme.AppTheme

enum class AvatarSize(val size: Dp) {
    Small(32.dp), Medium(48.dp), Large(96.dp)
}

sealed class AvatarImage {
    data class Drawable(@DrawableRes val id: Int) : AvatarImage()
    data class Uri(val uri: android.net.Uri) : AvatarImage()
    data class Icon(val icon: ImageVector) : AvatarImage()
}

@Composable
fun Avatar(
    image: AvatarImage,
    avatarSize: AvatarSize,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val avatarModifier =
        modifier
            .size(avatarSize.size)
            .clip(CircleShape)
            .border(1.dp, Color.LightGray, CircleShape)

    Box(
        modifier = avatarModifier,
        contentAlignment = Alignment.Center,
    ) {
        when (image) {
            is AvatarImage.Drawable -> {
                Image(
                    painter = painterResource(id = image.id),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(color),
                )
            }

            is AvatarImage.Uri -> {
                Image(
                    painter = rememberAsyncImagePainter(image.uri),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                )
            }

            is AvatarImage.Icon -> {
                Icon(
                    imageVector = image.icon,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    tint = color,
                )
            }
        }
    }
}

private class AvatarSizesParameters : PreviewParameterProvider<AvatarSize> {
    override val values: Sequence<AvatarSize>
        get() = AvatarSize.entries.asSequence()
}

@PreviewUiModes
@Composable
private fun AvatarIconPreview(
    @PreviewParameter(AvatarSizesParameters::class) size: AvatarSize,
) {
    AppTheme {
        Avatar(
            image = AvatarImage.Icon(icon = Icons.Filled.Person),
            avatarSize = size,
        )
    }
}

@PreviewUiModes
@Composable
private fun AvatarImagePreview(
    @PreviewParameter(AvatarSizesParameters::class) size: AvatarSize,
) {
    AppTheme {
        Avatar(
            image = AvatarImage.Uri(uri = "https://www.transparentpng.com/thumb/aang/eZK3Lb-mundonick-avatar-aang.png".toUri()),
            avatarSize = size,
        )
    }
}

@PreviewUiModes
@Composable
private fun AvatarDrawablePreview(
    @PreviewParameter(AvatarSizesParameters::class) size: AvatarSize,
) {
    AppTheme {
        Avatar(
            image = AvatarImage.Drawable(id = R.drawable.ic_google_login),
            avatarSize = size,
        )
    }
}
