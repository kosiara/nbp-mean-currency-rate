package com.bk.currency.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("mid")
    val mid: Double,

    @SerializedName("effectiveDate")
    val effectiveDate: String,
    @SerializedName("no")
    val serialNumber: String,
)
