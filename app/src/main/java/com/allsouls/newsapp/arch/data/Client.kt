package com.allsouls.newsapp.arch.data

import com.allsouls.newsapp.tracking.data.TrackingInterceptor
import com.fasterxml.jackson.databind.ObjectMapper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

data class ClientConfig(
    val host: String,
    val mapper: ObjectMapper,
    val loggingEnabled: Boolean = false,
    val trackingEnabled: Boolean = true
)

inline fun <reified Client> createApiClient(config: ClientConfig): Client {
    val logging by lazy { HttpLoggingInterceptor().apply { level = BASIC } }

    val client = with(config) {
        OkHttpClient()
            .newBuilder()
            .applyWhen(trackingEnabled) { addInterceptor(TrackingInterceptor()) }
            .applyWhen(loggingEnabled) { addInterceptor(logging) }
            .build()
    }

    return Retrofit.Builder()
        .baseUrl(config.host)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(JacksonConverterFactory.create(config.mapper))
        .build()
        .create(Client::class.java)
}

inline fun <T> T.applyWhen(condition: Boolean, block: T.() -> Unit): T {
    return if (condition) apply(block) else this
}