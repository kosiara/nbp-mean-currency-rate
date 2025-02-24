package com.bk.currency.domain.welcome.repository

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow

interface TableListRepository {
    suspend fun currencyRates(tableName: NbpTableName): Flow<DataState<CurrencyTable>>
}