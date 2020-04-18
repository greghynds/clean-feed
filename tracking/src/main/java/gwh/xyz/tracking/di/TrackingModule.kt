package gwh.xyz.tracking.di

import gwh.xyz.core.arch.data.createApiClient
import gwh.xyz.tracking.data.AnalyticsApi
import gwh.xyz.tracking.data.AnalyticsClient
import gwh.xyz.tracking.data.TrackingService
import gwh.xyz.tracking.domain.TrackEvent
import gwh.xyz.tracking.domain.Tracker
import org.koin.dsl.module

val trackingModule = module {
    single { createApiClient<AnalyticsClient>(get()) }
    single<Tracker> { AnalyticsApi(get()) }
    single { TrackEvent(get()) }
    single { TrackingService(get(), get()) }
}