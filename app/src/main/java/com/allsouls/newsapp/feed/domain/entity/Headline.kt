package com.allsouls.newsapp.feed.domain.entity

import java.util.Date

data class Headline(
    val headline: String,
    val updated: Date,
    val introduction: String
)