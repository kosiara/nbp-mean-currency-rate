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
            // Load some data to have at least something visible
            val table = apiService.getCurrencyDetails(tableName.name, currencyCode, pageSize)
            emit(DataState.Success(table))
            val pagingFlow = getCurrencyDetailsPaging(tableName, currencyCode)
            pagingFlow.collect { pagingData ->

                // WARNING : THIS SHOULD NOT BE DONE; Feature request:
                // https://issuetracker.google.com/issues/171812435?pli=1
                val items = pagingData.map { singleItem ->
                    currencyDetailsMapper.map(singleItem, table.rates.first().mid) }.toList()

                emit(DataState.Success(
                    table.copy(
                        rates = table.rates.plus(items)
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
                CurrencyPagingSource(apiService, pageSize, tableName.name, currencyCode)
            }
        ).flow
    }
}

/**
 * Extracts the list of data from a PagingData object.
 * Useful for testing transformations on PagingData.
 *
 * flowOf(PagingData.from(listOf(model)).toList() == listOf(model)
 *
 * When nothing else is left, Java reflection will always be there to help us out.
 */
@Suppress("UNCHECKED_CAST")
private suspend fun <T : Any> PagingData<T>.toList(): List<T> {
    val flow = PagingData::class.java.getDeclaredField("flow").apply {
        isAccessible = true
    }.get(this) as Flow<Any?>
    //val pageEventInsert = flow.single()
    val pageEventInsert = (flow as Flow<T>).take(2).drop(1).first()

    val pageEventInsertClass = Class.forName("androidx.paging.PageEvent\$Insert")
    val pagesField = pageEventInsertClass.getDeclaredField("pages").apply {
        isAccessible = true
    }
    val pages = pagesField.get(pageEventInsert) as List<Any?>
    val transformablePageDataField =
        Class.forName("androidx.paging.TransformablePage").getDeclaredField("data").apply {
            isAccessible = true
        }
    val listItems =
        pages.flatMap { transformablePageDataField.get(it) as List<*> }
    return listItems as List<T>
}
// https://gist.github.com/orgmir/05b4b0265ca63fed46f2c6496c9ad913