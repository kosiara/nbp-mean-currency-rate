package com.bk.currency.domain.detail.di

import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.domain.detail.repository.TableDetailsRepositoryImpl
import com.bk.currency.domain.detail.repository.TableDetailsRepository
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
}