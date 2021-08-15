package com.allsouls.newsapp.feed.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import com.allsouls.newsapp.feed.presentation.FeedState
import com.allsouls.newsapp.feed.presentation.SELECT_HEADLINE
import com.allsouls.newsapp.feed.presentation.createLoadFeedAction
import xyz.gwh.redux.Action
import xyz.gwh.redux.Store


@Composable
fun FeedUi(
    store: Store<FeedState>
) {
    val state: FeedState by store.updates.subscribeAsState(initial = FeedState.empty())

    when {
        state.loading -> LoadingScreen()
        state.error != null -> ErrorScreen { store.dispatch(createLoadFeedAction()) }
        state.isRenderable() && state.headlines.isNotEmpty() -> {
            HeadlinesList(state.headlines) { store.dispatch(Action(SELECT_HEADLINE, it)) }
        }
    }
}