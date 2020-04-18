package gwh.xyz.tracking.data

import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.TrackEvent
import gwh.xyz.core.arch.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify

class TrackingServiceTest {

    @Test
    fun `should send tracking request when tracking event`() {
        runBlocking {
            val event = Event.NetworkRequest(100)
            val trackEvent = mock<TrackEvent>()
            val sut = TrackingService(trackEvent, TestDispatchers())

            sut.track(event)

            verify(trackEvent).send(event)
        }
    }
}