package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.datalayer.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.viewmodel.AppState
import com.matrix159.mastadonclone.shared.viewmodel.AuthStatus
import com.matrix159.mastadonclone.shared.viewmodel.Events

/********** EVENT functions, called directly by the UI layer **********/


fun Events.login() = screenCoroutine {
  val clientAppDetails: MastadonApiApplication = this.dataRepository.getClientApplication()
  lateinit var newState: AppState
  stateManager.updateAppState {
    newState = it.copy(
      authState = it.authState.copy(
        clientId = clientAppDetails.clientId,
        clientSecret = clientAppDetails.clientSecret,
        redirectUri = clientAppDetails.redirectUri,
        authStatus = AuthStatus.Authenticating
      )
    )
    return@updateAppState newState
  }
  // Store the new state in local settings
  stateManager.dataRepository.mastadonSettings.authState = newState.authState
}

fun Events.loginComplete(accessToken: String) {
  lateinit var newState: AppState
  stateManager.updateAppState {
    newState = it.copy(
      authState = it.authState.copy(authStatus = AuthStatus.LoggedIn, accessToken = accessToken)
    )
    return@updateAppState newState
  }
  // Store the new state in local settings
  stateManager.dataRepository.mastadonSettings.authState = newState.authState
}

fun Events.logout() {
  lateinit var newState: AppState
  stateManager.updateAppState {
    newState = it.copy(
      authState = it.authState.copy(
        accessToken = null,
        authStatus = AuthStatus.NotLoggedIn
      )
    )
    return@updateAppState newState
  }
  // Store the new state in local settings
  stateManager.dataRepository.mastadonSettings.authState = newState.authState
}
