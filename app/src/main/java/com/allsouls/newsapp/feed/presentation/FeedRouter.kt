package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.headline.domain.entity.Headline

interface FeedRouter {
    fun navigateToHeadline(headline: Headline)
}