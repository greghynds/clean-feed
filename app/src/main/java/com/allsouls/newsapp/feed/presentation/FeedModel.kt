package com.allsouls.newsapp.feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.ui.createHeadlineRoute
import com.gwh.routes.Route
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FeedModel(
    private val fetchFeed: FetchFeed,
    private val dispatchers: Dispatchers,
    private val view: FeedView
) : Model<FeedState>() {

    override val state = MutableLiveData(FeedState(listOf()))
    override val routes = MutableLiveData<Route>()

    fun load() {
        viewModelScope.launch(dispatchers.main) {
            withContext(dispatchers.io) { fetchFeed.execute(Params.None) }
                .fold(::fetchFeedSuccess, ::fetchFeedFailure)
        }
    }

    fun selectHeadline(headline: Headline) {
        routes.value = createHeadlineRoute(headline)
    }

    private fun fetchFeedSuccess(feed: Feed) {
        state.value = FeedState(sortByDate(feed.headlines))
    }

    private fun fetchFeedFailure(error: Throwable) {
        view.showError(error)
    }

    private fun sortByDate(headlines: List<Headline>): List<Headline> {
        return headlines.sortedByDescending(Headline::updated)
    }
}