package gwh.xyz.tracking.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AnalyticsClient {

    @GET(ANALYTICS_ENDPOINT)
    fun track(@QueryMap params: Map<String, String>): Deferred<Response<Unit>>
}