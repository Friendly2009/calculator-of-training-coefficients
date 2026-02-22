package com.example.calculatoroftrainingcoefficients.core

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var numbersList = mutableStateListOf<Coefficient>()
    fun addElement(num: String, coef: String) {
        viewModelScope.launch {
            val newElement = Coefficient(num, coef)
            numbersList.add(newElement)
        }
    }

    fun calculate(listOfCoefficient: List<Coefficient>) {

    }

    fun deleteElement(count: Int){
        viewModelScope.launch {
            numbersList.removeAt(count)
        }
    }
}
