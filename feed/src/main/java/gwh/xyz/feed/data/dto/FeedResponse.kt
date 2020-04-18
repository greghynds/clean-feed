package gwh.xyz.feed.data.dto

import gwh.xyz.feed.headline.data.dto.HeadlineDto
import com.fasterxml.jackson.annotation.JsonProperty

data class FeedResponse(
    @JsonProperty("headlines") val headlines: List<HeadlineDto>
)