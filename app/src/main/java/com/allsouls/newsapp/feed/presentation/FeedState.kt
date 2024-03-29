package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline

data class FeedState(
    val headlines: List<Headline> = listOf(),
    val error: Throwable? = null,
    val loading: Boolean = false
) {

    fun isRenderable(): Boolean = !loading && error == null

    companion object {
        fun empty() = FeedState()
        fun loading() = FeedState(loading = true)
        fun error(error: Throwable) = FeedState(error = error)
        fun from(feed: Feed) = FeedState(headlines = feed.headlines.sortedByDescending(Headline::updated))
    }
}