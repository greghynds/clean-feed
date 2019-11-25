package com.allsouls.newsapp.feed.data.dto

import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.fasterxml.jackson.annotation.JsonProperty

data class FeedResponse(
    @JsonProperty("headlines") val headlines: List<HeadlineDto>
)