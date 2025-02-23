package com.bk.currency.data.di

import com.bk.currency.data.datasource.remote.NbpApiService
import com.bk.currency.domain.welcome.repository.TableRepository
import com.bk.currency.domain.welcome.repository.TableRepositoryImpl
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
    ): com.bk.currency.domain.welcome.repository.TableRepository {
        return com.bk.currency.domain.welcome.repository.TableRepositoryImpl(
            apiService
        )
    }
}