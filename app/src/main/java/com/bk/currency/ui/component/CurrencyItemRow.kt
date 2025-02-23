package com.bk.currency.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun CurrencyItemRow(
    selected: Boolean,
    currencyItem: CurrencyItem,
    onclick: () -> Unit,
) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onclick
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currencyItem.currency,
                modifier = Modifier.weight(1f),
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = currencyItem.code,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = String.format(locale = Locale.ENGLISH, "%10.3f", currencyItem.mid),
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
}

@Preview(name = "CurrencyItemRow", showBackground = true)
@Composable
fun PreviewCurrencyItemRow() {
    CurrencyItemRow(
        true,
        CurrencyItem("Dolar amerykański", "USD", 4.12),
        {},
    )
}

@Preview(name = "CurrencyItemRow", showBackground = true)
@Composable
fun PreviewCurrencyItemRow2() {
    CurrencyItemRow(
        true,
        CurrencyItem("rand (Republika Południowej Afryki)", "USD", 4.12),
        {},
    )
}