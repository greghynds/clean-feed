package com.allsouls.newsapp.tracking.di

import com.allsouls.newsapp.arch.data.createApiClient
import com.allsouls.newsapp.tracking.data.AnalyticsApi
import com.allsouls.newsapp.tracking.data.AnalyticsClient
import com.allsouls.newsapp.tracking.data.TrackingService
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.tracking.domain.Tracker
import org.koin.dsl.module

val trackingModule = module {
    single { createApiClient<AnalyticsClient>(get()) }
    single<Tracker> { AnalyticsApi(get()) }
    single { TrackEvent(get()) }
    single { TrackingService(get(), get()) }
}