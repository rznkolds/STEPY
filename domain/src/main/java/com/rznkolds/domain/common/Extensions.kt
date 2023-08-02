package com.rznkolds.domain.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// FLOW EXTENSIONS

fun <T> Flow<T>.collect(scope: CoroutineScope, function: (T) -> Unit) {
    scope.launch {
        collect {
            function(it)
        }
    }
}

