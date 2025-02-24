package com.bk.currency.domain.detail.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyItem
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.detail.source.CurrencyPagingSource
import com.bk.currency.domain.detail.usecase.CurrencyDetailsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class TableDetailsRepositoryImpl @Inject constructor(
    private val apiService: NbpApiService,
    private val currencyDetailsMapper: CurrencyDetailsMapper,
) : TableDetailsRepository {

    private val pageSize = 4

    override suspend fun currencyDetail(tableName: NbpTableName, currencyCode: String): Flow<DataState<CurrencyTable>> = flow {
        emit(DataState.Loading)
        try {
            // API has no paging support
            // Load some data to have at least something visible
            val table = apiService.getCurrencyDetails(tableName.name, currencyCode, pageSize)
            emit(DataState.Success(table))
            val pagingFlow = getCurrencyDetailsPaging(tableName, currencyCode)
            pagingFlow.collect { pagingData ->
                val pagingDataOfCurrencyItem = pagingData.map { singleItem -> currencyDetailsMapper.map(singleItem, table.rates.first().mid) }
                emit(DataState.Success(
                    table.copy(
                        rates = table.rates.plus(pagingDataOfCurrencyItem.toList())
                    )
                ))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception when downloading: exchangerates/rates/{tableName}/{currencyCode}/last/{responseCount}")
            emit(DataState.Error(e))
        }
    }

    override suspend fun getCurrencyDetailsPaging(tableName: NbpTableName, currencyCode: String): Flow<PagingData<CurrencyItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CurrencyPagingSource(apiService, tableName.name, currencyCode)
            }
        ).flow
    }
}