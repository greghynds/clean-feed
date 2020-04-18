package gwh.xyz.feed.adapter

import gwh.xyz.core.arch.domain.InputPort
import gwh.xyz.core.arch.domain.Params
import gwh.xyz.core.arch.domain.Params.None
import gwh.xyz.core.arch.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify

class FeedControllerTest {

    @Test
    fun `sends params to input port when fetching feed`() {
        runBlocking {
            val inputPort = mock<InputPort<Params>>()
            val sut = FeedController(inputPort, TestDispatchers())

            sut.fetchFeed()

            verify(inputPort).send(params = None)
        }
    }
}