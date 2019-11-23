package com.allsouls.newsapp.feed.domain

import com.allsouls.newsapp.feed.domain.entity.Feed

interface FeedRepo {
    suspend fun feed(): Result<Feed>
}