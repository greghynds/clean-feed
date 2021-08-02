package com.allsouls.newsapp.feed.presentation

import androidx.lifecycle.MutableLiveData
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Action
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.ui.createHeadlineRoute


class FeedModel(
    private val fetchFeed: FetchFeed,
    dispatchers: Dispatchers,
) : Model<FeedState>(dispatchers) {

    override val state = MutableLiveData(FeedState.empty())

    override fun send(action: Action) = with(action) {
        when (type) {
            LOAD_FEED -> loadFeed()
            SELECT_HEADLINE -> selectHeadline(payload as Headline)
        }
    }

    override fun onCoroutineError(error: Throwable) {
        emit(FeedState.error(error))
    }

    private fun loadFeed() {
        emit(FeedState.loading())

        main {
            emit(FeedState.from(
                io { fetchFeed.execute(Params.None) }
            ))
        }
    }

    private fun selectHeadline(headline: Headline) {
        navigateTo(createHeadlineRoute(headline))
    }

    companion object {
        const val LOAD_FEED = "LOAD_FEED"
        const val SELECT_HEADLINE = "SELECT_HEADLINE"
    }
}