package gwh.xyz.feed.headline.presentation

import gwh.xyz.core.arch.presentation.formatDate
import gwh.xyz.feed.headline.domain.entity.Headline

class HeadlineCardPresenter(private val view: HeadlineCardView) {

    fun bind(headline: Headline) {
        view.setTitle(headline.headline)
        view.setDate(formatDate(headline.updated))
    }
}