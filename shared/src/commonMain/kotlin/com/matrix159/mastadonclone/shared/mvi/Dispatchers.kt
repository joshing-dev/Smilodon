package com.matrix159.mastadonclone.shared.mvi


interface ActionDispatcher<in A> {
  fun dispatchAction(action: A)
}

interface SuspendingEffectDispatcher<E> {
  suspend fun dispatchEffect(effect: E)
}

interface SuspendingDispatcher<A, E> : ActionDispatcher<A>, SuspendingEffectDispatcher<E>
