package gwh.xyz.feed.data

import gwh.xyz.core.arch.presentation.dateFromTimestamp
import gwh.xyz.feed.data.dto.FeedResponse
import gwh.xyz.feed.domain.entity.Feed
import gwh.xyz.feed.headline.data.dto.HeadlineDto
import gwh.xyz.feed.headline.domain.entity.Headline
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FeedAdaptersTest {

    @Test
    fun `maps all fields when converting feed response to feed entity`() {
        val text = "headline"
        val updateDateTs = 1448401928L
        val updateDate = dateFromTimestamp(updateDateTs)
        val introduction = "introduction"
        val headline = Headline(text, updateDate, introduction)
        val dto = HeadlineDto(text, updateDateTs, introduction)
        val feed = Feed(listOf(headline))
        val sut = FeedResponse(listOf(dto))

        val result = sut.toEntity()

        assertThat(result).isEqualToComparingFieldByField(feed)
    }
}