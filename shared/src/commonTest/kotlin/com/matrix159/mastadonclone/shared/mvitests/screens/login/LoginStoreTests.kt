package com.matrix159.mastadonclone.shared.mvitests.screens.login

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState
import com.matrix159.mastadonclone.shared.fakes.FakeRepository
import com.matrix159.mastadonclone.shared.mvi.app.AppState
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenAction
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenEffect
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenState
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginStoreTests : KoinTest {

  private lateinit var appStore: AppStore
  private lateinit var loginStore: LoginStore
  private lateinit var repository: Repository

  @BeforeTest
  fun before() {
    startKoin {
      //modules
      modules(
        module {
          single { AppStore() }
          single { LoginStore(get()) }
          // Repository is single for tests so that multiple instances aren't created between the test
          // and the store's usage
          single<Repository> { FakeRepository() }
        }
      )
    }
    appStore = get()
    loginStore = get()
    repository = get()
  }

  @AfterTest
  fun after() {
    stopKoin()
  }

  // ------ ACTIONS ------
  @Test
  fun testInitialLoginScreenState() = runTest {
    assertEquals(LoginScreenState.BaseState(), loginStore.state.value)
  }

  @Test
  fun testServerUrlUpdatedAction() = runTest {
    val serverUrl = "androiddev.social"
    loginStore.dispatchAction(LoginScreenAction.ServerUrlUpdated(serverUrl))
    assertEquals(
      LoginScreenState.BaseState(
        serverUrl,
        serverTitle = null,
        serverDescription = null
      ), loginStore.state.value
    )
  }

  @Test
  fun testServerFoundAction() = runTest {
    val serverTitle = "Android Dev"
    val serverDescription = "Android Dev Server"
    loginStore.dispatchAction(
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
      loginStore.state.value
    )
  }

  // ------ EFFECTS ------

  @Test
  fun testLoginEffect() = runTest {
    val serverUrl = "androiddev.social"
    val clientApplication = repository.getClientApplication(serverUrl)
    loginStore.dispatchEffect(LoginScreenEffect.Login(serverUrl))
    advanceUntilIdle()
    assertEquals(
      AppState.Authenticating(
        userServerUrl = serverUrl,
        clientId = clientApplication.clientId,
        clientSecret = clientApplication.clientSecret,
        redirectUri = clientApplication.redirectUri
      ),
      appStore.state.value
    )
    // Assert app state was saved while going into authenticating state
    assertEquals(
      SettingsAppState(
        userServerUrl = serverUrl,
        clientId = clientApplication.clientId,
        clientSecret = clientApplication.clientSecret,
        redirectUri = clientApplication.redirectUri
      ),
      repository.getSavedAppState()
    )
  }

  @Test
  fun testSearchForServer() = runTest {
    // TODO: Figure out how the loginstore is getting shared between tests still
    val serverUrl = "androiddev.social"
    loginStore.dispatchEffect(LoginScreenEffect.SearchForServer(serverUrl))
    //advanceUntilIdle()
  }
}
