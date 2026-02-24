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

    fun addElement(num: Int, coef: Float) {
        viewModelScope.launch {
            val newElement = Coefficient(num, coef)
            numbersList.add(newElement)
        }
    }


    fun calculate() {

    }




    fun deleteElement(count: Int){
        viewModelScope.launch {
            numbersList.removeAt(count)
        }
    }
}
