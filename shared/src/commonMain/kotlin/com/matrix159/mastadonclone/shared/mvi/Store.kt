package com.matrix159.mastadonclone.shared.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

internal class StoreImpl<S, A, E>(
  initialState: S,
  private val handleAction: ActionReducer<S, A>,
  private val handleEffect: EffectHandler<S, A, E>,
) : Store<S, A, E> {

  private val _state = MutableStateFlow(initialState)
  override val state = _state.asStateFlow()

  override fun dispatchAction(action: A) {
    _state.update { state -> handleAction(state, action) }
  }

  override suspend fun dispatchEffect(effect: E) {
    handleEffect(state.value, effect, this@StoreImpl)
  }
}

interface Store<S, A, E> : SuspendingDispatcher<A, E> {
  val state: StateFlow<S>

  companion object {
    operator fun <S, A, E> invoke(
      initialState: S,
      actionReducer: ActionReducer<S, A>,
      effectHandler: EffectHandler<S, A, E>
    ): Store<S, A, E> = StoreImpl(initialState, actionReducer, effectHandler)
  }
}
