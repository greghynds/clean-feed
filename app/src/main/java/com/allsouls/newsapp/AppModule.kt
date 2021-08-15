package com.allsouls.newsapp

import com.allsouls.newsapp.arch.presentation.Navigator
import com.allsouls.newsapp.arch.presentation.createLoggingMiddleware
import com.allsouls.newsapp.feed.presentation.createFetchFeedThunk
import com.allsouls.newsapp.feed.presentation.createRoutingMiddleware
import org.koin.dsl.module
import xyz.gwh.redux.createStore

val appModule = module {
    single { (router: Navigator) ->
        createStore(
            rootReducer,
            AppState.INITIAL,
            createLoggingMiddleware(),
            createFetchFeedThunk(get(), get()),
            createRoutingMiddleware(router)
        )
    }
}