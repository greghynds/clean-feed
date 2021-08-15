package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.feed.domain.entity.Feed
import xyz.gwh.redux.Reducer

val rootReducer: Reducer<FeedState> = { state, action ->
    when {
        action.isOfType(LOAD_FEED) -> FeedState.loading()
        action.isOfType(LOAD_FEED_SUCCESS) -> FeedState.from(action.payload as Feed)
        action.isOfType(LOAD_FEED_FAILURE) -> FeedState.error(ApiError(500, "???"))
        else -> state
    }
}