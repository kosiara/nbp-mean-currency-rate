package com.piashcse.hilt_mvvm_compose_movie.data.repository.remote.movie

import androidx.paging.PagingData
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow

interface TableRepository {
    suspend fun currencyRates(tableName: NbpTableName): Flow<DataState<CurrencyTable>>
    suspend fun currencyDetail(tableName: NbpTableName, currencyCode: String): Flow<DataState<CurrencyTable>>
}