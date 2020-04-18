package gwh.xyz.feed.domain

import gwh.xyz.feed.domain.entity.Feed

interface FeedRepo {
    suspend fun feed(): Result<Feed>
}