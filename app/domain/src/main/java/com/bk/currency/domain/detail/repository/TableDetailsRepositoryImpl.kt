package com.bk.currency.domain.detail.repository

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class TableDetailsRepositoryImpl @Inject constructor(
    private val apiService: NbpApiService
) : TableDetailsRepository {

    override suspend fun currencyDetail(tableName: NbpTableName, currencyCode: String): Flow<DataState<CurrencyTable>> = flow {
        emit(DataState.Loading)
        try {
            val query = apiService.getCurrencyDetails(tableName.name, currencyCode, 14)
            emit(DataState.Success(query))
        } catch (e: Exception) {
            Timber.e(e, "Exception when downloading: exchangerates/rates/{tableName}/{currencyCode}/last/{responseCount}")
            emit(DataState.Error(e))
        }
    }

}