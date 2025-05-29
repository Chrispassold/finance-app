package com.chrispassold.presentation.common

internal interface UiEventBehavior<T> {
    fun onEvent(event: T)
}