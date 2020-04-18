package gwh.xyz.feed.adapter

import gwh.xyz.feed.domain.entity.Feed
import gwh.xyz.feed.headline.domain.entity.Headline

interface FeedView {
    fun showLoading()
    fun showFeed(feed: Feed)
    fun showError(error: Throwable)
    fun showDetail(headline: Headline)
}