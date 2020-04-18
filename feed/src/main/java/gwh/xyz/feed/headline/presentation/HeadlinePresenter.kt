package gwh.xyz.feed.headline.presentation

import gwh.xyz.core.arch.presentation.formatDate
import gwh.xyz.feed.headline.domain.entity.Headline

class HeadlinePresenter(private val view: HeadlineView) {

    fun render(headline: Headline) {
        view.setTitle(headline.headline)
        view.setDate(formatDate(headline.updated))
        view.setIntroduction(headline.introduction)
    }
}