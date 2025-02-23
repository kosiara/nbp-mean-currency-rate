package com.bk.currency.domain.usecase

import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.MainDispatcherRule
import com.bk.currency.domain.detail.repository.TableDetailsRepository
import com.bk.currency.domain.detail.usecase.CurrencyDetailsMapper
import com.bk.currency.domain.detail.usecase.GetCurrencyDetailUseCase
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
class GetCurrencyDetailUseCaseTest {

    private val currencyRepo: TableDetailsRepository = mockk()
    private val currencyDetailsMapper: CurrencyDetailsMapper = mockk()
    private val useCase = GetCurrencyDetailUseCase(currencyRepo, currencyDetailsMapper)

    @get:Rule
    val coroutineRule = MainDispatcherRule() // Custom JUnit Rule for Dispatchers

    @Test
    fun `invoke should call currencyDetail() and map result`() = runTest {
        // Given
        val tableName = NbpTableName.A
        val currencyCode = "USD"
        val currencyResponse: Flow<DataState<CurrencyTable>> = flowOf(DataState.Success(mockk()))

        coEvery { currencyRepo.currencyDetail(tableName, currencyCode) } returns currencyResponse
        coEvery { currencyDetailsMapper.map(currencyResponse) } returns currencyResponse

        // When
        val result = useCase.invoke(tableName, currencyCode)

        // Then
        coVerify(exactly = 1) { currencyRepo.currencyDetail(tableName, currencyCode) }
        coVerify(exactly = 1) { currencyDetailsMapper.map(currencyResponse) }

        Assertions.assertTrue(result.first() is DataState.Success<*>)
    }
}
