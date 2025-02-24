package com.bk.currency.common.other

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Suppress("NewApi")
fun getDateFromToday(daysOffset: Long): String {
    val targetDate = LocalDate.now().minusDays(daysOffset)
    return targetDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
}