package com.allsouls.newsapp.arch.di

import com.allsouls.newsapp.arch.presentation.AndroidDispatchers
import com.allsouls.newsapp.arch.presentation.Dispatchers
import org.koin.dsl.module

val asyncModule = module {
    single<Dispatchers> { AndroidDispatchers() }
}