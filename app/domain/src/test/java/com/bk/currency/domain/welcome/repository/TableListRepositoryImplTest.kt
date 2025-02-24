package com.bk.currency.domain.welcome.repository

import app.cash.turbine.test
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class TableListRepositoryImplTest {
    private val apiService: NbpApiService = mockk()
    private lateinit var repository: TableListRepositoryImpl

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = TableListRepositoryImpl(apiService)
    }

    @Test
    fun `currencyRates emits Loading then Success when API call is successful`() = runTest {
        // given
        val mockCurrencyTable = CurrencyTable("USD", "Dollar", "USD", "ABC-123", "2024-01-01", emptyList())
        coEvery { apiService.getCurrencies("A") } returns listOf(mockCurrencyTable)

        // when
        val flow = repository.currencyRates(NbpTableName.A)

        // then
        flow.test {
            assertEquals(DataState.Loading, awaitItem())
            assertEquals(DataState.Success(mockCurrencyTable), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `currencyRates emits Loading then Error when API returns an empty list`() = runTest {
        // given
        coEvery { apiService.getCurrencies("A") } returns emptyList()

        // when
        val flow = repository.currencyRates(NbpTableName.A)

        // then
        flow.test {
            assertEquals(DataState.Loading, awaitItem())
            val errorState = awaitItem()
            assertTrue(errorState is DataState.Error)
            assertEquals("Empty currency list received!", (errorState as DataState.Error).exception.localizedMessage)
            awaitComplete()
        }
    }

    @Test
    fun `currencyRates emits Loading then Error when API call fails`() = runTest {
        // gien
        val exception = IOException("Network error")
        coEvery { apiService.getCurrencies("A") } throws exception

        // when
        val flow = repository.currencyRates(NbpTableName.A)

        // then
        flow.test {
            assertEquals(DataState.Loading, awaitItem())
            assertEquals(DataState.Error(exception), awaitItem())
            awaitComplete()
        }
    }
}
