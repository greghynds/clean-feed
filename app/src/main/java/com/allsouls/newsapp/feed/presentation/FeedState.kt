package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.headline.domain.entity.Headline

data class FeedState(
    val headlines: List<Headline>,
    val error: Throwable? = null
) {
    fun isRenderable(): Boolean = error == null
}