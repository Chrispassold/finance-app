package com.chrispassold.presentation.common

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal fun <T> MutableStateFlow<ViewState<T>>.loading() {
    update { ViewState.Loading }
}

internal fun <T> MutableStateFlow<ViewState<T>>.success(value: T) {
    update { ViewState.Success(value) }
}

internal fun <T> MutableStateFlow<ViewState<T>>.error(
    exception: Throwable,
    message: String? = null,
) {
    update { ViewState.Error(exception, message ?: exception.message ?: "Unknown error message") }
}

internal fun <T> MutableStateFlow<ViewState<T>>.transformData(transform: T.() -> T) {
    update {
        when (it) {
            is ViewState.Success -> ViewState.Success(it.data.transform())
            else -> it
        }
    }
}

internal fun <T> mutableViewStateFlow(value: ViewState<T>): MutableStateFlow<ViewState<T>> =
    MutableStateFlow<ViewState<T>>(value)

sealed interface ViewState<out T> {
    @Stable
    object Loading : ViewState<Nothing>

    @Stable
    data class Success<T>(val data: T) : ViewState<T>

    @Stable
    data class Error<T>(val exception: Throwable, val message: String) : ViewState<T> {
        constructor(exception: Throwable) : this(
            exception,
            exception.message ?: "Unknown error message",
        )
    }

    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error
}