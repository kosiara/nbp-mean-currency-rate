package com.bk.currency.common.strategy

import kotlin.math.abs

class PercentageHighlightingStrategy(private val thresholdPercentage: Double): HighlightingStrategy() {

    override fun shouldHighlight(rate: Double, meanRate: Double): Boolean {
        val diff = abs(meanRate - rate)
        return diff > thresholdPercentage * meanRate
    }
}