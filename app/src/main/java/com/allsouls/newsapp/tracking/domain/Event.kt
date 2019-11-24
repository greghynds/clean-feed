package com.allsouls.newsapp.tracking.domain

const val TYPE_DISPLAY = "display"
const val TYPE_LOAD = "load"

sealed class Event(val key: String) {
    class Display(val screen: String) : Event(TYPE_DISPLAY)
    class NetworkRequest(val time: Int) : Event(TYPE_LOAD)
}