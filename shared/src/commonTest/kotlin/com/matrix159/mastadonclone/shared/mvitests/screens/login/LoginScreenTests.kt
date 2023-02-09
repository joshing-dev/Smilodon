package com.matrix159.mastadonclone.shared.mvitests.screens.login

import com.matrix159.mastadonclone.shared.mvi.Store
import com.matrix159.mastadonclone.shared.mvi.screens.login.loginStore
import com.matrix159.mastadonclone.shared.mvitests.Action
import com.matrix159.mastadonclone.shared.mvitests.Effect
import com.matrix159.mastadonclone.shared.mvitests.actionReducer
import com.matrix159.mastadonclone.shared.mvitests.effectHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class LoginScreenTests {

//  @Test
//  fun testActionsAndEffect() = runTest {
//    val store = loginStore
//    assertEquals("foo", store.state.value)
//
//    store.dispatchAction(Action.Uppercase)
//    assertEquals("FOO", store.state.value)
//
//    store.dispatchAction(Action.Lowercase)
//    assertEquals("foo", store.state.value)
//
//    store.dispatchEffect(Effect.DelayedAction(2000, Action.Reverse))
//    assertEquals("oof", store.state.value)
//  }
//
//  @Test
//  fun testLaunch() = runTest {
//    val store = Store("foo", actionReducer, effectHandler)
//
//    launch {
//      store.dispatchEffect(Effect.DelayedAction(2000, Action.Reverse))
//      assertEquals("oof", store.state.value) // this is in same coroutine as the effect, so this will run after 2000ms
//    }
//    assertNotEquals("oof", store.state.value) // this runs directly after launching the coroutine, so it will still be "foo"
//    advanceUntilIdle() // a convenience function that waits until all tasks are completed
//    assertEquals("oof", store.state.value) // all tasks in the coroutine are completed and the value is reversed
//  }
}
