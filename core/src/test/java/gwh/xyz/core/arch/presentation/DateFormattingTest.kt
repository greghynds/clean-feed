package gwh.xyz.core.arch.presentation

import gwh.xyz.core.arch.presentation.dateFromTimestamp
import gwh.xyz.core.arch.presentation.formatDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.Date

class DateFormattingTest {

    @Test
    fun `should create date from unix timestamp`() {
        val timestamp = 1448401928L
        val date = Date().apply { time = timestamp * 1000 }

        val result = dateFromTimestamp(timestamp)

        assertThat(result).isEqualTo(date)
    }

    @Test
    fun `should format date to readable string`() {
        val timestamp = 1448401928L
        val date = dateFromTimestamp(timestamp)

        val result = formatDate(date)

        assertThat(result).isEqualTo("24 November, 2015")
    }
}