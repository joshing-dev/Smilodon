package com.matrix159.mastadonclone.shared.mvi

import kotlinx.coroutines.flow.StateFlow

typealias EffectHandler<S, A, E> = suspend (state: S, effect: E, suspendingDispatcher: SuspendingDispatcher<A, E>) -> Unit
//interface EffectHandler<S, A, E> {
//  @Suppress("ClassCastException")
//  suspend fun handleEffect(
//    state: StateFlow<S>,
//    effect: E,
//    suspendingDispatcher: SuspendingDispatcher<A, E>
//  )
//}