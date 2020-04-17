package com.allsouls.newsapp.feed.domain

import com.allsouls.newsapp.arch.domain.InputPort
import com.allsouls.newsapp.arch.domain.OutputPort
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.feed.domain.entity.Feed
import kotlin.Result.Companion.failure

class FetchFeed(
    private val repo: FeedRepo,
    private val output: OutputPort<Feed>
) : InputPort<Params> {

    override suspend fun send(params: Params) {
        try {
            output.receive(repo.feed())
        } catch (error: Throwable) {
            output.receive(failure(error))
        }
    }
}