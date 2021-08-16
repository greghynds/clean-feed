package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.feed.domain.entity.Feed
import com.github.greghynds.redux.Reducer

val feedReducer: Reducer<FeedState> = { state, action ->
    when {
        action.isOfType(LOAD_FEED) -> FeedState.loading()
        action.isOfType(LOAD_FEED_SUCCESS) -> FeedState.from(action.payload as Feed)
        action.isOfType(LOAD_FEED_FAILURE) -> FeedState.error(action.payload as Throwable)
        else -> state
    }
}