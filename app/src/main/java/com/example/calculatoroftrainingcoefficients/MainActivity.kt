package com.example.calculatoroftrainingcoefficients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.calculatoroftrainingcoefficients.ui.Main
import com.example.calculatoroftrainingcoefficients.ui.theme.CalculatorOfTrainingCoefficientsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorOfTrainingCoefficientsTheme {
                Surface(Modifier.fillMaxSize()) {
                    Main()
                }
            }
        }
    }
}