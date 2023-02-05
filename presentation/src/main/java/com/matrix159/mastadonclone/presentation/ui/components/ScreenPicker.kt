package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedEffects
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.homeFeedStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenAction
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenEffect
import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.loadHomeFeed
import com.matrix159.mastadonclone.shared.mvi.screens.login.loginStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class, FlowPreview::class)
@Composable
fun ScreenPicker(
  navigation: Navigation,
) {
//  val stateProvider = remember { navigation.stateProvider }
//  val events = remember { navigation.events }
  val currentScreenIdentifier =
    remember(navigation.currentScreenIdentifier) { navigation.currentScreenIdentifier }



  when (currentScreenIdentifier.screen) {
    Screen.LoginScreen -> {
      val coroutineScope = rememberCoroutineScope()
      val loginScreenState by loginStore.state.collectAsStateWithLifecycle(context = Dispatchers.Main.immediate)
      val serverFlow: MutableStateFlow<String> = remember { MutableStateFlow("") }
      LaunchedEffect(true) {
        serverFlow
          .filter { it.isNotEmpty() }
          .onEach { loginStore.dispatchAction(LoginScreenAction.StartLoading) }
          .debounce(300)
          .onEach { loginStore.dispatchEffect(LoginScreenEffect.SearchForServer(it)) }
          .launchIn(this)
      }
      LoginScreen(
        state = loginScreenState,
        serverTextChange = {
          loginStore.dispatchAction(LoginScreenAction.ServerUrlUpdated(it))
          serverFlow.value = it
        },
        login = {
          coroutineScope.launch {
            loginStore.dispatchEffect(LoginScreenEffect.Login(it))
          }
        },
        modifier = Modifier.fillMaxSize()
      )
    }
    Screen.HomeFeed -> {
      val coroutineScope = rememberCoroutineScope()
      val homeFeedState by homeFeedStore.state.collectAsStateWithLifecycle(context = Dispatchers.Main.immediate)
      // Make sure the initial posts are loaded
      LaunchedEffect(true) {
        homeFeedStore.dispatchEffect(HomeFeedEffects.Init)
      }

      HomeFeedScreen(
        state = homeFeedState,
        loadNew = {
          coroutineScope.launch {
            homeFeedStore.dispatchEffect(HomeFeedEffects.LoadPosts)
          }
        },
        modifier = Modifier.fillMaxSize()
      )
    }

    else -> {
      Text("No screen found")
    }
  }
}
