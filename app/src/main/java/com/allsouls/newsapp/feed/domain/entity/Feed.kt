package com.allsouls.newsapp.feed.domain.entity

import com.allsouls.newsapp.headline.domain.entity.Headline

data class Feed(
    val headlines: List<Headline>
)