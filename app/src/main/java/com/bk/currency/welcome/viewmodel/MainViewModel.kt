package com.bk.currency.welcome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bk.currency.data.model.CurrencyTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> get()  = _uiState.asStateFlow()

    fun loadGenres() {
        viewModelScope.launch {
//            movieRepo.genreList()
//                .onStart {
//                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
//                }
//                .catch { exception ->
//                    _uiState.value = _uiState.value.copy(isLoading = false, error = exception)
//                }
//                .collect { result ->
//                    when (result) {
//                        is DataState.Success -> {
//                            _uiState.value = _uiState.value.copy(
//                                genres = result.data, // Extracting Genres object
//                                isLoading = false
//                            )
//                        }
//                        is DataState.Error -> {
//                            _uiState.value = _uiState.value.copy(
//                                isLoading = false,
//                                error = result.exception
//                            )
//                        }
//                        is DataState.Loading -> {
//                            _uiState.value = _uiState.value.copy(isLoading = true)
//                        }
//                    }
//                }
        }
    }


}
data class MainUiState(
    val currencyTable: CurrencyTable? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
