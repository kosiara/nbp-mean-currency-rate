package com.bk.currency.domain.welcome.usecase

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.MainDispatcherRule
import com.bk.currency.domain.welcome.repository.TableListRepository
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.first
import org.junit.Test
import org.junit.jupiter.api.Assertions

@ExperimentalCoroutinesApi
class GetCurrencyListUseCaseTest {

    private val currencyRepo: TableListRepository = mockk()
    private val useCase = GetCurrencyListUseCase(currencyRepo)

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Test
    fun `invoke should call currencyRates()`() = runTest {
        // Given
        val tableName = NbpTableName.A
        val currencyCode = "USD"
        val currencyResponse: Flow<DataState<CurrencyTable>> = flowOf(DataState.Success(mockk()))

        coEvery { currencyRepo.currencyRates(tableName) } returns currencyResponse

        // When
        val result = useCase.invoke(tableName)

        // Then
        coVerify(exactly = 1) { currencyRepo.currencyRates(tableName) }

        Assertions.assertTrue(result.first() is DataState.Success<*>)
    }
}
