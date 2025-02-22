package com.bk.currency.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bk.currency.data.model.CurrencyItem
import com.bk.currency.ui.theme.cornerRadius

@Composable
fun CurrencyItemRow(
    selected: Boolean,
    currencyItem: CurrencyItem,
    onclick: () -> Unit,
) {
//    val animateChipBackgroundColor by animateColorAsState(
//        targetValue = if (selected) Purple500 else Purple200,
//        animationSpec = tween(durationMillis = 50, easing = LinearOutSlowInEasing)
//    )

    Box(
        modifier = Modifier.padding(end = 8.dp)
            .cornerRadius(16)
            //.background(animateChipBackgroundColor)
            .height(32.dp)
            .widthIn(min = 80.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onclick
            )
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = currencyItem.currency,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            color = Color.White.copy(alpha = 0.80F),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}