package gwh.xyz.feed.adapter

import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.TrackEventPort
import gwh.xyz.core.arch.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FeedTrackingControllerTest {

    @Test
    fun `tracks screen view when resuming`() {
        runBlocking {
            val event = Event.Display("feed")
            val inputPort = mock<TrackEventPort>()
            val sut = FeedTrackingController(inputPort, TestDispatchers())

            sut.resume()

            verify(inputPort).send(event)
        }
    }
}