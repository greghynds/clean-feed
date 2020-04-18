package gwh.xyz.feed.di

import gwh.xyz.core.arch.data.createApiClient
import gwh.xyz.feed.data.FeedApi
import gwh.xyz.feed.data.FeedClient
import gwh.xyz.feed.domain.FeedRepo
import gwh.xyz.feed.headline.presentation.HeadlineCardPresenter
import gwh.xyz.feed.headline.presentation.HeadlineCardView
import gwh.xyz.feed.headline.presentation.HeadlinePresenter
import gwh.xyz.feed.headline.presentation.HeadlineView
import org.koin.dsl.module

val feedModule = module {
    single<FeedClient> { createApiClient(get()) }
    single<FeedRepo> { FeedApi(get()) }
    factory { (view: HeadlineCardView) -> HeadlineCardPresenter(view) }
    factory { (view: HeadlineView) -> HeadlinePresenter(view) }
}