package com.allsouls.newsapp.tracking.domain

import com.allsouls.newsapp.arch.domain.UseCase

class TrackEvent(private val tracker: Tracker) : UseCase<Event, Unit>() {

    override suspend fun operation(params: Event): Result<Unit> {
        return tracker.track(params)
    }
}