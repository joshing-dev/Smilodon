package com.matrix159.mastadonclone.shared.mvitests.app

import app.cash.turbine.test
import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState
import com.matrix159.mastadonclone.shared.fakes.FakeRepository
import com.matrix159.mastadonclone.shared.mvi.app.AppAction
import com.matrix159.mastadonclone.shared.mvi.app.AppEffect
import com.matrix159.mastadonclone.shared.mvi.app.AppState
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AppStoreTests : KoinTest {

  private lateinit var appStore: AppStore
  private lateinit var repository: Repository

  @BeforeTest
  fun before() {
    startKoin {
      //modules
      modules(
        module {
          // Repository is single for tests because it holds Fake state
          single<Repository> { FakeRepository() }
          single { AppStore(repository = get()) }
        }
      )
    }
    appStore = get()
    repository = get()
  }

  @AfterTest
  fun after() {
    stopKoin()
  }

  @Test
  fun testInitialState() = runTest {
    assertEquals(AppState.NotLoggedIn, appStore.state.value)
  }

  // ----- ACTIONS -----

  @Test
  fun testStartAuthenticationAction() = runTest {
    val serverUrl = "asda.asdasd"
    val clientId = "asdasd"
    val clientSecret = "1231g1"
    val redirectUri = "asdasd.agagom"
    appStore.dispatchAction(
      AppAction.StartAuthentication(
        userServerUrl = serverUrl,
        clientId = clientId,
        clientSecret = clientSecret,
        redirectUri = redirectUri
      )
    )
    assertEquals(
      AppState.Authenticating(
        userServerUrl = serverUrl,
        clientId = clientId,
        clientSecret = clientSecret,
        redirectUri = redirectUri
      ),
      appStore.state.value
    )
  }

  @Test
  fun testLoginSuccessAction() = runTest {
    val accessToken = "ahahasdaease"
    appStore.dispatchAction(AppAction.LoginSuccess(accessToken))
    assertEquals(
      AppState.LoggedIn(
        accessToken = accessToken
      ),
      appStore.state.value
    )
  }

  @Test
  fun testLogoutAction() = runTest {
    appStore.dispatchAction(AppAction.Logout)
    assertEquals(
      AppState.NotLoggedIn,
      appStore.state.value
    )
  }

  // ----- EFFECTS -----

  @Test
  fun testSaveAppStateEffect() = runTest {
    // Test saving a logged in state
    appStore.state.test {
      // Skip initial state
      awaitItem()
      val accessToken = "1231231"
      appStore.dispatchAction(AppAction.LoginSuccess(accessToken = accessToken))
      assertEquals(
        AppState.LoggedIn(
          accessToken = accessToken
        ),
        awaitItem()
      )
      appStore.dispatchEffect(AppEffect.SaveAppState)
      assertEquals(
        SettingsAppState(
          accessToken = accessToken
        ),
        repository.getSavedAppState()
      )
    }
    // Test saving an authenticating state
    appStore.state.test {
      val serverUrl = "asda.asdasd"
      val clientId = "asdasd"
      val clientSecret = "1231g1"
      val redirectUri = "asdasd.agagom"
      // Skip initial state
      awaitItem()
      appStore.dispatchAction(
        AppAction.StartAuthentication(
          userServerUrl = serverUrl,
          clientId = clientId,
          clientSecret = clientSecret,
          redirectUri = redirectUri
        )
      )
      assertEquals(
        AppState.Authenticating(
          userServerUrl = serverUrl,
          clientId = clientId,
          clientSecret = clientSecret,
          redirectUri = redirectUri
        ),
        awaitItem()
      )
      appStore.dispatchEffect(AppEffect.SaveAppState)
      assertEquals(
        SettingsAppState(
          userServerUrl = serverUrl,
          clientId = clientId,
          clientSecret = clientSecret,
          redirectUri = redirectUri
        ),
        repository.getSavedAppState()
      )
    }
    // Test saving a not logged in state
    appStore.state.test {
      // Skip initial state
      awaitItem()
      appStore.dispatchAction(AppAction.Logout)
      assertEquals(
        AppState.NotLoggedIn,
        awaitItem()
      )
      appStore.dispatchEffect(AppEffect.SaveAppState)
      assertEquals(
        SettingsAppState(),
        repository.getSavedAppState()
      )
    }
  }

  @Test
  fun testStartupEffect_AlreadyLoggedIn() = runTest {
    val accessToken = "asdasd"
    val userServerUrl = "androiddev.social"
    repository.saveAppState(
      SettingsAppState(
        accessToken = accessToken,
        userServerUrl = userServerUrl
      )
    )
    appStore.state.test {
      awaitItem()
      appStore.dispatchEffect(AppEffect.Startup)
      assertEquals(
        AppState.LoggedIn(accessToken),
        awaitItem()
      )
    }
  }

  @Test
  fun testStartupEffect_NotLoggedIn() = runTest {
    appStore.state.test {
      awaitItem()
      // Do this so we weren't in an already logged out state
      appStore.dispatchAction(AppAction.LoginSuccess("asdasd"))
      awaitItem()
      appStore.dispatchEffect(AppEffect.Startup)
      assertEquals(
        AppState.NotLoggedIn,
        awaitItem()
      )
    }
  }

  @Test
  fun testStartAuthenticationEffect() = runTest {
    val serverUrl = "asda.asdasd"
    val clientId = "asdasd"
    val clientSecret = "1231g1"
    val redirectUri = "asdasd.agagom"
    appStore.state.test {
      awaitItem()
      appStore.dispatchEffect(
        AppEffect.StartAuthentication(
          userServerUrl = serverUrl,
          clientId = clientId,
          clientSecret = clientSecret,
          redirectUri = redirectUri
        )
      )
      assertEquals(
        AppState.Authenticating(
          userServerUrl = serverUrl,
          clientId = clientId,
          clientSecret = clientSecret,
          redirectUri = redirectUri
        ),
        awaitItem()
      )
      assertEquals(
        SettingsAppState(
          userServerUrl = serverUrl,
          clientId = clientId,
          clientSecret = clientSecret,
          redirectUri = redirectUri
        ),
        repository.getSavedAppState()
      )
    }
  }

  @Test
  fun testLoginEffect() = runTest {
    val accessToken = "Asdasda"
    appStore.state.test {
      awaitItem()
      appStore.dispatchEffect(AppEffect.Login(accessToken))
      assertEquals(
        AppState.LoggedIn(accessToken = accessToken),
        awaitItem()
      )
      assertEquals(
        SettingsAppState(
          accessToken = accessToken
        ),
        repository.getSavedAppState()
      )
    }
  }

  @Test
  fun testLogoutEffect() = runTest {
    appStore.state.test {
      awaitItem()
      // Do this so we weren't in an already logged out state
      appStore.dispatchAction(AppAction.LoginSuccess("asdasd"))
      awaitItem()
      appStore.dispatchEffect(AppEffect.Logout)
      assertEquals(
        AppState.NotLoggedIn,
        awaitItem()
      )
      assertEquals(
        SettingsAppState(),
        repository.getSavedAppState()
      )
    }
  }
}
