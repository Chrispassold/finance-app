package com.chrispassold.presentation.components.containers

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.rememberAsyncImagePainter
import com.chrispassold.presentation.R
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.theme.AppTheme

enum class ImageSize(val size: Dp) {
    Small(32.dp), Medium(48.dp), Large(96.dp)
}

sealed class ImageVariant {
    data class Icon(val icon: ImageVector) : ImageVariant()
    data class Drawable(@DrawableRes val id: Int) : ImageVariant()
    data class Uri(val uri: android.net.Uri) : ImageVariant()
}

@Composable
fun CircularImage(
    variant: ImageVariant,
    imageSize: ImageSize,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val avatarModifier = modifier
        .clip(CircleShape)
        .size(imageSize.size)
        .background(color = MaterialTheme.colorScheme.surface)

    Box(
        modifier = avatarModifier,
        contentAlignment = Alignment.Center,
    ) {
        when (variant) {
            is ImageVariant.Icon -> {
                Icon(
                    imageVector = variant.icon,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    tint = color,
                )
            }
            is ImageVariant.Drawable -> {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(id = variant.id),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(color),
                    )
                } else {
                    TODO("Not supported yet")
                }
            }

            is ImageVariant.Uri -> {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = rememberAsyncImagePainter(variant.uri),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    // TODO("Not supported yet")
                }
            }
        }
    }
}

private class ImagesSizesParameters : PreviewParameterProvider<ImageSize> {
    override val values: Sequence<ImageSize>
        get() = ImageSize.entries.asSequence()
}

@PreviewUiModes
@Composable
private fun ImageIconPreview(
    @PreviewParameter(ImagesSizesParameters::class) size: ImageSize,
) {
    AppTheme {
        CircularImage(
            variant = ImageVariant.Icon(icon = Icons.Filled.Person),
            imageSize = size,
        )
    }
}

@PreviewUiModes
@Composable
private fun ImageUriPreview(
    @PreviewParameter(ImagesSizesParameters::class) size: ImageSize,
) {
    AppTheme {
        CircularImage(
            variant = ImageVariant.Uri(uri = "https://www.transparentpng.com/thumb/aang/eZK3Lb-mundonick-avatar-aang.png".toUri()),
            imageSize = size,
        )
    }
}

@PreviewUiModes
@Composable
private fun ImageDrawablePreview(
    @PreviewParameter(ImagesSizesParameters::class) size: ImageSize,
) {
    AppTheme {
        CircularImage(
            variant = ImageVariant.Drawable(id = R.drawable.ic_google_login),
            imageSize = size,
        )
    }
}
