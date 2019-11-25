package com.allsouls.newsapp.headline.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class HeadlineDto(
    @JsonProperty("headline") val headline: String,
    @JsonProperty("updated") val updated: Long,
    @JsonProperty("introduction") val introduction: String
)