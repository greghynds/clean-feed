package com.allsouls.newsapp

import com.allsouls.newsapp.feed.presentation.feedReducer
import xyz.gwh.redux.Reducer

val rootReducer: Reducer<AppState> = { state, action ->
    AppState(
        feedState = feedReducer(state.feedState, action)
    )
}