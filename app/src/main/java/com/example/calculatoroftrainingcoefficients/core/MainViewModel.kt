package com.example.calculatoroftrainingcoefficients.core

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val numbersList = mutableStateListOf<Coefficient>()
    fun addElement(num: String, coef: String) {
        viewModelScope.launch {
            val newElement = Coefficient(num, coef)
            numbersList.add(newElement)
        }
    }

    fun calculate(listOfCoefficient: List<Coefficient>) {

    }
}
