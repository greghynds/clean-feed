package gwh.xyz.tracking.data

import gwh.xyz.core.arch.data.toResult
import gwh.xyz.core.BuildConfig
import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.Tracker

class AnalyticsApi(private val client: AnalyticsClient) : Tracker {

    override suspend fun track(event: Event): Result<Unit> {
        if (BuildConfig.DEBUG) {
            println("Analytics: ${event.toMap()}")
        }

        return client.track(event.toMap())
            .await()
            .toResult()
    }
}