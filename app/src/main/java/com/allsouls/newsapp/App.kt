package com.allsouls.newsapp

import android.app.Application
import com.allsouls.newsapp.arch.di.asyncModule
import com.allsouls.newsapp.arch.di.networkModule
import com.allsouls.newsapp.arch.di.serializationModule
import com.allsouls.newsapp.feed.di.feedModule
import com.allsouls.newsapp.tracking.di.trackingModule
import org.koin.core.context.startKoin

class App : Application() {

    private val modules = listOf(
        asyncModule,
        serializationModule,
        trackingModule,
        networkModule,
        feedModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(modules)
        }
    }
}