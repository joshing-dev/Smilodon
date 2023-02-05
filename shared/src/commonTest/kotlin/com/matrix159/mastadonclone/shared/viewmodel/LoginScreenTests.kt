package com.matrix159.mastadonclone.shared.viewmodel

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.matrix159.mastadonclone.shared.datalayer.MastadonRepository
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.LoginScreenState
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.*
import com.matrix159.mastadonclone.shared.mvi.screens.login.login

@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenTests {

  private lateinit var viewModel: DKMPViewModel
  private lateinit var navigation: Navigation
  private lateinit var stateProvider: StateProvider
  private lateinit var stateManager: StateManager

  private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

  @BeforeTest
  fun before() {
    Logger.setLogWriters(CommonWriter())
    Dispatchers.setMain(testDispatcher)
    viewModel = DKMPViewModel(
      MastadonRepository(
        settings = MapSettings(),
      )
    )
    navigation = viewModel.navigation
    stateProvider = navigation.stateProvider
    stateManager = navigation.stateManager
  }

  @AfterTest
  fun after() {
    Dispatchers.resetMain()
  }

  @Test
  fun testLoginScreen() {
    val screenIdentifier = ScreenIdentifier.get(Screen.LoginScreen, null)
    val screenInitSettings = screenIdentifier.getScreenInitSettings(navigation)
    stateManager.addScreen(screenIdentifier, screenInitSettings)
    val serverName = "This is a test server"
    val serverDescription = "This is a test description"
    stateManager.updateScreen<LoginScreenState> {
      it.copy(serverName = serverName, serverDescription = serverDescription)
    }
    val screenState = stateProvider.get<LoginScreenState>(screenIdentifier)
    assertEquals(serverName, screenState.serverName)
    assertEquals(serverDescription, screenState.serverDescription)
  }

  // TODO
  @Test
  fun testLogin() {
    val screenIdentifier = ScreenIdentifier.get(Screen.LoginScreen, null)
    val screenInitSettings = screenIdentifier.getScreenInitSettings(navigation)
    stateManager.addScreen(screenIdentifier, screenInitSettings)
    val serverUrl = "testserver@test"
    com.matrix159.mastadonclone.shared.mvi.screens.login.login(serverUrl)
  }
}
