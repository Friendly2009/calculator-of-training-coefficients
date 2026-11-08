package com.example.calculatoroftrainingcoefficients

import com.example.calculatoroftrainingcoefficients.core.*
import java.util.Locale
import org.junit.Assert.*
import org.junit.Test

class WeightedAverageTest {
    @Test fun emptyInputHasNoAverage() { assertNull(weightedAverage(emptyList())) }
    @Test fun calculatesWeightedAverage() {
        assertEquals(4.0, weightedAverage(listOf(Coefficient(2, 1.0), Coefficient(5, 2.0)))!!, 1e-12)
    }
    @Test fun handlesSmallWeightsAndDuplicates() {
        assertEquals(3.5, weightedAverage(listOf(Coefficient(2, Double.MIN_VALUE), Coefficient(5, Double.MIN_VALUE)))!!, 1e-12)
        assertEquals(5.0, weightedAverage(List(500) { Coefficient(5, 0.1) })!!, 1e-12)
    }
    @Test fun validatesGradesAndWeights() {
        listOf(1, 6).forEach { assertThrows(IllegalArgumentException::class.java) { Coefficient(it, 1.0) } }
        listOf(0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY, 1001.0).forEach {
            assertThrows(IllegalArgumentException::class.java) { Coefficient(5, it) }
        }
    }
    @Test fun parsesBothDecimalSeparators() {
        assertEquals(1.3, parseWeight(" 1,3 ")!!, 0.0)
        assertEquals(1.3, parseWeight("1.3")!!, 0.0)
        listOf("", "0", "-2", "NaN", "Infinity", "1,2.3", "1001").forEach { assertNull(parseWeight(it)) }
    }
    @Test fun roundsOnlyDisplayHalfUpAndUsesLocale() {
        assertEquals("4.13", formatAverage(4.125, Locale.US))
        assertEquals("4,13", formatAverage(4.125, Locale.forLanguageTag("ru")))
        assertEquals("—", formatAverage(null))
    }
}
