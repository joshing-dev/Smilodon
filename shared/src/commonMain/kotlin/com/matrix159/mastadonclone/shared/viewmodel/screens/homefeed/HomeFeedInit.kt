package com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed

import com.matrix159.mastadonclone.shared.viewmodel.AppState
import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.ScreenParams
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.ScreenInitSettings
import kotlinx.serialization.Serializable

// INIZIALIZATION settings for this screen
// this is what should be implemented:
// - a data class implementing the ScreenParams interface, which defines the parameters to the passed to the screen
// - Navigation extension function taking the ScreenParams class as an argument, return the ScreenInitSettings for this screen
// to understand the initialization behaviour, read the comments in the ScreenInitSettings.kt file

//@Serializable // Note: ScreenParams should always be set as Serializable
//data class HomeFeedParams(val test: Int) : ScreenParams

fun Navigation.initHomeFeed() = ScreenInitSettings(
  title = "Home Feed",
  initState = { HomeFeedState(isLoading = true) },
  callOnInit = {
    // update state, after retrieving data from the repository
    stateManager.updateScreen<HomeFeedState> {
      it.copy(
        isLoading = false,
        posts = listOf(
          Post("Test author", "test post here"),
          Post("Another author", "Another post")
        ),
      )
    }
  },
  reinitOnEachNavigation = true,
  callOnInitAlsoAfterBackground = true
)