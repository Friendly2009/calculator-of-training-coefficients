package com.example.calculatoroftrainingcoefficients.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val savedState: SavedStateHandle) : ViewModel() {
    var numbersList: List<Coefficient> by mutableStateOf(restore(savedState[ENTRIES]))
        private set
    var selectedWeight by mutableStateOf(savedState.get<Double>(WEIGHT)?.takeIf(::isValidWeight) ?: 1.0)
        private set
    val result: Double? get() = weightedAverage(numbersList)
    private var removed: List<Pair<Int, Coefficient>> = emptyList()

    fun selectWeight(weight: Double) {
        require(isValidWeight(weight))
        selectedWeight = weight
        savedState[WEIGHT] = weight
    }

    fun addElement(number: Int, coefficient: Double = selectedWeight): Boolean {
        val entry = Coefficient(number, coefficient)
        if (numbersList.size >= MAX_ENTRIES) return false
        update(numbersList + entry)
        return true
    }

    fun editElement(id: String, number: Int, coefficient: Double) {
        val replacement = Coefficient(number, coefficient, id)
        update(numbersList.map { if (it.id == id) replacement else it })
    }

    fun deleteElement(id: String) {
        val index = numbersList.indexOfFirst { it.id == id }
        if (index < 0) return
        removed = listOf(index to numbersList[index])
        update(numbersList.filterNot { it.id == id })
    }

    fun clear() {
        removed = numbersList.mapIndexed { index, entry -> index to entry }
        update(emptyList())
    }

    fun undoDelete(): Boolean {
        val missing = removed.filter { (_, old) -> numbersList.none { it.id == old.id } }
        if (numbersList.size + missing.size > MAX_ENTRIES) return false
        val restored = numbersList.toMutableList()
        missing.forEach { (index, entry) -> restored.add(index.coerceAtMost(restored.size), entry) }
        removed = emptyList()
        update(restored)
        return true
    }

    private fun update(entries: List<Coefficient>) {
        numbersList = entries
        savedState[ENTRIES] = ArrayList(entries.map { "${it.id}|${it.number}|${it.coefficient}" })
    }

    companion object {
        const val ENTRIES = "calculator.entries"
        const val WEIGHT = "calculator.weight"
        private fun restore(rows: ArrayList<String>?): List<Coefficient> = rows.orEmpty().mapNotNull {
            runCatching {
                val fields = it.split('|')
                require(fields.size == 3)
                Coefficient(fields[1].toInt(), fields[2].toDouble(), fields[0])
            }.getOrNull()
        }.distinctBy { it.id }.take(MAX_ENTRIES)
    }
}
