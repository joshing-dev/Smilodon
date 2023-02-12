package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matrix159.mastadonclone.presentation.ui.model.Route
import com.matrix159.mastadonclone.shared.mvi.app.AppState
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedEffects
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenAction
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenEffect
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

const val DEBOUNCE_TIME_FOR_INPUT = 300L

@OptIn(ExperimentalLifecycleComposeApi::class, FlowPreview::class)
@Composable
fun Router(
  appStore: AppStore = get(),
  loginStore: LoginStore = get(),
  homeFeedStore: HomeFeedStore = get(),
) {
  val navController = rememberNavController()

  // TODO: This is temporary, need to handle navigation and configuration changes better
  LaunchedEffect(true) {
    appStore.state
      .distinctUntilChanged { old, new ->
        old == new
      }
      .onEach { appState ->
        when (appState) {
          is AppState.LoggedIn -> navController.navigate(Route.HomeFeed.routeName) {
            popUpTo(navController.graph.startDestinationId) {
              inclusive = true
            }
            launchSingleTop = true
          }
          is AppState.NotLoggedIn -> navController.navigate(Route.LoginScreen.routeName) {
            popUpTo(navController.graph.startDestinationId) {
              inclusive = true
            }
            launchSingleTop = true
          }
          is AppState.Authenticating -> {}
        }
      }
      .launchIn(this)
  }
  NavHost(navController = navController, startDestination = Route.LoginScreen.routeName) {
    composable(Route.LoginScreen.routeName) {
      val coroutineScope = rememberCoroutineScope()
      val loginScreenState by loginStore.state.collectAsStateWithLifecycle(context = Dispatchers.Main.immediate)
      val serverFlow: MutableStateFlow<String> = remember { MutableStateFlow("") }
      LaunchedEffect(true) {
        serverFlow
          .filter { it.isNotEmpty() }
          .onEach { loginStore.dispatchAction(LoginScreenAction.StartLoading) }
          .debounce(DEBOUNCE_TIME_FOR_INPUT)
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
    composable(Route.HomeFeed.routeName) {
      val homeFeedState by homeFeedStore.state.collectAsStateWithLifecycle(context = Dispatchers.Main.immediate)
      // Make sure the initial posts are loaded
      LaunchedEffect(true) {
        homeFeedStore.dispatchEffect(HomeFeedEffects.Init)
      }

      HomeFeedScreen(
        state = homeFeedState,
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}
