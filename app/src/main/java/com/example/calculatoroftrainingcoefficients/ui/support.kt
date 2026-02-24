package com.example.calculatoroftrainingcoefficients.ui
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SupportScreen(
    email: String = "support@example.com",
    telegramLink: String = "https://t.me/your_support_channel",
    onExitClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Support", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Email")
        Text(
            text = email,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Email", email)
                clipboard.setPrimaryClip(clip)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Telegram: ")
        Text(
            text = telegramLink,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telegramLink))
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = onExitClick) {
            Text(text = "Exit to app")
        }
    }
}
