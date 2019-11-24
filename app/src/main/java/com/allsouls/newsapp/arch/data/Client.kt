package com.allsouls.newsapp.arch.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

data class ClientConfig(
    val host: String,
    val mapper: ObjectMapper,
    val interceptors: List<Interceptor>
)

inline fun <reified Client> createApiClient(config: ClientConfig): Client {
    val client = OkHttpClient()
        .newBuilder()
        .apply {
            config.interceptors.forEach { interceptor ->
                addInterceptor(interceptor)
            }
        }
        .build()

    return Retrofit.Builder()
        .baseUrl(config.host)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(JacksonConverterFactory.create(config.mapper))
        .build()
        .create(Client::class.java)
}