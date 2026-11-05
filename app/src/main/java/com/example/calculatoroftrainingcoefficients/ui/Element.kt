package com.example.calculatoroftrainingcoefficients.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.calculatoroftrainingcoefficients.R
import com.example.calculatoroftrainingcoefficients.core.Coefficient
import com.example.calculatoroftrainingcoefficients.core.formatWeight

@Composable
fun GradeEntry(entry: Coefficient, onEdit: () -> Unit, onDelete: () -> Unit) {
    val editLabel = stringResource(R.string.edit_entry, entry.number, formatWeight(entry.coefficient))
    OutlinedCard(onClick = onEdit, modifier = Modifier.fillMaxWidth().testTag("entry_${entry.id}")
        .semantics { onClick(label = editLabel, action = null) }) {
        Row(Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("${entry.number}", style = MaterialTheme.typography.headlineMedium)
            Text(stringResource(R.string.entry_weight, formatWeight(entry.coefficient)), modifier = Modifier.weight(1f))
            IconButton(onClick = onDelete, modifier = Modifier.testTag("delete_${entry.id}")) {
                Icon(Icons.Outlined.Delete, stringResource(R.string.delete_entry, entry.number, formatWeight(entry.coefficient)))
            }
        }
    }
}
