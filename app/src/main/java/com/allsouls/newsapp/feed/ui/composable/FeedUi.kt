package com.allsouls.newsapp.feed.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.allsouls.newsapp.feed.presentation.FeedModel
import com.allsouls.newsapp.feed.presentation.FeedState


@Composable
fun FeedUi(model: FeedModel) {
    val state: FeedState by model.updates().observeAsState(FeedState.empty())

    when {
        state.loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
        state.isRenderable() -> HeadlinesList(state.headlines)
        else -> Text(text = "Something went wrong!")
    }
}