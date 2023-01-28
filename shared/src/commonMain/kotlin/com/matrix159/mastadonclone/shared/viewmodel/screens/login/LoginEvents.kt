package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.viewmodel.Events
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen

/********** EVENT functions, called directly by the UI layer **********/


fun Events.login()  {
  stateManager.updateAppState {
    it.copy(isLoggedIn = true)
  }
  navigation.navigate(Screen.HomeFeed)
}


fun Events.logout()  {
  stateManager.updateAppState {
    it.copy(isLoggedIn = false)
  }
  navigation.navigate(Screen.LoginScreen)
}