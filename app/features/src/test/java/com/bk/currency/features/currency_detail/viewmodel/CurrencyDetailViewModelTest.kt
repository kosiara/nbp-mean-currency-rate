
package com.bk.currency.features.currency_detail.viewmodel

import app.cash.turbine.test
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.detail.usecase.GetCurrencyDetailUseCase
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
class CurrencyDetailViewModelTest {

    private lateinit var viewModel: CurrencyDetailViewModel
    private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase = mockk()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = CurrencyDetailViewModel(getCurrencyDetailUseCase)
    }

    @Test
    fun `initial viewmodel state is correct`() = runTest {
        assertEquals(null, viewModel.uiState.value.currencyTable)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(null, viewModel.uiState.value.error)
    }

    @Test
    fun `loadCurrencyDetails emits loading state first and stays loading if no data downloaded`() = runTest {
        coEvery { getCurrencyDetailUseCase.invoke(any(), any()) } returns flow {}

        viewModel.uiState.test {
            viewModel.loadCurrencyDetails(NbpTableName.A, "USD")
            assertEquals(DetailUiState(isLoading = false), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(DetailUiState(isLoading = true), awaitItem())
        }
    }

    @Test
    fun `loadCurrencyDetails updates uiState with success when data downloaded`() = runTest {
        val mockData = CurrencyTable("USD", "Dollar", "USD", "ABC-123", "2024-05-23", listOf())
        coEvery { getCurrencyDetailUseCase.invoke(any(), any()) } returns flow {
            emit(DataState.Success(mockData))
        }

        viewModel.uiState.test {
            viewModel.loadCurrencyDetails(NbpTableName.A, "USD")
            assertEquals(DetailUiState(isLoading = false), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(DetailUiState(isLoading = true), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(DetailUiState(currencyTable = mockData, isLoading = false), awaitItem())
        }
    }

    @Test
    fun `loadCurrencyDetails updates uiState with error`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { getCurrencyDetailUseCase.invoke(any(), any()) } returns flow {
            emit(DataState.Error(exception))
        }

        viewModel.uiState.test {
            viewModel.loadCurrencyDetails(NbpTableName.A, "USD")
            assertEquals(DetailUiState(isLoading = false), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(DetailUiState(isLoading = true), awaitItem())
            testScheduler.advanceUntilIdle()
            assertEquals(DetailUiState(isLoading = false, error = exception), awaitItem()) // Error state
        }
    }
}
