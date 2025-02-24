package com.bk.currency.domain.detail.usecase

import androidx.paging.PagingData
import com.bk.currency.common.strategy.HighlightingStrategy
import com.bk.currency.data.common.DataState
import com.bk.currency.data.model.CurrencyItem
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyDetailsMapperImpl @Inject constructor(
    private val highlightingStrategy: HighlightingStrategy,
): CurrencyDetailsMapper {
    override suspend fun map(items: Flow<DataState<CurrencyTable>>): Flow<DataState<CurrencyTable>> {
        return items.map { result ->
            if (result is DataState.Success) {
                result.copy(
                    data = result.data.copy(
                        rates = result.data.rates.map { rate ->
                            rate.copy(
                                isHighlighted = highlightingStrategy.shouldHighlight(
                                    rate.mid, result.data.rates.first().mid
                                )
                            )
                        }.sortedByDescending { it.effectiveDate }
                    )
                )
            } else {
                result
            }
        }
    }

    override suspend fun mapPaging(items: Flow<PagingData<CurrencyItem>>): Flow<DataState<CurrencyTable>> {
        return emptyFlow()
    }
}
