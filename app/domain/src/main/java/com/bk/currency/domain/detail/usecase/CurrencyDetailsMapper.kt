package com.bk.currency.domain.detail.usecase

import com.bk.currency.data.common.DataState
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow

interface CurrencyDetailsMapper {
    suspend fun map(items: Flow<DataState<CurrencyTable>>): Flow<DataState<CurrencyTable>>
}
