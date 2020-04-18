package com.allsouls.newsapp.feed.di

import com.allsouls.newsapp.arch.data.createApiClient
import com.allsouls.newsapp.feed.data.FeedApi
import com.allsouls.newsapp.feed.data.FeedClient
import com.allsouls.newsapp.feed.domain.FeedRepo
import com.allsouls.newsapp.headline.presentation.HeadlineCardPresenter
import com.allsouls.newsapp.headline.presentation.HeadlineCardView
import com.allsouls.newsapp.headline.presentation.HeadlinePresenter
import com.allsouls.newsapp.headline.presentation.HeadlineView
import org.koin.dsl.module

val feedModule = module {
    single<FeedClient> { createApiClient(get()) }
    single<FeedRepo> { FeedApi(get()) }
    factory { (view: HeadlineCardView) -> HeadlineCardPresenter(view) }
    factory { (view: HeadlineView) -> HeadlinePresenter(view) }
}