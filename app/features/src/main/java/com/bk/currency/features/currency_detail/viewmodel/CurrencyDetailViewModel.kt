package com.bk.currency.features.currency_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.model.CurrencyTable
import com.bk.currency.domain.detail.usecase.GetCurrencyDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> get()  = _uiState.asStateFlow()

    fun loadCurrencyDetails(tableName: NbpTableName, currencyCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrencyDetailUseCase.invoke(tableName, currencyCode)
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = exception)
                }
                .collect { result ->
                    when (result) {
                        is DataState.Success -> {
                            _uiState.value = _uiState.value.copy(
                                currencyTable = result.data,
                                isLoading = false
                            )
                        }
                        is DataState.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = result.exception
                            )
                        }
                        is DataState.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }
}

data class DetailUiState(
    val currencyTable: CurrencyTable? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
