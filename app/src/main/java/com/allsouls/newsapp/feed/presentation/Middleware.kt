package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.AppState
import com.allsouls.newsapp.arch.async.Dispatchers
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.Navigator
import com.allsouls.newsapp.arch.presentation.createThunk
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.presentation.createHeadlineRoute
import com.github.greghynds.redux.Middleware


fun createRoutingMiddleware(router: Navigator): Middleware<AppState> = {
    { action ->
        val route = when {
            action.isOfType(SELECT_HEADLINE) -> createHeadlineRoute(action.payload as Headline)
            else -> null
        }

        route?.let(router::navigateTo)
        action
    }
}

fun createFetchFeedThunk(
    fetchFeed: FetchFeed,
    dispatchers: Dispatchers
): Middleware<AppState> {
    return createThunk(LOAD_FEED, dispatchers) {
        fetchFeed.execute(Params.None)
            .fold(
                ::createLoadFeedSuccessAction,
                ::createLoadFeedFailureAction
            )
    }
}