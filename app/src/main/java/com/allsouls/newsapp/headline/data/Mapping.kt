package com.allsouls.newsapp.headline.data

import com.allsouls.newsapp.arch.presentation.dateFromTimestamp
import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.allsouls.newsapp.headline.domain.entity.Headline

fun HeadlineDto.toEntity(): Headline {
    return Headline(
        headline,
        dateFromTimestamp(updated),
        introduction
    )
}