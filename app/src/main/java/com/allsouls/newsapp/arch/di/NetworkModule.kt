package com.allsouls.newsapp.arch.di

import com.allsouls.newsapp.BuildConfig
import com.allsouls.newsapp.arch.data.ClientConfig
import org.koin.dsl.module

val networkModule = module {
    single { ClientConfig(BuildConfig.HOST, get(), loggingEnabled = BuildConfig.DEBUG) }
}