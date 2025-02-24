package com.bk.currency.domain.welcome.repository

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class TableListRepositoryImpl @Inject constructor(
    private val apiService: NbpApiService
) : TableListRepository {


     override suspend fun currencyRates(tableName: NbpTableName): Flow<DataState<CurrencyTable>> =
        flow {
            emit(DataState.Loading)
            try {
                val query = apiService.getCurrencies(tableName.name)
                if (query.isEmpty()) {
                    throw Exception("Empty currency list received!")
                }

                emit(DataState.Success(query.first()))
            } catch (e: Exception) {
                Timber.e(e, "Exception when downloading: api/exchangerates/tables")
                emit(DataState.Error(e))
            }
        }

//    override fun currencyRates(tableName: NbpTableName?): Flow<PagingData<CurrencyTable>> = Pager(
//        pagingSourceFactory = { TablePagingDataSource(apiService, tableName) },
//        config = PagingConfig(pageSize = 20)
//    ).flow
}