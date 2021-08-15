package com.allsouls.newsapp.arch.presentation

import android.util.Log
import com.allsouls.newsapp.AppState
import xyz.gwh.redux.Middleware


fun logging(): Middleware<AppState> = {
    { action ->
        Log.d("Redux", "Dispatching action: ${action.type}")
        action
    }
}