package com.bk.currency.common.strategy

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PercentageHighlightingStrategyTest {
    private val thresholdPercentage = 0.1
    private val strategy = PercentageHighlightingStrategy(thresholdPercentage)

    @Test
    fun `should highlight when difference is greater than threshold`() {
        val meanRate = 100.0
        val rate = 80.0
        assertTrue(strategy.shouldHighlight(rate, meanRate))
    }

    @Test
    fun `should not highlight when difference is less than threshold`() {
        val meanRate = 100.0
        val rate = 95.0
        assertFalse(strategy.shouldHighlight(rate, meanRate))
    }

    @Test
    fun `should not highlight when difference equals threshold`() {
        val meanRate = 100.0
        val rate = 90.0
        assertFalse(strategy.shouldHighlight(rate, meanRate))
    }

    @Test
    fun `should not highlight when rate equals meanRate`() {
        val meanRate = 100.0
        val rate = 100.0
        assertFalse(strategy.shouldHighlight(rate, meanRate))
    }

    @Test
    fun `should handle case when meanRate is zero and rate is nonzero`() {
        val meanRate = 0.0
        val rate = 5.0
        assertTrue(strategy.shouldHighlight(rate, meanRate))
    }

    @Test
    fun `should not highlight when both meanRate and rate are zero`() {
        val meanRate = 0.0
        val rate = 0.0
        assertFalse(strategy.shouldHighlight(rate, meanRate))
    }

    @Test
    fun `should not highlight when diff is 12 but threshold is 20 percent`() {
        val thresholdPercentage = 0.2
        val strategy = PercentageHighlightingStrategy(thresholdPercentage)
        val meanRate = 100.0
        val rate = 88.0
        assertFalse(strategy.shouldHighlight(rate, meanRate))
    }
}
