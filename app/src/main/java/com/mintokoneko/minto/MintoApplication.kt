package com.mintokoneko.minto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MintoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}