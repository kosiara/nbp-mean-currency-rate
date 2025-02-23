package com.bk.currency.domain.detail.repository

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow

interface TableDetailsRepository {
    suspend fun currencyDetail(tableName: NbpTableName, currencyCode: String): Flow<DataState<CurrencyTable>>
}