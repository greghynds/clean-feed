package com.allsouls.newsapp.feed.di

import com.allsouls.newsapp.arch.data.createApiClient
import com.allsouls.newsapp.feed.data.FeedApi
import com.allsouls.newsapp.feed.data.FeedClient
import com.allsouls.newsapp.feed.domain.FeedRepo
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.presentation.FeedPresenter
import com.allsouls.newsapp.feed.presentation.FeedView
import com.allsouls.newsapp.feed.ui.HeadlineCardPresenter
import com.allsouls.newsapp.feed.ui.HeadlineCardView
import com.allsouls.newsapp.feed.ui.HeadlinePresenter
import com.allsouls.newsapp.feed.ui.HeadlineView
import org.koin.dsl.module

val feedModule = module {
    single<FeedClient> { createApiClient(get()) }
    single<FeedRepo> { FeedApi(get()) }
    single { FetchFeed(get()) }
    factory { (view: FeedView) -> FeedPresenter(get(), get(), view) }
    factory { (view: HeadlineCardView) -> HeadlineCardPresenter(view) }
    factory { (view: HeadlineView) -> HeadlinePresenter(view) }
}