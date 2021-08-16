package com.allsouls.newsapp.feed.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import com.allsouls.newsapp.AppState
import com.allsouls.newsapp.feed.presentation.FeedState
import com.allsouls.newsapp.feed.presentation.createLoadFeedAction
import com.allsouls.newsapp.feed.presentation.createSelectHeadlineAction
import com.github.greghynds.redux.Store
import com.github.greghynds.redux.select


@Composable
fun FeedUi(store: Store<AppState>) {

    val state: FeedState by store
        .select(AppState::feedState)
        .subscribeAsState(initial = FeedState.empty())

    when {
        state.loading -> LoadingScreen()
        state.error != null -> ErrorScreen { store.dispatch(createLoadFeedAction()) }
        state.isRenderable() -> HeadlinesList(state.headlines) { headline ->
            store.dispatch(createSelectHeadlineAction(headline))
        }
    }
}