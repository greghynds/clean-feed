package com.allsouls.newsapp.tracking.data

import com.allsouls.newsapp.tracking.domain.Event

const val KEY_TYPE = "event"
const val KEY_SCREEN = "screen"
const val KEY_TIME = "time"

fun Event.toMap(): Map<String, String> {
    val map = mutableMapOf(KEY_TYPE to key)

    when (this) {
        is Event.Display -> map[KEY_SCREEN] = screen
        is Event.NetworkRequest -> map[KEY_TIME] = "$time"
    }

    return map
}