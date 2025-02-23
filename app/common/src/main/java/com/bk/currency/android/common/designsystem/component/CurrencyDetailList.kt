package com.bk.currency.android.common.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bk.currency.data.mock.DataMock
import com.bk.currency.data.model.CurrencyItem

@Composable
fun CurrencyDetailList(
    name: String,
    code: String,
    rates: List<CurrencyItem>
) {
    Column {
        Text(
            text = name,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = code,
            modifier = Modifier.padding(start = 30.dp, bottom = 12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            items(rates) { item ->
                HighlightingItemRow(
                    currencyItem = item,
                    onclick = {}
                )
            }
        }
    }
}

@Preview(name = "CurrencyDetailScreen", showBackground = true)
@Composable
fun PreviewCurrencyDetailList() {
    CurrencyDetailList(
        "Dolar amerykański",
        "USD",
        DataMock.singleCurrencyItems,
    )
}