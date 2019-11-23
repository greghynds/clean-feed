package com.allsouls.newsapp.feed.domain

import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.domain.UseCase
import com.allsouls.newsapp.feed.domain.entity.Feed

class FetchFeed(private val repo: FeedRepo) : UseCase<Params, Feed>() {

    override suspend fun operation(params: Params): Result<Feed> {
        return repo.feed()
    }
}