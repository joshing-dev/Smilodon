package com.matrix159.mastadonclone.shared.mvi


interface ActionDispatcher<in A> {
  fun dispatchAction(action: A)
}

//interface EffectDispatcher<in E> {
//  fun dispatchEffect(effect: E)
//}
//
//interface Dispatcher<A, E> : ActionDispatcher<A>, EffectDispatcher<E>


interface SuspendingEffectDispatcher<E> {
  suspend fun dispatchEffect(effect: E)
}

interface SuspendingDispatcher<A, E> : ActionDispatcher<A>, SuspendingEffectDispatcher<E>
