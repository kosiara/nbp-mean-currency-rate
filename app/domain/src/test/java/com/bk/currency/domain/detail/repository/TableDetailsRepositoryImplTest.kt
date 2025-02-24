package com.bk.currency.domain.detail.repository

import app.cash.turbine.test
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class TableDetailsRepositoryImplTest {
    private val apiService: NbpApiService = mockk()
    private lateinit var repository: TableDetailsRepositoryImpl

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = TableDetailsRepositoryImpl(apiService)
    }

    @Test
    fun `currencyDetail emits Loading then Success when API call is successful`() = runTest {
        // given
        val mockCurrencyTable = CurrencyTable("USD", "Dollar", "USD", "ABC-123", "2024-01-01", emptyList())
        coEvery { apiService.getCurrencyDetails("A", "USD", 14) } returns mockCurrencyTable

        // when
        val flow = repository.currencyDetail(NbpTableName.A, "USD")

        // then
        flow.test {
            assertEquals(DataState.Loading, awaitItem())
            assertEquals(DataState.Success(mockCurrencyTable), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `currencyDetail emits Loading then Error when API call fails`() = runTest {
        // given
        val exception = IOException("Network error")
        coEvery { apiService.getCurrencyDetails("A", "USD", 14) } throws exception

        // when
        val flow = repository.currencyDetail(NbpTableName.A, "USD")

        // then
        flow.test {
            assertEquals(DataState.Loading, awaitItem())
            assertEquals(DataState.Error(exception), awaitItem())
            awaitComplete()
        }
    }
}
