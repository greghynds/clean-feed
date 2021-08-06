package com.allsouls.newsapp.feed.di

import com.allsouls.newsapp.arch.data.createApiClient
import com.allsouls.newsapp.feed.data.FeedApi
import com.allsouls.newsapp.feed.data.FeedClient
import com.allsouls.newsapp.feed.domain.FeedRepo
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.presentation.FeedModel
import org.koin.dsl.module

val feedModule = module {
    single<FeedClient> { createApiClient(get()) }
    single<FeedRepo> { FeedApi(get()) }
    single { FetchFeed(get()) }
    factory { FeedModel(get(), get()) }
}