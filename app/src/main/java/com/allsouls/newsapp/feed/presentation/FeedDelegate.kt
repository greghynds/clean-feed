package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.headline.domain.entity.Headline

interface FeedDelegate {
    fun selectHeadline(headline: Headline)
    fun refresh()
}