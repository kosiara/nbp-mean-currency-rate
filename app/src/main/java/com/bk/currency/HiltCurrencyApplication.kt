package com.bk.currency

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltCurrencyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.d("Currency Application started")
    }
}