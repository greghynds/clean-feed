package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.arch.presentation.Model
import com.allsouls.newsapp.feed.domain.FetchFeed
import xyz.gwh.redux.Action


class FeedModel(
    private val fetchFeed: FetchFeed,
    dispatchers: Dispatchers,
) : Model<FeedState>(dispatchers) {

    override fun send(action: Action) = with(action) {
        when (type) {
            LOAD_FEED -> _loadFeed()
        }
    }

    override fun onCoroutineError(error: Throwable) {
        emit(FeedState.error(error))
    }

    private fun _loadFeed() {
        emit(FeedState.loading())

        main {
            emit(FeedState.from(
                io { fetchFeed.execute(Params.None) }
            ))
        }
    }
}