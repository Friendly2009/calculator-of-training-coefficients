package com.example.calculatoroftrainingcoefficients.core

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun formatAverage(value: Double?, locale: Locale = Locale.getDefault()): String {
    if (value == null) return "—"
    return NumberFormat.getNumberInstance(locale).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
        isGroupingUsed = false
    }.format(BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP))
}

fun formatWeight(value: Double): String {
    val text = if (value < 0.000001) value.toString()
        else BigDecimal.valueOf(value).stripTrailingZeros().toPlainString()
    return text.replace('.', DecimalFormatSymbols.getInstance().decimalSeparator)
}
