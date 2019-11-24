package com.allsouls.newsapp.tracking.data

import com.allsouls.newsapp.arch.data.toResult
import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.Tracker

class AnalyticsApi(private val client: AnalyticsClient) : Tracker {

    override suspend fun track(event: Event): Result<Unit> {
        return client.track(event.toMap())
            .await()
            .toResult()
    }
}