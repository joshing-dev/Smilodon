package com.matrix159.mastadonclone.shared.viewmodel

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.matrix159.mastadonclone.shared.datalayer.MastadonRepository
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.HomeFeedState
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.Post
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class HomeFeedTests {

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
  fun testHomeFeed() {
    val screenIdentifier = ScreenIdentifier.get(Screen.HomeFeed, null)
    val screenInitSettings = screenIdentifier.getScreenInitSettings(navigation)
    stateManager.addScreen(screenIdentifier, screenInitSettings)
    val posts = listOf(Post(author = "Test author", content = "Test content"))
    stateManager.updateScreen<HomeFeedState> {
      it.copy(posts = posts)
    }
    val screenState = stateProvider.get<HomeFeedState>(screenIdentifier)
    assertEquals(posts, screenState.posts)
  }
}
