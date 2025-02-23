package com.bk.currency.data.datasource.remote

import com.bk.currency.data.model.CurrencyTable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//// https://api.nbp.pl/api/exchangerates/tables/A?format=json

interface NbpApiService {
    @GET("exchangerates/tables/{tableName}")
    suspend fun getCurrencies(
        @Path("tableName") tableName: String,
    ): List<CurrencyTable>

    //exchangerates/rates/A/USD/last/14?format=json
    @GET("exchangerates/rates/{tableName}/{currencyCode}/last/{responseCount}")
    suspend fun getCurrencyDetails(
        @Path("tableName") tableName: String,
        @Path("currencyCode") currencyCode: String,
        @Path("responseCount") responseCount: Int,
    ): CurrencyTable
}