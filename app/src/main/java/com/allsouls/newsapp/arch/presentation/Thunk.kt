package com.allsouls.newsapp.arch.presentation

import com.allsouls.newsapp.arch.async.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.gwh.redux.Action
import xyz.gwh.redux.Middleware
import xyz.gwh.redux.Store


fun <State> createThunk(
    type: String,
    dispatchers: Dispatchers,
    block: suspend (Store<State>) -> Action
): Middleware<State> = { store ->
    { action ->
        when {
            action.isOfType(type) -> CoroutineScope(dispatchers.main).launch {
                store.dispatch(withContext(dispatchers.io) { block(store) })
            }
        }
        action
    }
}