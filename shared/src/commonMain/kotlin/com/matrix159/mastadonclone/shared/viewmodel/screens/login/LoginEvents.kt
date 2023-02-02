package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.datalayer.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.viewmodel.AppState
import com.matrix159.mastadonclone.shared.viewmodel.AuthStatus
import com.matrix159.mastadonclone.shared.viewmodel.Events

/********** EVENT functions, called directly by the UI layer **********/
fun Events.login(serverUrl: String) = screenCoroutine {
  val clientAppDetails: MastadonApiApplication = this.dataRepository.getClientApplication(serverUrl)
  lateinit var newState: AppState
  stateManager.updateAppState {
    newState = it.copy(
      authState = it.authState.copy(
        userServerUrl = clientAppDetails.serverUrl,
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

fun Events.loginComplete(accessToken: String) = screenCoroutine {
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

fun Events.logout() = screenCoroutine {
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

fun Events.searchForServer(serverUrl: String) = screenCoroutine {
  stateManager.updateScreen<LoginScreenState> {
    it.copy(
      isLoadingServerList = true,
      serverName = null,
      serverDescription = null,
    )
  }
  val response: InstanceResponseJson? = stateManager.dataRepository.getInstance(serverUrl)
  if (response != null) {
    stateManager.updateScreen<LoginScreenState> {
      it.copy(serverName = response.title, serverDescription = response.description)
    }
  } else {
    stateManager.updateScreen<LoginScreenState> {
      it.copy(serverName = null, serverDescription = null)
    }
  }
  stateManager.updateScreen<LoginScreenState> {
    it.copy(isLoadingServerList = false)
  }
}
