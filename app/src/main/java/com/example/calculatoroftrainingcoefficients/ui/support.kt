package com.example.calculatoroftrainingcoefficients.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SupportScreen(
    email: String = "support@example.com",
    telegramLink: String = "https://t.me/your_support_channel",
    onExitClick: () -> Unit
) {
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

            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Telegram: ")
        Text(
            text = telegramLink,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {

            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = onExitClick) {
            Text(text = "Exit to app")
        }
    }
}
