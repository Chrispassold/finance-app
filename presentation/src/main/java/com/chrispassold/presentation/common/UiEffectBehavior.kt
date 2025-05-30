package com.chrispassold.presentation.common

import com.chrispassold.core.appLogger
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Defines a behavior for UI components (typically ViewModels) to send one-time "effects"
 * or events to the UI layer (e.g., Composables, Activities, Fragments).
 *
 * Effects are distinct from UI state. While UI state represents the data to be displayed
 * at any given time, effects represent transient events that should be consumed once,
 * such as showing a Snackbar, navigating to another screen, or triggering a specific animation.
 *
 * This interface uses a Kotlin [Flow] to expose the stream of effects.
 *
 * @param T The type of the effect that will be emitted.
 */
internal interface UiEffectBehavior<T> {
    /**
     * A [Flow] that emits UI effects of type [T].
     * UI components can collect this flow to react to one-time events.
     */
    val effect: Flow<T>

    /**
     * Sends a new effect of type [T] to be collected by observers of the [effect] Flow.
     * This method should be called from a coroutine scope.
     *
     * @param effect The effect object to send.
     */
    suspend fun sendEffect(effect: T)
}

/**
 * A concrete implementation of [UiEffectBehavior] using a [Channel] to manage
 * the emission and reception of UI effects.
 *
 * [Channel] is used here because it's well-suited for hot streams of events where
 * each event is typically consumed by a single collector (or if multiple collectors,
 * they all get the event if collected in time, depending on channel type).
 * For UI effects, we usually want the event to be processed once by the current UI observer.
 * Using `receiveAsFlow()` on a channel helps ensure this behavior.
 *
 * @param T The type of the effect that will be handled.
 */
internal class UiEffectBehaviorImpl<T> : UiEffectBehavior<T> {
    /**
     * The underlying [Channel] used to send and receive effects.
     * It's configured as a rendezvous channel by default (no buffering), meaning `send` will suspend
     * until `receive` is called. Other channel types (e.g., `Channel.CONFLATED`, `Channel.BUFFERED`)
     * could be used depending on the desired behavior for unconsumed effects.
     */
    private val _effect = Channel<T>(Channel.CONFLATED)

    /**
     * Exposes the [Channel] as a cold [Flow] for collecting effects.
     * Each collector will start receiving effects from the point they start collecting.
     */
    override val effect: Flow<T> = _effect.receiveAsFlow()

    /**
     * Sends an effect into the channel. This call will suspend if the channel's buffer
     * (if any) is full, until there is a collector ready to receive.
     *
     * @param effect The effect to be sent.
     */
    override suspend fun sendEffect(effect: T) {
        effect?.let { appLogger.d("sendEffect: ${it::class.simpleName}") }
        _effect.send(effect)
    }
}