package com.example.calculatoroftrainingcoefficients.core

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var numbersList = mutableStateListOf<Coefficient>()

    var result by mutableStateOf(0f)
    fun addElement(num: Int, coef: Float) {
        viewModelScope.launch {
            val newElement = Coefficient(num, coef)
            numbersList.add(newElement)
        }
    }


    fun calculate() {
        var sumIndex = 0.0f
        var sumCoefficient = 0.0f

        numbersList.forEach { element ->
            sumIndex += element.number * element.coefficient
            sumCoefficient += element.coefficient
            result = sumIndex/sumCoefficient
        }
    }




    fun deleteElement(count: Int){
        viewModelScope.launch {
            numbersList.removeAt(count)
        }
    }
}
