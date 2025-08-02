package com.chrispassold.presentation.features.profile.personalinformation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.chrispassold.presentation.R
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.extensions.whenPreview
import com.chrispassold.presentation.components.containers.CircularImage
import com.chrispassold.presentation.components.containers.ImageVariant
import com.chrispassold.presentation.components.containers.ImageSize
import com.chrispassold.presentation.components.buttons.PrimaryButton
import com.chrispassold.presentation.components.containers.AppScaffold
import com.chrispassold.presentation.components.inputs.DatePickerInput
import com.chrispassold.presentation.components.inputs.PasswordInput
import com.chrispassold.presentation.components.inputs.TextInput
import com.chrispassold.presentation.components.texts.TextLink
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun PersonalInformationScreen(
    onBack: () -> Unit,
) {
    AppScaffold(
        appBarTitle = stringResource(R.string.personal_information_screen_title),
        onBack = onBack,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Header()
        Spacer(modifier = Modifier.height(24.dp))
        Content()
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.save),
            onClick = onBack,
        )
    }
}

@Composable
private fun Header() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Row {
            CircularImage(
                variant = whenPreview(
                    ifPreview = { ImageVariant.Drawable(id = com.chrispassold.presentation.R.drawable.ic_google_login) },
                    ifNotPreview = { ImageVariant.Uri("https://picsum.photos/50/50".toUri()) },
                ),
                imageSize = ImageSize.Large,
            )
        }
        Row {
            TextLink(text = stringResource(R.string.edit_picture), onClick = {})
        }
    }
}

@Composable
private fun Content() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableLongStateOf(0L) }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextInput(
            label = stringResource(R.string.name),
            value = name,
            onValueChange = { name = it },
        )
        TextInput(
            label = stringResource(R.string.e_mail),
            value = email,
            onValueChange = { email = it },
        )
        DatePickerInput(
            label = stringResource(R.string.date_of_birth),
            onDateSelected = {
                birthDate = it
            },
            onDismissRequest = {
                // Handle dismiss request if needed
            },
        )
        PasswordInput(
            label = stringResource(R.string.password),
            value = password,
            onValueChange = { password = it },
        )
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        PersonalInformationScreen(
            onBack = {},
        )
    }
}
