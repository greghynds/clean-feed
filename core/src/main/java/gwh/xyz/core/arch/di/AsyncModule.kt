package gwh.xyz.core.arch.di

import gwh.xyz.core.arch.presentation.AndroidDispatchers
import gwh.xyz.core.arch.presentation.Dispatchers
import org.koin.dsl.module

val asyncModule = module {
    single<Dispatchers> { AndroidDispatchers() }
}