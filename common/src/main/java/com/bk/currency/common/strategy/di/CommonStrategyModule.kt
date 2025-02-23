package com.bk.currency.common.strategy.di

import com.bk.currency.common.strategy.HighlightingStrategy
import com.bk.currency.common.strategy.PercentageHighlightingStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonStrategyModule {

    @Singleton
    @Provides
    fun providePercentageHighlightingStrategy(): HighlightingStrategy {
        return PercentageHighlightingStrategy(0.10)
    }
}