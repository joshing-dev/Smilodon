package com.matrix159.mastadonclone.shared.viewmodel.screens

import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.ScreenIdentifier
import com.matrix159.mastadonclone.shared.viewmodel.ScreenState
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.initHomeFeed
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.LoginScreenState
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.initLoginScreen

// DEFINITION OF ALL SCREENS IN THE APP

enum class Screen(
  val asString: String,
  val navigationLevel : Int = 1,
  val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
  val stackableInstances : Boolean = false,
) {
  LoginScreen("loginscreen", 1, { screenIdentifier -> initLoginScreen()}),
  HomeFeed("homefeed", 1, { screenIdentifier -> initHomeFeed() }),
}