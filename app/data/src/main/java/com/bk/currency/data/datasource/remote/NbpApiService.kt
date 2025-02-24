package com.bk.currency.data.datasource.remote

import com.bk.currency.data.model.CurrencyTable
import retrofit2.http.GET
import retrofit2.http.Path

interface NbpApiService {

    // https://api.nbp.pl/api/exchangerates/tables/A
    @GET("exchangerates/tables/{tableName}")
    suspend fun getCurrencies(
        @Path("tableName") tableName: String,
    ): List<CurrencyTable>

    // https://api.nbp.pl/api/exchangerates/rates/A/USD/last/14?format=json
    @GET("exchangerates/rates/{tableName}/{currencyCode}/last/{responseCount}")
    suspend fun getCurrencyDetails(
        @Path("tableName") tableName: String,
        @Path("currencyCode") currencyCode: String,
        @Path("responseCount") responseCount: Int,
    ): CurrencyTable

    //https://api.nbp.pl/api/exchangerates/rates/a/gbp/2012-01-01/2012-01-31/
    @GET("exchangerates/rates/{tableName}/{currencyCode}/{dateFromString}/{dateToString}/")
    suspend fun getCurrencyDetailsDateRange(
        @Path("tableName") tableName: String,
        @Path("currencyCode") currencyCode: String,
        @Path("dateFromString") dateFromString: String,
        @Path("dateToString") dateToString: String,
    ): CurrencyTable
}