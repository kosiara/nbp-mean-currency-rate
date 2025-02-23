package com.bk.currency.android.common.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bk.currency.data.mock.DataMock

class CurrencyDetailListPreviewsScreenshots {
    @Preview(name = "CurrencyDetailScreen", showBackground = true)
    @Composable
    fun PreviewCurrencyDetailList() {
        CurrencyDetailList(
            "Dolar amerykański",
            "USD",
            DataMock.singleCurrencyItems,
        )
    }
}