package com.chrispassold.presentation.components.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chrispassold.domain.models.IconType
import com.chrispassold.presentation.R
import com.chrispassold.presentation.extensions.PreviewUiModes
import com.chrispassold.presentation.formatters.IconTypeFormatter
import com.chrispassold.presentation.theme.AppThemePreview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconSelector(
    selectedIcon: IconType?, onIconSelected: (IconType) -> Unit, modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val avaiableIcons by remember { mutableStateOf(IconTypeFormatter.icons.keys.toList()) }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.small)
                .clickable { showBottomSheet = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (selectedIcon != null) {
                    Icon(
                        imageVector = IconTypeFormatter.format(selectedIcon),
                        contentDescription = "Selected icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Text("Selecione um ícone", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Open the icon chooser",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
        ) {
            IconSelectionSheetContent(
                availableIcons = avaiableIcons,
                onIconSelected = onIconSelected,
                onClose = { showBottomSheet = false }, // Atualiza o estado para fechar
                sheetState = sheetState,
                scope = scope
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IconSelectionSheetContent(
    availableIcons: List<IconType>,
    onIconSelected: (IconType) -> Unit,
    onClose: () -> Unit,
    sheetState: SheetState,
    scope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            stringResource(R.string.choose_an_icon),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 64.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(availableIcons) { iconType ->
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clickable {
                            onIconSelected(iconType)
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onClose() // Chama o onClose original
                                }
                            }
                        }
                        .padding(8.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = IconTypeFormatter.format(iconType),
                        contentDescription = iconType.name,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onClose() // Chama o onClose original
                    }
                }
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.close))
        }
    }
}

@PreviewUiModes
@Composable
private fun IconSelectorNullPreview() {
    var selected by remember { mutableStateOf<IconType?>(null) }
    AppThemePreview {
        Column(modifier = Modifier.padding(16.dp)) {
            IconSelector(
                selectedIcon = selected,
                onIconSelected = { selected = it },
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (selected != null) {
                Text(
                    text = "Ícone selecionado: ${selected?.name}",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@PreviewUiModes
@Composable
private fun IconSelectorSelectedPreview() {
    var selected by remember { mutableStateOf<IconType?>(IconType.ShoppingCart) }
    AppThemePreview {
        Column(modifier = Modifier.padding(16.dp)) {
            IconSelector(
                selectedIcon = selected,
                onIconSelected = { selected = it },
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (selected != null) {
                Text(
                    text = "Ícone selecionado: ${selected?.name}",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewUiModes
@Composable
private fun IconSelectionSheetContentPreview() {
    val sampleIcons = IconTypeFormatter.icons.keys.toList().take(10)
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded
    )
    val scope = rememberCoroutineScope()

    AppThemePreview {
        ModalBottomSheet(
            onDismissRequest = { /* Não faz nada no preview */ },
            sheetState = sheetState,
        ) {
            IconSelectionSheetContent(
                availableIcons = sampleIcons,
                onIconSelected = { /* Não faz nada no preview */ },
                onClose = { /* Não faz nada no preview */ },
                sheetState = sheetState,
                scope = scope
            )
        }
    }
}
