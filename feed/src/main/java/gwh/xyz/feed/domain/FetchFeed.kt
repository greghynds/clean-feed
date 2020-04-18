package gwh.xyz.feed.domain

import gwh.xyz.core.arch.domain.InputPort
import gwh.xyz.core.arch.domain.OutputPort
import gwh.xyz.core.arch.domain.Params
import gwh.xyz.feed.domain.entity.Feed
import kotlin.Result.Companion.failure

typealias FetchFeedPort = InputPort<Params>

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