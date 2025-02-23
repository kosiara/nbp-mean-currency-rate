package com.bk.currency.domain.welcome.di

import com.bk.currency.data.datasource.remote.NbpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryListModule {

    @Singleton
    @Provides
    fun provideTableListRepository(
        apiService: NbpApiService,
    ): com.bk.currency.domain.welcome.repository.TableListRepository {
        return com.bk.currency.domain.welcome.repository.TableListRepositoryImpl(
            apiService
        )
    }
}