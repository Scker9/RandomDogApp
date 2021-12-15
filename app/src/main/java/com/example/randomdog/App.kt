package com.example.randomdog

import android.app.Application
import com.example.randomdog.di.Modules.interactors
import com.example.randomdog.di.Modules.network
import com.example.randomdog.di.Modules.picasso
import com.example.randomdog.di.Modules.presenters
import com.example.randomdog.di.Modules.repos
import com.example.randomdog.di.Modules.router
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(network, repos, presenters, router, interactors,picasso))
        }
    }
}