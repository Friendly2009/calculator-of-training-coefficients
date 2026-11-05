package com.example.calculatoroftrainingcoefficients.ui

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.calculatoroftrainingcoefficients.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(email: String, telegramLink: String, onExitClick: () -> Unit) {
    val context = LocalContext.current
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.about), maxLines = 1, overflow = TextOverflow.Ellipsis) },
            navigationIcon = { IconButton(onClick = onExitClick, modifier = Modifier.testTag("support_back")) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back))
            } })
    }, snackbarHost = { SnackbarHost(snackbar) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).consumeWindowInsets(padding)
            .verticalScroll(rememberScrollState()).padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(stringResource(R.string.about_body), style = MaterialTheme.typography.bodyLarge)
            Card { Text(stringResource(R.string.formula), modifier = Modifier.padding(20.dp)) }
            Text(stringResource(R.string.state_notice), color = MaterialTheme.colorScheme.onSurfaceVariant)
            OutlinedButton(onClick = {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Email", email))
                scope.launch { snackbar.showSnackbar(context.getString(R.string.email_copied)) }
            }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.copy_email)) }
            Text(email, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = {
                try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(telegramLink))) }
                catch (_: ActivityNotFoundException) {
                    scope.launch { snackbar.showSnackbar(context.getString(R.string.no_link_handler)) }
                }
            }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.open_telegram)) }
        }
    }
}
