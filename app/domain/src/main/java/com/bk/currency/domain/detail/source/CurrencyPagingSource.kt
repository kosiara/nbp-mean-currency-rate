package com.bk.currency.domain.detail.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyItem

const val MAXIMUM_NUMBER_OF_CURRENCY_ITEMS = 14

class CurrencyPagingSource(
    private val apiService: NbpApiService,
    private val tableName: String,
    private val currencyCode: String
) : PagingSource<Int, CurrencyItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CurrencyItem> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize

            var rates: List<CurrencyItem> = emptyList()
            if (pageSize*currentPage < MAXIMUM_NUMBER_OF_CURRENCY_ITEMS) {
                val response = apiService.getCurrencyDetailsDateRange(
                    tableName,
                    currencyCode,
                    "2024-02-01",
                    "2024-02-04"
                )
                rates = response.rates
            }

            LoadResult.Page(
                data = rates,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (rates.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CurrencyItem>): Int? {
        return state.anchorPosition
    }
}
