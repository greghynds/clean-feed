package gwh.xyz.tracking.domain

interface Tracker {
    suspend fun track(event: Event): Result<Unit>
}