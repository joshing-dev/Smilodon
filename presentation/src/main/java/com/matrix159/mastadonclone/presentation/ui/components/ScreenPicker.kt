package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.loadHomeFeed
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.login

@Composable
fun ScreenPicker(
  navigation: Navigation,
) {
  val stateProvider = remember { navigation.stateProvider }
  val events = remember { navigation.events }
  val currentScreenIdentifier =
    remember(navigation.currentScreenIdentifier) { navigation.currentScreenIdentifier }
  when (currentScreenIdentifier.screen) {
    Screen.HomeFeed ->
      HomeFeedScreen(
        stateProvider.get(currentScreenIdentifier),
        loadNew = { events.loadHomeFeed() },
        modifier = Modifier.fillMaxSize()
      )
    Screen.LoginScreen ->
      LoginScreen(
        login = { events.login() },
        modifier = Modifier.fillMaxSize()
      )
    else -> {
      Text("No screen found")
    }
  }
}
