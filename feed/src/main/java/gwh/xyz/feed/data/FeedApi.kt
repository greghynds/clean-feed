package gwh.xyz.feed.data

import gwh.xyz.core.arch.data.toResult
import gwh.xyz.feed.domain.FeedRepo
import gwh.xyz.feed.domain.entity.Feed

class FeedApi(private val client: FeedClient) : FeedRepo {

    override suspend fun feed(): Result<Feed> {
        return client.feed()
            .await()
            .toResult()
            .map { response -> response.toEntity() }
    }
}