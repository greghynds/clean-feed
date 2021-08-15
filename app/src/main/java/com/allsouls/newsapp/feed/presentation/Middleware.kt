package com.allsouls.newsapp.feed.presentation

import android.util.Log
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.arch.presentation.createThunk
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.headline.domain.entity.Headline
import xyz.gwh.redux.Middleware

val logging: Middleware<FeedState> = { store ->
    { action ->
        Log.d("Logger", "Dispatching action: ${action.type}")
        action
    }
}

fun routing(router: FeedRouter): Middleware<FeedState> = {
    { action ->
        when {
            action.isOfType(SELECT_HEADLINE) -> router.navigateToHeadline(action.payload as Headline)
        }
        action
    }
}

fun createFetchFeedThunk(
    fetchFeed: FetchFeed,
    dispatchers: Dispatchers
): Middleware<FeedState> {
    return createThunk(LOAD_FEED, dispatchers) {
        fetchFeed.execute(Params.None)
            .fold(
                ::createLoadFeedSuccessAction,
                ::createLoadFeedFailureAction
            )
    }
}