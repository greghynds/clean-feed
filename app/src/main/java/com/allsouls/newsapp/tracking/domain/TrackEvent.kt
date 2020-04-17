package com.allsouls.newsapp.tracking.domain

import com.allsouls.newsapp.arch.domain.InputPort

class TrackEvent(private val tracker: Tracker) : InputPort<Event> {

    override suspend fun send(params: Event) {
        tracker.track(params)
    }
}