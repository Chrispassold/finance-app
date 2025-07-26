package com.chrispassold.presentation.features.profile.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.TransactionType
import com.chrispassold.presentation.R
import com.chrispassold.presentation.components.avatars.Avatar
import com.chrispassold.presentation.components.avatars.AvatarImage
import com.chrispassold.presentation.components.avatars.AvatarSize
import com.chrispassold.presentation.components.buttons.PrimaryButton
import com.chrispassold.presentation.components.containers.AppScaffold
import com.chrispassold.presentation.components.inputs.TextInput
import com.chrispassold.presentation.components.progress.FullScreenCircularIndicator
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.formatters.IconTintFormatter
import com.chrispassold.presentation.formatters.IconTypeFormatter
import com.chrispassold.presentation.formatters.TransactionTypeFormatter
import com.chrispassold.presentation.theme.AppThemePreview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private typealias OnEvent = (DetailCategoryUiEvent) -> Unit

@Composable
fun DetailCategoryScreen(
    state: DetailCategoryUiState,
    onEvent: OnEvent,
    effect: DetailCategoryUiEffect,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {
        when (effect) {
            is DetailCategoryUiEffect.ShowSnackBar -> snackbarHostState.showSnackbar(effect.message)
            else -> Unit
        }
    }

    AppScaffold(
        snackbarHostState = snackbarHostState,
        appBarTitle = stringResource(R.string.category_detail_screen_title),
        onBack = {
            onEvent(DetailCategoryUiEvent.OnBack)
        },
    ) {
        if (state.isLoading) {
            FullScreenCircularIndicator()
        } else {
            DetailComponent(
                state = state,
                onEvent = onEvent,
                effect = effect,
            )
        }
    }
}

@Composable
private fun DetailComponent(
    state: DetailCategoryUiState,
    onEvent: OnEvent,
    effect: DetailCategoryUiEffect,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(effect) {
        when (effect) {
            is DetailCategoryUiEffect.ShowSubCategoryModal -> showBottomSheet = true
            DetailCategoryUiEffect.HideSubCategoryModal -> showBottomSheet = false
            else -> Unit
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Avatar(
            image = AvatarImage.Icon(icon = IconTypeFormatter.format(state.image)),
            avatarSize = AvatarSize.Large,
            color = IconTintFormatter.format(state.color),
        )
        TextInput(
            label = stringResource(R.string.category_name_placeholder),
            value = state.categoryName,
            onValueChange = {
                onEvent(DetailCategoryUiEvent.CategoryNameChanged(it))
            },
        )
        CategoryTypeSelector(
            selectedType = state.type,
            onTypeSelected = {
                onEvent(DetailCategoryUiEvent.TypeChanged(it))
            },
        )
        SubCategories(subCategories = state.subCategories, onAdd = {
            onEvent(DetailCategoryUiEvent.ShowSubCategoryModal(null))
        }, onRemove = {
            onEvent(DetailCategoryUiEvent.SubCategoryRemove(it))
        }, onEdit = {
            onEvent(DetailCategoryUiEvent.ShowSubCategoryModal(it))
        })
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.save),
            onClick = {
                onEvent(DetailCategoryUiEvent.Submit)
            },
        )
    }
    if (showBottomSheet) {
        SubCategoryModal(
            subCategory = (effect as? DetailCategoryUiEffect.ShowSubCategoryModal)?.subCategory,
            onEvent = onEvent,
            onClose = {
                onEvent(DetailCategoryUiEvent.HideSubCategoryModal)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubCategoryModal(
    subCategory: DetailCategoryUiState.SubCategory?,
    onEvent: OnEvent,
    onClose: () -> Unit,
) {
    val sheetState: SheetState = rememberModalBottomSheetState()
    val scope: CoroutineScope = rememberCoroutineScope()
    var subCategoryName by remember { mutableStateOf(subCategory?.name ?: "") }
    ModalBottomSheet(
        onDismissRequest = onClose,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                stringResource(R.string.modal_title_new_sub_category),
                style = MaterialTheme.typography.titleLarge,
            )
            TextInput(
                label = stringResource(R.string.category_name),
                value = subCategoryName,
                onValueChange = { subCategoryName = it },
                modifier = Modifier.fillMaxWidth(),
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.add),
                enabled = subCategoryName.isNotBlank(),
                onClick = {
                    if (subCategoryName.isNotBlank()) {
                        onEvent(
                            DetailCategoryUiEvent.SubCategoryChange(
                                subCategory?.id, subCategoryName
                            )
                        )
                    }
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onClose()
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun SubCategories(
    subCategories: List<DetailCategoryUiState.SubCategory>,
    onAdd: () -> Unit,
    onRemove: (DetailCategoryUiState.SubCategory) -> Unit,
    onEdit: (DetailCategoryUiState.SubCategory) -> Unit
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
                onClick = onAdd,
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
            data = subCategories, onRemove = onRemove, onEdit = onEdit
        )
    }
}

@Composable
private fun SubCategoriesList(
    data: List<DetailCategoryUiState.SubCategory>,
    onRemove: (DetailCategoryUiState.SubCategory) -> Unit,
    onEdit: (DetailCategoryUiState.SubCategory) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(count = data.size, key = { index -> data[index].name }) {
            ListItem(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .clickable {
                        onEdit(data[it])
                    },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                ),
                headlineContent = {
                    Text(text = data[it].name, style = MaterialTheme.typography.bodyMedium)
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
    selectedType: TransactionType,
    onTypeSelected: (TransactionType) -> Unit,
) {
    val options by remember {
        mutableStateOf(
            listOf(
                TransactionType.EXPENSE,
                TransactionType.INCOME,
            ),
        )
    }

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
                        text = TransactionTypeFormatter.format(type),
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
            state = DetailCategoryUiState(),
            onEvent = {},
            effect = DetailCategoryUiEffect.Idle,
        )
    }
}
