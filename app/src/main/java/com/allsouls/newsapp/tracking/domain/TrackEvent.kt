package com.allsouls.newsapp.tracking.domain

import com.allsouls.newsapp.arch.domain.InputPort

typealias TrackEventPort = InputPort<Event>

class TrackEvent(private val tracker: Tracker) : TrackEventPort {

    override suspend fun send(params: Event) {
        tracker.track(params)
    }
}