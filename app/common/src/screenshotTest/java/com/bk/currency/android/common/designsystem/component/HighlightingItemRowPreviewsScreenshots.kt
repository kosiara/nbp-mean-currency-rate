package com.bk.currency.android.common.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bk.currency.data.mock.DataMock

class HighlightingItemRowPreviewsScreenshots {
    @Preview(name = "CurrencyItemRow", showBackground = true)
    @Composable
    fun PreviewHighlightingItemRow() {
        HighlightingItemRow(
            DataMock.singleCurrencyItems[0],
            isHighlighted = false,
            {},
        )
    }

    @Preview(name = "CurrencyItemRow", showBackground = true)
    @Composable
    fun PreviewHighlightingItemRow2() {
        HighlightingItemRow(
            DataMock.singleCurrencyItems[1],
            isHighlighted = true,
            {},
        )
    }
}