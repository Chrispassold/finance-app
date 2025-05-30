package com.chrispassold.presentation.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias ViewStateFlow<T> = StateFlow<ViewState<T>>

fun <T> mutableViewStateFlow(value: ViewState<T>): MutableStateFlow<ViewState<T>> =
    MutableStateFlow<ViewState<T>>(value)

sealed interface ViewState<T> {
    object Loading : ViewState<Nothing>
    data class Success<T>(val data: T) : ViewState<T>
    data class Error<T>(val message: String) : ViewState<T>
}