package com.ilnur.calculator

import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object ExpressionEvaluator {

    private val formatter = DecimalFormat("0.00", DecimalFormatSymbols(Locale.US))

    fun evaluate(expression: String): String {

        val value = ExpressionBuilder(expression)
            .build()
            .evaluate()

        return formatter.format(value)
    }
}