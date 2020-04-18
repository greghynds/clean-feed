package gwh.xyz.tracking.data

import gwh.xyz.tracking.domain.Event.NetworkRequest
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.koin.core.KoinComponent
import org.koin.core.inject

class TrackingInterceptor : Interceptor, KoinComponent {

    private val tracking: TrackingService by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(request)
        val endTime = System.currentTimeMillis()
        val body = response.body()
        val endpoint = response.request().url().pathSegments().last()
        val bodyString = response.body()?.string()
        val newBody = ResponseBody.create(body?.contentType(), bodyString?.toByteArray())
        val newResponse = response.newBuilder().body(newBody).build()
        val isTrackable = endpoint != ANALYTICS_ENDPOINT

        if (isTrackable) {
            val time = calculateResponseTime(startTime, endTime)
            val event = NetworkRequest(time)
            tracking.track(event)
        }

        return newResponse
    }

    private fun calculateResponseTime(startTime: Long, endTime: Long): Int {
        return (endTime - startTime).toInt()
    }
}
