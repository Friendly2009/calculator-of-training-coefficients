package com.example.calculatoroftrainingcoefficients.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.calculatoroftrainingcoefficients.R
import com.example.calculatoroftrainingcoefficients.core.Coefficient
import com.example.calculatoroftrainingcoefficients.core.parseWeight

@Composable
private fun WeightField(value: String, onChange: (String) -> Unit) {
    val invalid = parseWeight(value) == null
    OutlinedTextField(value = value, onValueChange = onChange, singleLine = true,
        label = { Text(stringResource(R.string.weight_label)) }, isError = invalid,
        supportingText = { Text(stringResource(if (invalid) R.string.weight_error else R.string.weight_help)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = Modifier.fillMaxWidth().testTag("weight_input"))
}

@Composable
fun WeightDialog(initialWeight: Double, onDismiss: () -> Unit, onSave: (Double) -> Unit) {
    var text by rememberSaveable { mutableStateOf(initialWeight.toString()) }
    AlertDialog(onDismissRequest = onDismiss, title = { Text(stringResource(R.string.custom_weight)) },
        text = { Column(Modifier.verticalScroll(rememberScrollState())) { WeightField(text) { text = it } } },
        confirmButton = { TextButton(onClick = { parseWeight(text)?.let(onSave) }, enabled = parseWeight(text) != null,
            modifier = Modifier.testTag("save_weight")) { Text(stringResource(R.string.save)) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) } })
}

@Composable
fun EditGradeDialog(entry: Coefficient, onDismiss: () -> Unit, onSave: (Int, Double) -> Unit) {
    var grade by rememberSaveable(entry.id) { mutableStateOf(entry.number) }
    var weight by rememberSaveable(entry.id) { mutableStateOf(entry.coefficient.toString()) }
    AlertDialog(onDismissRequest = onDismiss, title = { Text(stringResource(R.string.edit_title)) },
        text = { Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(stringResource(R.string.grade_label))
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                (2..5).forEach { number -> FilterChip(selected = number == grade, onClick = { grade = number }, label = { Text("$number") }) }
            }
            WeightField(weight) { weight = it }
        } },
        confirmButton = { TextButton(onClick = { parseWeight(weight)?.let { onSave(grade, it) } }, enabled = parseWeight(weight) != null) {
            Text(stringResource(R.string.save))
        } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) } })
}
