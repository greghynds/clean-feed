package com.allsouls.newsapp.arch.di

import com.allsouls.newsapp.arch.async.AndroidDispatchers
import com.allsouls.newsapp.arch.async.Dispatchers
import org.koin.dsl.module

val asyncModule = module {
    single<Dispatchers> { AndroidDispatchers() }
}