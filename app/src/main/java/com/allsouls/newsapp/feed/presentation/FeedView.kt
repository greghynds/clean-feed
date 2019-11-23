package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.feed.domain.entity.Feed

interface FeedView {
    fun showFeed(feed: Feed)
    fun showError(error: Throwable)
}