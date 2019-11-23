package com.allsouls.newsapp.feed.data

import com.allsouls.newsapp.feed.data.dto.FeedResponse
import com.allsouls.newsapp.feed.data.dto.HeadlineDto
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.domain.entity.Headline
import java.util.Date

fun FeedResponse.toEntity(): Feed {
    return Feed(headlines.map { dto -> dto.toEntity() })
}

fun HeadlineDto.toEntity(): Headline {
    return Headline(
        headline,
        Date(updated),
        introduction
    )
}