package com.bk.currency.domain.detail.di

import com.bk.currency.common.strategy.HighlightingStrategy
import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.domain.detail.repository.TableDetailsRepositoryImpl
import com.bk.currency.domain.detail.repository.TableDetailsRepository
import com.bk.currency.domain.detail.usecase.CurrencyDetailsMapper
import com.bk.currency.domain.detail.usecase.CurrencyDetailsMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryDetailsModule {

    @Singleton
    @Provides
    fun provideTableDetailsRepository(
        apiService: NbpApiService,
    ): TableDetailsRepository {
        return TableDetailsRepositoryImpl(
            apiService
        )
    }

    @Singleton
    @Provides
    fun provideCurrencyDetailsMapper(
        highlightingStrategy: HighlightingStrategy,
    ): CurrencyDetailsMapper {
        return CurrencyDetailsMapperImpl(
            highlightingStrategy
        )
    }
}