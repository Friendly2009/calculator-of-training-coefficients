package com.example.calculatoroftrainingcoefficients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculatoroftrainingcoefficients.ui.Main
import com.example.calculatoroftrainingcoefficients.ui.SupportScreen
import com.example.calculatoroftrainingcoefficients.ui.theme.CalculatorOfTrainingCoefficientsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorOfTrainingCoefficientsTheme {
                Surface(Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main"){
                            Main({
                                navController.navigate("support")
                            })
                        }
                        composable("support"){
                            SupportScreen(
                                "support@example.com",
                                "http://PornHub.com",
                                { navController.navigate("main")
                            })
                        }
                    }
                }
            }
        }
    }
}