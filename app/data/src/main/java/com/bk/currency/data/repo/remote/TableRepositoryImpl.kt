package com.piashcse.hilt_mvvm_compose_movie.data.repository.remote.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bk.currency.data.common.DataState
import com.bk.currency.data.common.NbpTableName
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.data.model.CurrencyItem
import com.bk.currency.data.model.CurrencyTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TableRepositoryImpl @Inject constructor(
    private val apiService: NbpApiService
) : TableRepository {


     override suspend fun currencyRates(tableName: NbpTableName): Flow<DataState<CurrencyTable>> =
        flow {
            emit(DataState.Loading)
            try {
                val query = apiService.getCurrencies(tableName.name)
                emit(DataState.Success(query))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

//    override suspend fun currencyDetail(movieId: Int): Flow<DataState<List<CurrencyDetails>>> = flow {
//        emit(DataState.Loading)
//        try {
//            val searchResult = apiService.currencyDetail(movieId)
//            emit(DataState.Success(searchResult))
//
//        } catch (e: Exception) {
//            emit(DataState.Error(e))
//        }
//    }



//    override fun nowPlayingMoviePagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
//        pagingSourceFactory = { NowPlayingMoviePagingDataSource(apiService, genreId) },
//        config = PagingConfig(pageSize = 20)
//    ).flow


}