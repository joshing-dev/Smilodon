package com.matrix159.mastadonclone.shared.mvitests.screens.login

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.fakes.FakeRepository
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenAction
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenState
import com.matrix159.mastadonclone.shared.mvi.screens.login.loginStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenTests : KoinTest {

  lateinit var store: Store
  @BeforeTest
  fun before() {
    store = loginStore
    startKoin {
      //modules
      module {
        factory<Repository> { FakeRepository() }
      }
    }
  }

  @AfterTest
  fun after() {
    stopKoin()
  }

  @Test
  fun testInitialLoginScreenState() = runTest {
    val store = loginStore
    assertEquals(LoginScreenState.BaseState(), store.state.value)
  }

  @Test
  fun testServerUrlUpdatedAction() = runTest {
    val store = loginStore
    val serverUrl = "androiddev.social"
    store.dispatchAction(LoginScreenAction.ServerUrlUpdated(serverUrl))
    assertEquals(
      LoginScreenState.BaseState(
        serverUrl,
        serverTitle = null,
        serverDescription = null
      ), store.state.value
    )
  }

  @Test
  fun testServerFoundAction() = runTest {
    val store = loginStore
    val serverTitle = "Android Dev"
    val serverDescription = "Android Dev Server"
    store.dispatchAction(
      LoginScreenAction.ServerFound(
        serverTitle = serverTitle,
        serverDescription = serverDescription
      )
    )
    assertEquals(
      LoginScreenState.BaseState(
        serverTitle = serverTitle,
        serverDescription = serverDescription
      ),
      store.state.value
    )
  }
}
