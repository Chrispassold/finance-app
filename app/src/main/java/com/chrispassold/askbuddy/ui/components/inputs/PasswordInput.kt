package com.chrispassold.askbuddy.ui.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern


@Composable
fun PasswordInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Enter your password",
    errorText: String = "Password not valid",
) {
    // State variables to manage password visibility and validity
    var showPassword by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(true) }

    // TextField for entering user password
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            isPasswordError = it.isValidPassword()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            // Password visibility toggle icon
            PasswordVisibilityToggleIcon(
                showPassword = showPassword,
                onTogglePasswordVisibility = { showPassword = !showPassword },
            )
        },
        isError = !isPasswordError,
        supportingText = {
            // Display error text if the password is not valid
            if (!isPasswordError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        label = { Text(label, style = MaterialTheme.typography.labelLarge) },
        modifier = modifier,
    )
}

@Composable
private fun PasswordVisibilityToggleIcon(
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
) {
    // Determine the icon based on password visibility
    val image = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    val contentDescription = if (showPassword) "Hide password icon" else "Show password icon"

    // IconButton to toggle password visibility
    IconButton(onClick = onTogglePasswordVisibility) {
        Icon(imageVector = image, contentDescription = contentDescription)
    }
}

/**
 * Checks if the password is valid based on the following rules:
 * - Minimum length of 8 characters.
 * - At least one lowercase letter.
 * - At least one uppercase letter.
 * - At least one numeric digit.
 * - At least one special character within the set: @$!%*?&.
 * - Only the following characters are allowed: uppercase and lowercase letters, numbers, and the special characters @$!%*?&.
 *
 * Examples of valid passwords:
 * - SenhaForte123!
 * - Mudar@123
 * - OutraSenha$9
 * - Complex0*Pass
 * - Super123@Senha
 *
 * Examples of invalid passwords:
 * - senha (missing uppercase, digit, and special character)
 * - SENHA (missing lowercase, digit, and special character)
 * - senha123 (missing uppercase and special character)
 * - Senha (missing digit, special character and less than 8 characters)
 * - Senha! (missing digit and less than 8 characters)
 * - Se1! (less than 8 characters)
 * - Senha123! (less than 8 characters)
 * - senha123! (missing uppercase, and less than 8 characters)
 * - SenhaMuitoForteç1! (contains an invalid character: ç)
 *
 * @return `true` if the password is valid, `false` otherwise.
 */
private fun TextFieldValue.isValidPassword(): Boolean {
    val password = text
    val passwordRegex =
        Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")

    return password.matches((passwordRegex).toRegex())
}

@Preview(showBackground = true)
@Composable
private fun PasswordInputPreview() {
    var password by remember { mutableStateOf(TextFieldValue()) }
    Column(modifier = Modifier.padding(16.dp)) {
        PasswordInput(
            value = password,
            onValueChange = {
                password = it
            },
        )
    }
}