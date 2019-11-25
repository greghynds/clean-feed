package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.domain.entity.Headline

interface FeedView {
    fun showLoading()
    fun showFeed(feed: Feed)
    fun showError(error: Throwable)
    fun showDetail(headline: Headline)
}