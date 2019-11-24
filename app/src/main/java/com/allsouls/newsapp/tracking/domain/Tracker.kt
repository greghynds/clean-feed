package com.allsouls.newsapp.tracking.domain

interface Tracker {
    suspend fun track(event: Event): Result<Unit>
}