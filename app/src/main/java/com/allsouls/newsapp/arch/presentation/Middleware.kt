package com.allsouls.newsapp.arch.presentation

import android.util.Log
import com.allsouls.newsapp.AppState
import com.github.greghynds.redux.Middleware


fun createLoggingMiddleware(): Middleware<AppState> = {
    { action ->
        Log.d("Redux", "Dispatching action: ${action.type}")
        action
    }
}