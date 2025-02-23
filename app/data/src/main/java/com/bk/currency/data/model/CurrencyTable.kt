package com.bk.currency.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyTable(
    @SerializedName("table")
    val tableName: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("no")
    val serialNumber: String,
    @SerializedName("effectiveDate")
    val effectiveDate: String,
    @SerializedName("rates")
    val rates: List<CurrencyItem>
)
