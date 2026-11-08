package com.example.calculatoroftrainingcoefficients.core

import java.util.UUID

const val MAX_WEIGHT = 1000.0
const val MAX_ENTRIES = 500
fun isValidWeight(value: Double): Boolean = value.isFinite() && value > 0 && value <= MAX_WEIGHT
fun parseWeight(text: String): Double? = text.trim().replace(',', '.').toDoubleOrNull()?.takeIf(::isValidWeight)

data class Coefficient(val number: Int, val coefficient: Double, val id: String = UUID.randomUUID().toString()) {
    init {
        require(number in 2..5) { "Grade must be between 2 and 5" }
        require(isValidWeight(coefficient)) { "Invalid weight" }
        require(id.isNotBlank() && '|' !in id) { "Invalid ID" }
    }
}

fun weightedAverage(entries: List<Coefficient>): Double? {
    val scale = entries.maxOfOrNull { it.coefficient } ?: return null
    // Normalize before summing, including for subnormal positive weights.
    return entries.sumOf { it.number * (it.coefficient / scale) } /
        entries.sumOf { it.coefficient / scale }
}
