package com.allsouls.newsapp.headline.data

import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.allsouls.newsapp.headline.domain.entity.Headline
import java.util.*

fun HeadlineDto.toEntity(): Headline {
    return Headline(
        headline,
        Date(updated),
        introduction
    )
}