package gwh.xyz.feed.headline.data

import gwh.xyz.core.arch.presentation.dateFromTimestamp
import gwh.xyz.feed.headline.data.dto.HeadlineDto
import gwh.xyz.feed.headline.domain.entity.Headline

fun HeadlineDto.toEntity(): Headline {
    return Headline(
        headline,
        dateFromTimestamp(updated),
        introduction
    )
}