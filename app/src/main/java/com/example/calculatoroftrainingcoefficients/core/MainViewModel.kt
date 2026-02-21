package com.example.calculatoroftrainingcoefficients.core

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var searchJob: Job? = null
    private val appContext get() = getApplication<Application>().applicationContext

    val numbersList = mutableStateListOf<Coefficient>()

    fun addElement(num: String, coef: String) {
        viewModelScope.launch {
            val newElement = Coefficient(num, coef)
            numbersList.add(newElement)
            Log.i("main", numbersList.count().toString())
        }
    }

    fun calculate(listOfCoefficient: List<Coefficient>) {

    }
}
