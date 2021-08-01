package com.allsouls.newsapp.feed.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.allsouls.newsapp.feed.presentation.FeedModel
import com.allsouls.newsapp.feed.presentation.FeedState
import com.allsouls.newsapp.headline.domain.entity.Headline

@Composable
fun HeadlinesList(headlines: List<Headline>) {
    LazyColumn {
        headlines.forEach { headline ->
            item {
                Text(text = headline.headline)
            }
        }
    }
}

@Composable
fun FeedScreen(model: FeedModel) {
    val state: FeedState by model.updates().observeAsState(FeedState(listOf()))

    when {
        state.isRenderable() -> HeadlinesList(state.headlines)
        else -> Text(text = "You done fucked up.")
    }
}