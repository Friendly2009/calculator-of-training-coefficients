package com.example.calculatoroftrainingcoefficients.core

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Job

class MainViewModel (application: Application) : AndroidViewModel(application){
    private var searchJob: Job? = null
    private val appContext get() = getApplication<Application>().applicationContext
    var numbersList: List<Coefficient> = mutableListOf()

    fun addElement(num: String, coef: String){
        val newElement = Coefficient(num, coef)
        numbersList = numbersList + newElement
        Log.i("main", numbersList.count().toString())
        Log.i("main", numbersList[0].number)
    }
    fun calculate(listOfCoefficient: List<Coefficient>){

    }
}