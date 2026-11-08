package com.example.calculatoroftrainingcoefficients.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatoroftrainingcoefficients.R
import com.example.calculatoroftrainingcoefficients.core.*
import com.example.calculatoroftrainingcoefficients.ui.theme.JetBrainsMonoFont
import kotlinx.coroutines.launch

private val presetWeights = listOf(0.1, 1.0, 1.3, 1.5, 2.0)

@Composable
fun Main(toSupport: () -> Unit, viewModel: MainViewModel = viewModel()) {
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    fun deleted(message: Int, action: () -> Unit) {
        snackbar.currentSnackbarData?.dismiss()
        action()
        scope.launch {
            if (snackbar.showSnackbar(context.getString(message), context.getString(R.string.undo),
                    duration = SnackbarDuration.Long) == SnackbarResult.ActionPerformed) {
                if (!viewModel.undoDelete()) snackbar.showSnackbar(context.getString(R.string.undo_limit, MAX_ENTRIES))
            }
        }
    }
    CalculatorScreen(
        entries = viewModel.numbersList,
        selectedWeight = viewModel.selectedWeight,
        average = viewModel.result,
        snackbar = snackbar,
        onSupport = toSupport,
        onWeight = viewModel::selectWeight,
        onAdd = { grade ->
            if (!viewModel.addElement(grade)) scope.launch {
                snackbar.showSnackbar(context.getString(R.string.limit_reached, MAX_ENTRIES))
            }
        },
        onDelete = { id -> deleted(R.string.deleted) { viewModel.deleteElement(id) } },
        onClear = { deleted(R.string.cleared) { viewModel.clear() } },
        onEdit = viewModel::editElement
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    entries: List<Coefficient>, selectedWeight: Double, average: Double?,
    snackbar: SnackbarHostState, onSupport: () -> Unit, onWeight: (Double) -> Unit,
    onAdd: (Int) -> Unit, onDelete: (String) -> Unit, onClear: () -> Unit,
    onEdit: (String, Int, Double) -> Unit
) {
    var customWeight by rememberSaveable { mutableStateOf(false) }
    var clearConfirmation by rememberSaveable { mutableStateOf(false) }
    var editingId by rememberSaveable { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.average_title), maxLines = 1, overflow = TextOverflow.Ellipsis) },
                actions = {
                    IconButton(onClick = onSupport, modifier = Modifier.testTag("support")) {
                        Icon(Icons.Outlined.Info, stringResource(R.string.about))
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbar) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).consumeWindowInsets(padding).testTag("calculator_list"),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(stringResource(R.string.result_label), style = MaterialTheme.typography.labelLarge)
                        BoxWithConstraints(Modifier.fillMaxWidth()) {
                            val text = formatAverage(average)
                            val size = minOf(57f, maxWidth.value / (text.length * 0.65f * LocalDensity.current.fontScale)).sp
                            Text(text, fontFamily = JetBrainsMonoFont, fontSize = size,
                                lineHeight = (size.value * 1.15f).sp, maxLines = 1, modifier = Modifier.testTag("average"))
                        }
                        Text(if (entries.isEmpty()) stringResource(R.string.empty_hint)
                            else stringResource(R.string.entry_count, entries.size), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(R.string.weight_title), style = MaterialTheme.typography.titleMedium)
                    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        presetWeights.forEach { weight ->
                            FilterChip(selected = selectedWeight == weight, onClick = { onWeight(weight) },
                                label = { Text("×${formatWeight(weight)}") }, modifier = Modifier.testTag("weight_$weight"))
                        }
                        FilterChip(selected = selectedWeight !in presetWeights, onClick = { customWeight = true },
                            label = { Text(if (selectedWeight in presetWeights) stringResource(R.string.custom_weight)
                                else stringResource(R.string.custom_selected, formatWeight(selectedWeight))) },
                            modifier = Modifier.testTag("custom_weight"))
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(stringResource(R.string.grade_title), style = MaterialTheme.typography.titleMedium)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        (2..5).forEach { grade ->
                            val description = stringResource(R.string.add_grade, grade, formatWeight(selectedWeight))
                            FilledTonalButton(onClick = { onAdd(grade) }, contentPadding = PaddingValues(8.dp),
                                modifier = Modifier.weight(1f).heightIn(min = 64.dp).testTag("add_$grade")
                                    .semantics { contentDescription = description }) {
                                Text("$grade", style = MaterialTheme.typography.headlineMedium)
                            }
                        }
                    }
                }
            }
            item {
                Column {
                    Text(stringResource(R.string.entries_title), style = MaterialTheme.typography.titleMedium)
                    if (entries.isNotEmpty()) TextButton(onClick = { clearConfirmation = true }, modifier = Modifier.testTag("clear")) {
                        Text(stringResource(R.string.clear_all))
                    }
                }
            }
            if (entries.isEmpty()) item {
                Text(stringResource(R.string.empty_entries), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            items(entries, key = { it.id }) { entry ->
                GradeEntry(entry, onEdit = { editingId = entry.id }, onDelete = { onDelete(entry.id) })
            }
        }
    }
    if (customWeight) WeightDialog(selectedWeight, onDismiss = { customWeight = false }) {
        onWeight(it); customWeight = false
    }
    entries.firstOrNull { it.id == editingId }?.let { entry ->
        EditGradeDialog(entry, onDismiss = { editingId = null }) { grade, weight ->
            onEdit(entry.id, grade, weight); editingId = null
        }
    }
    if (clearConfirmation) AlertDialog(
        onDismissRequest = { clearConfirmation = false },
        title = { Text(stringResource(R.string.clear_title)) },
        text = { Text(stringResource(R.string.clear_body)) },
        confirmButton = { TextButton(onClick = { onClear(); clearConfirmation = false }, modifier = Modifier.testTag("confirm_clear")) {
            Text(stringResource(R.string.clear_all))
        } },
        dismissButton = { TextButton(onClick = { clearConfirmation = false }) { Text(stringResource(R.string.cancel)) } }
    )
}
