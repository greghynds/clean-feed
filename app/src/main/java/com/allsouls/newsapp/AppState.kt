package com.allsouls.newsapp

import com.allsouls.newsapp.feed.presentation.FeedState

data class AppState(
    val feedState: FeedState
) {
    companion object {
        val INITIAL = AppState(
            feedState = FeedState.empty()
        )
    }
}