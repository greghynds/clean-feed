package gwh.xyz.cleanfeed

import gwh.xyz.feed.di.feedModule
import gwh.xyz.core.arch.di.asyncModule
import gwh.xyz.core.arch.di.networkModule
import gwh.xyz.core.arch.di.serializationModule
import gwh.xyz.tracking.di.trackingModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

object Dependencies {

    private val modules = listOf(
        asyncModule,
        serializationModule,
        trackingModule,
        networkModule,
        feedModule
    )

    fun inject() {
        startKoin { modules(modules) }
    }
}