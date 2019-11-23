package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.arch.presentation.Presenter
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed

class FeedPresenter(
    private val fetchFeed: FetchFeed,
    dispatchers: Dispatchers,
    private val view: FeedView
) : Presenter(dispatchers) {

    fun load() = main {
        io { fetchFeed.execute(Params.None) }
            .fold(::fetchFeedSuccess, ::fetchFeedFailure)
    }

    private fun fetchFeedSuccess(feed: Feed) {
        view.showFeed(feed)
    }

    private fun fetchFeedFailure(error: Throwable) {
        view.showError(error)
    }
}