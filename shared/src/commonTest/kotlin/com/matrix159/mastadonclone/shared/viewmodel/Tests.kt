package com.matrix159.mastadonclone.shared.viewmodel

import com.matrix159.mastadonclone.shared.datalayer.Repository
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.LoginScreenParams
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.LoginScreenState
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTests {

  private lateinit var viewModel: DKMPViewModel
  private lateinit var navigation: Navigation
  private lateinit var stateProvider: StateProvider
  private lateinit var stateManager: StateManager

  private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

  @BeforeTest
  fun before() {
    Dispatchers.setMain(testDispatcher)
    viewModel = DKMPViewModel(
      Repository(
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

  @Test()
  fun anything() {
    println("test")
  }

  @Test
  fun testLoginScreen() {
    val screenIdentifier = ScreenIdentifier.get(Screen.LoginScreen, LoginScreenParams())
    val screenInitSettings = screenIdentifier.getScreenInitSettings(navigation)
    stateManager.addScreen(screenIdentifier, screenInitSettings)
    val serverName = "This is a test server"
    val serverList = listOf("This is a test server", "This is another server")
    stateManager.updateScreen<LoginScreenState> {
      it.copy(serverName = serverName, serverList = serverList)
    }
    val screenState = stateProvider.get<LoginScreenState>(screenIdentifier)
    assertEquals(serverName, screenState.serverName)
    assertEquals(serverList, screenState.serverList)
  }
}
