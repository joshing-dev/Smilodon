package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.ScreenParams
import com.matrix159.mastadonclone.shared.viewmodel.ScreenState
import com.matrix159.mastadonclone.shared.viewmodel.screens.ScreenInitSettings
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.HomeFeedState
import kotlinx.serialization.Serializable

// INIZIALIZATION settings for this screen
// this is what should be implemented:
// - a data class implementing the ScreenParams interface, which defines the parameters to the passed to the screen
// - Navigation extension function taking the ScreenParams class as an argument, return the ScreenInitSettings for this screen
// to understand the initialization behaviour, read the comments in the ScreenInitSettings.kt file

//@Serializable // Note: ScreenParams should always be set as Serializable
//data class LoginScreenParams(val test: Int) : ScreenParams

fun Navigation.initLoginScreen() = ScreenInitSettings(
  title = "Login Screen",
  initState = { LoginScreenState(isLoading = true) },
  callOnInit = {
    // update state, after retrieving data from the repository
    stateManager.updateScreen<LoginScreenState> {
      it.copy(
        isLoading = false,
      )
    }
  },
  reinitOnEachNavigation = false,
  callOnInitAlsoAfterBackground = false
)