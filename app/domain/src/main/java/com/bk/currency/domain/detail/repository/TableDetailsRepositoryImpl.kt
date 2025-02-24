package com.bk.currency.domain.detail.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyItem
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.detail.source.CurrencyPagingSource
import com.bk.currency.domain.detail.usecase.CurrencyDetailsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
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
            // Get Currency meta information
            val table = apiService.getCurrencyDetails(tableName.name, currencyCode, pageSize)
            emit(DataState.Success(table.copy(rates = emptyList())))

            val dynamicResultList = mutableListOf<CurrencyItem>()
            val pagingSource = CurrencyPagingSource(apiService, tableName.name, currencyCode)
            var loadResult = pagingSource.load(
                PagingSource.LoadParams.Append(key = 1, loadSize = pageSize, placeholdersEnabled = false)
            )
            while (loadResult is PagingSource.LoadResult.Page){
                dynamicResultList.addAll(loadResult.data.map { currencyDetailsMapper.map(it, table.rates.first().mid) })
                emit(DataState.Success(
                    table.copy(
                        rates = dynamicResultList
                    )
                ))
                val next = loadResult.nextKey

                loadResult = if (next == null) {
                    PagingSource.LoadResult.Invalid()
                } else {
                    pagingSource.load(
                        PagingSource.LoadParams.Append(key = next, loadSize = pageSize, placeholdersEnabled = false)
                    )
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception when downloading: exchangerates/rates/{tableName}/{currencyCode}/last/{responseCount}")
            emit(DataState.Error(e))
        }
    }

//    override suspend fun getCurrencyDetailsPaging(tableName: NbpTableName, currencyCode: String): Flow<PagingData<CurrencyItem>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = pageSize,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
//                CurrencyPagingSource(apiService, tableName.name, currencyCode)
//            }
//        ).flow
//    }
}
