package com.chrispassold.presentation.features.profile.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.Category
import com.chrispassold.presentation.R
import com.chrispassold.presentation.components.containers.AppScaffold
import com.chrispassold.presentation.components.progress.FullScreenCircularIndicator
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.features.profile.categories.components.CategoryWidget
import com.chrispassold.presentation.features.profile.categories.components.ExpensesIncomesTabs
import com.chrispassold.presentation.formatters.IconTypeFormatter
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun ListCategoriesScreen(
    state: ListCategoriesUiState,
    onEvent: (ListCategoriesUiEvent) -> Unit,
    effect: ListCategoriesUiEffect?,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(effect) {
        when (effect) {
            is ListCategoriesUiEffect.ShowSnackBar -> {
                snackbarHostState.showSnackbar(effect.message)
            }

            else -> Unit
        }
    }

    AppScaffold(
        snackbarHostState = snackbarHostState,
        appBarTitle = stringResource(R.string.categories),
        onBack = {
            onEvent(ListCategoriesUiEvent.OnBackClicked)
        },
        rightActionIcon = Icons.Filled.Add,
        rightActionIconContentDescription = "Add",
        onRightAction = {
            onEvent(ListCategoriesUiEvent.OnNewClicked)
        },
    ) {
        if (state.isLoading) {
            FullScreenCircularIndicator()
        } else {
            Spacer(modifier = Modifier.height(16.dp))
            ExpensesIncomesTabs(
                expensesContent = {
                    CategoryItemsContent(
                        items = state.expenses,
                        onClick = {
                            onEvent(ListCategoriesUiEvent.OnItemClicked(it))
                        },
                    )
                },
                incomesContent = {
                    CategoryItemsContent(
                        items = state.incomes,
                        onClick = {
                            onEvent(ListCategoriesUiEvent.OnItemClicked(it))
                        },
                    )
                },
            )
        }

    }
}

@Composable
private fun CategoryItemsContent(
    items: List<Category>,
    onClick: (Category) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { category ->
                val img = IconTypeFormatter.format(category.image)
                CategoryWidget(
                    icon = img,
                    subCategoriesCount = category.subCategories.size,
                    name = category.name,
                    onClick = { onClick(category) },
                )
            }
        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ListCategoriesScreen(
            state = ListCategoriesUiState(isLoading = false),
            onEvent = {},
            effect = null,
        )
    }
}

@PreviewUiModes
@Composable
private fun PreviewLoading() {
    AppTheme {
        ListCategoriesScreen(
            state = ListCategoriesUiState(isLoading = true),
            onEvent = {},
            effect = null,
        )
    }
}
