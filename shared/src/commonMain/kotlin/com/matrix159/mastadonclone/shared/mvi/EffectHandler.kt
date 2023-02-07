package com.matrix159.mastadonclone.shared.mvi

typealias EffectHandler<S, A, E> = suspend (state: S, effect: E, suspendingDispatcher: SuspendingDispatcher<A, E>) -> Unit