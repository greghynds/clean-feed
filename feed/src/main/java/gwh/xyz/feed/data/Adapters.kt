package gwh.xyz.feed.data

import gwh.xyz.feed.data.dto.FeedResponse
import gwh.xyz.feed.domain.entity.Feed
import gwh.xyz.feed.headline.data.toEntity

fun FeedResponse.toEntity(): Feed {
    return Feed(headlines.map { dto -> dto.toEntity() })
}