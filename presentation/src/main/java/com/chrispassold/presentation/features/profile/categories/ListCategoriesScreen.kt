package com.chrispassold.presentation.features.profile.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.presentation.R
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.components.containers.ScreenContainer
import com.chrispassold.presentation.features.profile.categories.components.CategoryWidget
import com.chrispassold.presentation.features.profile.categories.components.ExpensesIncomesTabs
import com.chrispassold.presentation.theme.AppTheme

@Composable
fun ListCategoriesScreen(
    onNewCategory: () -> Unit,
    onBack: () -> Unit,
) {
    ScreenContainer(
        appBarTitle = stringResource(R.string.categories),
        onBack = onBack,
        rightActionIcon = Icons.Filled.Add,
        rightActionIconContentDescription = "Add",
        onRightAction = onNewCategory,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ExpensesIncomesTabs(
            expensesContent = {
                ExpensesContent(
                    onClickExpense = {},
                )
            },
            incomesContent = {
                IncomesContent(
                    onClickIncome = {},
                )
            },
        )

    }
}

@Composable
private fun ExpensesContent(
    onClickExpense: (name: String) -> Unit,
) {
    val onClick: (name: String) -> Unit = { name ->
        onClickExpense(name)
    }
    Column(modifier = Modifier.padding(16.dp)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CategoryWidget(
                icon = Icons.Filled.House,
                iconContentDescription = "House",
                subCategoriesCount = 5,
                name = "House",
                onClick = onClick,
            )
            CategoryWidget(
                icon = Icons.Filled.Work,
                iconContentDescription = "Work",
                subCategoriesCount = 1,
                name = "Work",
                onClick = onClick,
            )
            CategoryWidget(
                icon = Icons.Filled.School,
                iconContentDescription = "Education",
                subCategoriesCount = 0,
                name = "Education",
                onClick = onClick,
            )
            CategoryWidget(
                icon = Icons.Filled.ShoppingCart,
                iconContentDescription = "Groceries",
                subCategoriesCount = 3,
                name = "Groceries",
                onClick = onClick,
            )
            CategoryWidget(
                icon = Icons.Filled.DirectionsCar,
                iconContentDescription = "Car",
                subCategoriesCount = 0,
                name = "Car",
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun IncomesContent(
    onClickIncome: (name: String) -> Unit,
) {
    val onClick: (name: String) -> Unit = { name ->
        onClickIncome(name)
    }
    Column(modifier = Modifier.padding(16.dp)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CategoryWidget(
                icon = Icons.Filled.Money,
                iconContentDescription = "Salary",
                subCategoriesCount = 0,
                name = "Salary",
                onClick = onClick,
            )
            CategoryWidget(
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                iconContentDescription = "Investments",
                subCategoriesCount = 1,
                name = "Investments",
                onClick = onClick,
            )
            CategoryWidget(
                icon = Icons.Filled.AttachMoney,
                iconContentDescription = "Sales",
                subCategoriesCount = 5,
                name = "Sales",
                onClick = onClick,
            )
        }
    }
}


@PreviewUiModes
@Composable
private fun Preview() {
    AppTheme {
        ListCategoriesScreen(
            onNewCategory = {},
            onBack = {},
        )
    }
}