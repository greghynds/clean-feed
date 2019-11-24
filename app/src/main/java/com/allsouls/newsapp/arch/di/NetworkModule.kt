package com.allsouls.newsapp.arch.di

import com.allsouls.newsapp.BuildConfig
import com.allsouls.newsapp.arch.data.ClientConfig
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.koin.dsl.module

val networkModule = module {

    val logger = HttpLoggingInterceptor().apply { level = BODY }
    val interceptors = if (BuildConfig.DEBUG) listOf(logger) else listOf()

    single { ClientConfig(BuildConfig.HOST, get(), interceptors) }
}