package com.example.calculatoroftrainingcoefficients.core

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var numbersList = mutableStateListOf<Coefficient>()
    var result = mutableFloatStateOf(0.00f)

    fun addElement(num: String, coef: String) {
        viewModelScope.launch {
            val newElement = Coefficient(num, coef)
            numbersList.add(newElement)
        }
    }

    fun calculate() {
        var sumIndex = 0.00f
        var sumCoefficient = 0.00f

        numbersList.forEach { element ->
            sumIndex += element.number.toFloat() * element.coefficient.toFloat()
            sumCoefficient += element.coefficient.toFloat()
        }

        result.value = if (sumCoefficient != 0.00f) {
            String.format("%.2f", sumIndex / sumCoefficient).toFloat()
        } else {
            0.00f
        }
        Log.i("main", "$result")
    }

    fun deleteElement(count: Int){
        viewModelScope.launch {
            numbersList.removeAt(count)
        }
    }
}
