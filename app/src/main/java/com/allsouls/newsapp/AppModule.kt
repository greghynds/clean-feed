package com.allsouls.newsapp

import com.allsouls.newsapp.arch.presentation.Navigator
import com.allsouls.newsapp.arch.presentation.logging
import com.allsouls.newsapp.feed.presentation.createFetchFeedThunk
import com.allsouls.newsapp.feed.presentation.rootReducer
import com.allsouls.newsapp.feed.presentation.routing
import org.koin.dsl.module
import xyz.gwh.redux.createStore

val appModule = module {
    single { (router: Navigator) ->
        createStore(
            rootReducer,
            AppState.INITIAL,
            logging(),
            createFetchFeedThunk(get(), get()),
            routing(router)
        )
    }
}