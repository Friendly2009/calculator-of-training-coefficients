package com.example.calculatoroftrainingcoefficients.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColors = darkColorScheme(
    primary = LightBlue, onPrimary = Color(0xFF003064),
    primaryContainer = Color(0xFF17365F), onPrimaryContainer = Color(0xFFDFEAFF),
    secondaryContainer = Color(0xFF29374C), onSecondaryContainer = PaleBlue,
    background = Graphite, surface = Graphite,
    onSurface = Color(0xFFE3E5EB), onBackground = Color(0xFFE3E5EB)
)
private val LightColors = lightColorScheme(
    primary = Blue, onPrimary = Color.White,
    primaryContainer = PaleBlue, onPrimaryContainer = Color(0xFF112E54),
    secondaryContainer = Color(0xFFE5ECF7), onSecondaryContainer = Color(0xFF25364E),
    background = Paper, surface = Paper, onSurface = Ink, onBackground = Ink
)

@Composable
fun CalculatorOfTrainingCoefficientsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), dynamicColor: Boolean = false, content: @Composable () -> Unit
) {
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(colorScheme = colors, typography = Typography, content = content)
}
