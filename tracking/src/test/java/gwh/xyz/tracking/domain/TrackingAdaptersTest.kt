package gwh.xyz.tracking.domain

import gwh.xyz.tracking.data.toMap
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TrackingAdaptersTest {

    @Test
    fun `should return params map when converting network request event`() {
        val time = 100
        val map = mapOf("event" to "load", "time" to "$time")
        val sut = Event.NetworkRequest(time)

        val result = sut.toMap()

        assertThat(result).isEqualTo(map)
    }

    @Test
    fun `should return params map when converting display screen event`() {
        val screen = "xxx"
        val map = mapOf("event" to "display", "screen" to screen)
        val sut = Event.Display(screen)

        val result = sut.toMap()

        assertThat(result).isEqualTo(map)
    }
}