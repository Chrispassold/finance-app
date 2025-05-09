package com.chrispassold.askbuddy.presentation.features.profile.categories.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chrispassold.askbuddy.R
import com.chrispassold.askbuddy.extensions.PreviewUiModes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesIncomesTabs(
    expensesContent: @Composable () -> Unit,
    incomesContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.tab_title_expenses),
        stringResource(R.string.tab_title_incomes),
    )

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                )
            }
        }

        when (selectedTabIndex) {
            0 -> expensesContent()
            1 -> incomesContent()
        }
    }
}

@PreviewUiModes
@Composable
private fun Preview() {
    ExpensesIncomesTabs(
        expensesContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Content for Expenses Tab",
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        incomesContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Content for Incomes Tab",
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
    )
}