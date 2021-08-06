package com.allsouls.newsapp.feed.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.allsouls.newsapp.feed.presentation.FeedDelegate
import com.allsouls.newsapp.feed.presentation.FeedModel
import com.allsouls.newsapp.feed.presentation.FeedState


@Composable
fun FeedUi(model: FeedModel, actions: FeedDelegate) {
    val state: FeedState by model.updates().observeAsState(FeedState.empty())

    when {
        state.loading -> LoadingScreen()
        state.isRenderable() -> HeadlinesList(state.headlines, actions::selectHeadline)
        else -> ErrorScreen(actions::refresh)
    }
}