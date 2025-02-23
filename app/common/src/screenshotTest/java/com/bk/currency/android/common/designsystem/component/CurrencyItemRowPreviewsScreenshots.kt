package com.bk.currency.android.common.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bk.currency.data.model.CurrencyItem

class CurrencyItemRowPreviewsScreenshots {
    @Preview(name = "CurrencyItemRow", showBackground = true)
    @Composable
    fun PreviewCurrencyItemRow() {
        CurrencyItemRow(
            CurrencyItem("Dolar amerykański", "USD", 4.12, effectiveDate = "2025-02-04", serialNumber = "023/A/NBP/2025"),
            {},
        )
    }

    @Preview(name = "CurrencyItemRow", showBackground = true)
    @Composable
    fun PreviewCurrencyItemRow2() {
        CurrencyItemRow(
            CurrencyItem("rand (Republika Południowej Afryki)", "USD", 4.12, effectiveDate = "2025-02-04", serialNumber = "023/A/NBP/2025"),
            {},
        )
    }
}