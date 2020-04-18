package gwh.xyz.feed.domain.entity

import gwh.xyz.feed.headline.domain.entity.Headline

data class Feed(
    val headlines: List<Headline>
)