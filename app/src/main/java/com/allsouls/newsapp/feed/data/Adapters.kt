package com.allsouls.newsapp.feed.data

import com.allsouls.newsapp.feed.data.dto.FeedResponse
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.data.toEntity

fun FeedResponse.toEntity(): Feed {
    return Feed(headlines.map { dto -> dto.toEntity() })
}