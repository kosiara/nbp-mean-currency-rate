package com.bk.currency.common.strategy

abstract class HighlightingStrategy {
    abstract fun shouldHighlight(rate: Double, meanRate: Double): Boolean
}