package com.bk.currency.domain.detail.usecase

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.detail.repository.TableDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val currencyRepo: TableDetailsRepository,
    private val currencyDetailsMapper: CurrencyDetailsMapper,
) {
    suspend operator fun invoke(tableName: NbpTableName, currencyCode: String): Flow<DataState<CurrencyTable>> {
        return currencyDetailsMapper.map(
            currencyRepo.currencyDetail(tableName, currencyCode)
        )
    }
}
