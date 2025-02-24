package com.bk.currency.features.currency_list.viewmodel

import app.cash.turbine.test
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.welcome.usecase.GetCurrencyListUseCase
import com.bk.currency.features.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyListViewModelTest {

    private lateinit var viewModel: CurrencyListViewModel
    private val getCurrencyListUseCase: GetCurrencyListUseCase = mockk()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = CurrencyListViewModel(getCurrencyListUseCase)
    }

    @Test
    fun `initial viewmodel state is correct`() = runTest {
        assertEquals(null, viewModel.uiState.value.currencyTable)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(null, viewModel.uiState.value.error)
    }

    @Test
    fun `loadCurrencyDetails emits loading state first and stays loading if no data downloaded`() = runTest {
        coEvery { getCurrencyListUseCase.invoke(any()) } returns flow {}

        viewModel.uiState.test {
            viewModel.loadCurrencies(NbpTableName.A)
            assertEquals(MainUiState(isLoading = false), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(MainUiState(isLoading = true), awaitItem())
        }
    }

    @Test
    fun `loadCurrencyDetails updates uiState with success when data downloaded`() = runTest {
        val mockData = CurrencyTable("USD", "Dollar", "USD", "ABC-123", "2024-05-23", listOf())
        coEvery { getCurrencyListUseCase.invoke(any()) } returns flow {
            emit(DataState.Success(mockData))
        }

        viewModel.uiState.test {
            viewModel.loadCurrencies(NbpTableName.A)
            assertEquals(MainUiState(isLoading = false), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(MainUiState(isLoading = true), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(MainUiState(currencyTable = mockData, isLoading = false), awaitItem())
        }
    }

    @Test
    fun `loadCurrencyDetails updates uiState with error`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { getCurrencyListUseCase.invoke(any()) } returns flow {
            emit(DataState.Error(exception))
        }

        viewModel.uiState.test {
            viewModel.loadCurrencies(NbpTableName.A)
            assertEquals(MainUiState(isLoading = false), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(MainUiState(isLoading = true), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(MainUiState(isLoading = false, error = exception), awaitItem())
        }
    }
}
