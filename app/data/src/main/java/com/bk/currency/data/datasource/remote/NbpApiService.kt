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
}