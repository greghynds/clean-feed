package gwh.xyz.feed.headline.presentation

import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.TrackEventPort
import gwh.xyz.core.arch.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class HeadlineControllerTest {

    @Test
    fun `tracks screen view when resumed`() {
        runBlocking {
            val event = Event.Display("headline")
            val trackEvent = mock<TrackEventPort>()
            val sut = HeadlineController(trackEvent, TestDispatchers())

            sut.resume()

            verify(trackEvent).send(event)
        }
    }
}