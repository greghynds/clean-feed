package com.allsouls.newsapp.feed.data

import com.allsouls.newsapp.arch.data.toResult
import com.allsouls.newsapp.feed.domain.FeedRepo
import com.allsouls.newsapp.feed.domain.entity.Feed

class FeedApi(private val client: FeedClient) : FeedRepo {

    override suspend fun feed(): Result<Feed> {
        return client.feed()
            .await()
            .toResult()
            .map { response -> response.toEntity() }
    }
}