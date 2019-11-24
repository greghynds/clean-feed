package com.allsouls.newsapp

import android.app.Application
import com.allsouls.newsapp.arch.di.asyncModule
import com.allsouls.newsapp.arch.di.networkModule
import com.allsouls.newsapp.arch.di.serializationModule
import org.koin.core.context.startKoin

class App : Application() {

    private val modules = listOf(
        asyncModule,
        serializationModule,
        networkModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(modules)
        }
    }
}