package com.chrispassold.askbuddy

import android.app.Application
import com.chrispassold.core.ProductionTree
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ProductionTree())
        }
    }
}