package com.chrispassold.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> resultWithContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T,
): Result<T> {
    return runCatching {
        withContext(context, block)
    }.onFailure {
        appLogger.e(it, "Error executing use case")
    }
}