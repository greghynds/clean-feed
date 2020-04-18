package gwh.xyz.tracking.domain

import gwh.xyz.core.arch.domain.InputPort

typealias TrackEventPort = InputPort<Event>

class TrackEvent(private val tracker: Tracker) : TrackEventPort {

    override suspend fun send(params: Event) {
        tracker.track(params)
    }
}