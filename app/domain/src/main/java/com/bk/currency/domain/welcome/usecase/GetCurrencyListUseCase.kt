package com.bk.currency.domain.welcome.usecase

import javax.inject.Inject
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.welcome.repository.TableListRepository
import kotlinx.coroutines.flow.Flow

class GetCurrencyListUseCase @Inject constructor(
    private val currencyRepo: TableListRepository
) {
    suspend operator fun invoke(tableName: NbpTableName): Flow<DataState<CurrencyTable>> {
        return currencyRepo.currencyRates(tableName)
    }
}
