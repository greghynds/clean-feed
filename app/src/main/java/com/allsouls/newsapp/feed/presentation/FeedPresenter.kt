package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.arch.presentation.Presenter
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline

class FeedPresenter(
    private val fetchFeed: FetchFeed,
    dispatchers: Dispatchers,
    private val view: FeedView
) : Presenter(dispatchers) {

    override fun onCoroutineError(error: Throwable) {
        view.showError(error)
    }

    fun load() = main {
        view.showLoading()

        io { fetchFeed.execute(Params.None) }
            .fold(::fetchFeedSuccess, ::fetchFeedFailure)
    }

    fun selectHeadline(headline: Headline) {
        view.showDetail(headline)
    }

    private fun fetchFeedSuccess(feed: Feed) {
        view.showFeed(sortByDate(feed))
    }

    private fun fetchFeedFailure(error: Throwable) {
        view.showError(error)
    }

    private fun sortByDate(feed: Feed): Feed {
        return Feed(feed.headlines.sortedByDescending(Headline::updated))
    }
}