package com.chrispassold.presentation.features.profile.categories

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.R
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.components.avatars.Avatar
import com.chrispassold.presentation.components.avatars.AvatarImage
import com.chrispassold.presentation.components.avatars.AvatarSize
import com.chrispassold.presentation.components.buttons.PrimaryButton
import com.chrispassold.presentation.components.containers.ScreenContainer
import com.chrispassold.presentation.components.inputs.TextInput
import com.chrispassold.presentation.theme.AppThemePreview
import java.util.UUID

enum class CategoryType(
    @StringRes val title: Int,
) {
    INCOME(title = R.string.category_type_name_income), EXPENSE(title = R.string.category_type_name_expense),
}

@Composable
fun DetailCategoryScreen(
    onBack: () -> Unit,
) {
    var categoryName by remember { mutableStateOf("") }
    var categoryType by remember { mutableStateOf(CategoryType.EXPENSE) }
    val subCategories = remember { mutableStateListOf<String>("teste") }

    ScreenContainer(
        appBarTitle = stringResource(R.string.category_detail_screen_title),
        onBack = onBack,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Avatar(
                image = AvatarImage.Icon(icon = Icons.Filled.Home),
                avatarSize = AvatarSize.Large,
            )
            TextInput(
                label = stringResource(R.string.category_name_placeholder),
                value = categoryName,
                onValueChange = { categoryName = it },
            )
            CategoryTypeSelector(
                selectedType = categoryType,
                onTypeSelected = {
                    categoryType = it
                },
            )
            SubCategories(
                subCategories = subCategories,
                onAdd = {
                    subCategories.add(UUID.randomUUID().toString())
                },
                onRemove = {
                    subCategories.remove(it)
                },
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.save),
                onClick = onBack,
            )
        }
    }
}

@Composable
private fun SubCategories(
    subCategories: List<String>,
    onAdd: () -> Unit,
    onRemove: (String) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                stringResource(R.string.add_sub_category),
                style = MaterialTheme.typography.bodyMedium,
            )
            FilledTonalIconButton(
                onClick = { onAdd() },
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SubCategoriesList(
            data = subCategories,
            onRemove = onRemove,
        )
    }
}

@Composable
private fun SubCategoriesList(
    data: List<String>,
    onRemove: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(count = data.size, key = { index -> data[index] }) {
            ListItem(
                modifier = Modifier.clip(MaterialTheme.shapes.small),
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                ),
                headlineContent = {
                    Text(text = data[it], style = MaterialTheme.typography.bodyMedium)
                },
                trailingContent = {
                    FilledTonalIconButton(
                        onClick = { onRemove(data[it]) },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Remove,
                            contentDescription = null,
                        )

                    }
                },
            )
        }

    }
}

@Composable
private fun CategoryTypeSelector(
    selectedType: CategoryType,
    onTypeSelected: (CategoryType) -> Unit,
) {
    val options by remember { mutableStateOf(listOf(CategoryType.EXPENSE, CategoryType.INCOME)) }

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        options.forEachIndexed { index, type ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size,
                ),
                onClick = { onTypeSelected(type) },
                selected = type == selectedType,
                label = {
                    Text(
                        text = stringResource(type.title),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
            )
        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppThemePreview {
        DetailCategoryScreen(
            onBack = {},
        )
    }
}