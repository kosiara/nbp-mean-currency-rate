package com.bk.currency.data.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//// https://api.nbp.pl/api/exchangerates/tables/A?format=json

interface NbpApiService {
    @GET("exchangerates/tables/{tableName}")
    suspend fun getCurrencies(
        @Path("tableName") page: String,
    ): BaseModelMovie


}