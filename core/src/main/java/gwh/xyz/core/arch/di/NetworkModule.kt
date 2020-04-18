package gwh.xyz.core.arch.di

import gwh.xyz.core.BuildConfig
import gwh.xyz.core.arch.data.ClientConfig
import org.koin.dsl.module

val networkModule = module {
    val host =
        "https://raw.githubusercontent.com/bbc/news-and-weather-apps-coding-challenge-android/master/"
    single { ClientConfig(host, get(), loggingEnabled = BuildConfig.DEBUG) }
}