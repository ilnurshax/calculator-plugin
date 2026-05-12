package com.ilnur.calculator

import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

object ExpressionEvaluator {

    private val formatter = DecimalFormat("0.00")

    fun evaluate(expression: String): String {

        val value = ExpressionBuilder(expression)
            .build()
            .evaluate()

        return formatter.format(value)
    }
}