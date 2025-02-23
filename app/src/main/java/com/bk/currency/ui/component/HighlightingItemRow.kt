package com.bk.currency.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bk.currency.data.model.CurrencyItem
import java.util.Locale

@Composable
fun HighlightingItemRow(
    currencyItem: CurrencyItem,
    isHighlighted: Boolean = false,
    onclick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                if (isHighlighted) Color.Red else Color.Transparent
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onclick
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currencyItem.effectiveDate,
                modifier = Modifier.weight(1f),
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = String.format(locale = Locale.ENGLISH, "%10.3f", currencyItem.mid),
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(name = "CurrencyItemRow", showBackground = true)
@Composable
fun PreviewHighlightingItemRow() {
    HighlightingItemRow(
        CurrencyItem("Dolar amerykański", "USD", 4.12, effectiveDate = "2025-02-05", serialNumber = "023/A/NBP/2025"),
        isHighlighted = false,
        {},
    )
}

@Preview(name = "CurrencyItemRow", showBackground = true)
@Composable
fun PreviewHighlightingItemRow2() {
    HighlightingItemRow(
        CurrencyItem("rand (Republika Południowej Afryki)", "USD", 4.12, effectiveDate = "2025-02-04", serialNumber = "023/A/NBP/2025"),
        isHighlighted = true,
        {},
    )
}