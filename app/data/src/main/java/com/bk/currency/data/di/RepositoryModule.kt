package com.bk.currency.data.di

import com.bk.currency.data.datasource.remote.NbpApiService
import com.piashcse.hilt_mvvm_compose_movie.data.repository.remote.movie.TableRepository
import com.piashcse.hilt_mvvm_compose_movie.data.repository.remote.movie.TableRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTableRepository(
        apiService: NbpApiService,
    ): TableRepository {
        return TableRepositoryImpl(
            apiService
        )
    }
}