package com.matrix159.mastadonclone.shared.mvitests

import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.Store
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

sealed interface Action {
  object Uppercase : Action
  object Lowercase: Action
  object Reverse: Action
}

sealed interface Effect {
  data class DelayedAction(val delay: Long, val action: Action) : Effect
}

private val actionReducer: ActionReducer<String, Action> = { state, action ->
  when (action) {
    Action.Uppercase -> state.uppercase()
    Action.Lowercase -> state.lowercase()
    Action.Reverse -> state.reversed()
  }
}

private val effectHandler: EffectHandler<String, Action, Effect> = { _, effect, dispatcher ->
  when (effect) {
    is Effect.DelayedAction -> {
      delay(effect.delay) // we are in a suspend function so we can use delay
      dispatcher.dispatchAction(effect.action)
    }
  }
}

@OptIn(ExperimentalCoroutinesApi::class)
class ActionsAndEffectsTests {

  @Test
  fun testActionsAndEffect() = runTest {
    val store = Store("foo", actionReducer, effectHandler)
    assertEquals("foo", store.state.value)

    store.dispatchAction(Action.Uppercase)
    assertEquals("FOO", store.state.value)

    store.dispatchAction(Action.Lowercase)
    assertEquals("foo", store.state.value)

    store.dispatchEffect(Effect.DelayedAction(2000, Action.Reverse))
    assertEquals("oof", store.state.value)
  }

  @Test
  fun testLaunch() = runTest {
    val store = Store("foo", actionReducer, effectHandler)

    launch {
      store.dispatchEffect(Effect.DelayedAction(2000, Action.Reverse))
      assertEquals("oof", store.state.value) // this is in same coroutine as the effect, so this will run after 2000ms
    }
    assertNotEquals("oof", store.state.value) // this runs directly after launching the coroutine, so it will still be "foo"
    advanceUntilIdle() // a convenience function that waits until all tasks are completed
    assertEquals("oof", store.state.value) // all tasks in the coroutine are completed and the value is reversed
  }
}