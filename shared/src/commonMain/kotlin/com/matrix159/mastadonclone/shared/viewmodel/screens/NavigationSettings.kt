package com.matrix159.mastadonclone.shared.viewmodel.screens

import com.matrix159.mastadonclone.shared.viewmodel.ScreenIdentifier

// CONFIGURATION SETTINGS

object NavigationSettings {
  val homeScreen = Level1Navigation.LoginScreen // the start screen should be specified here
  val saveLastLevel1Screen = true
  val alwaysQuitOnHomeScreen = true
}


// LEVEL 1 NAVIGATION OF THE APP

enum class Level1Navigation(val screenIdentifier: ScreenIdentifier, val rememberVerticalStack: Boolean = false) {
  LoginScreen(ScreenIdentifier.get(Screen.LoginScreen, null)),
  HomeFeed(ScreenIdentifier.get(Screen.HomeFeed, null)),
}