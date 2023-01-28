package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.viewmodel.AuthStatus
import com.matrix159.mastadonclone.shared.viewmodel.Events

/********** EVENT functions, called directly by the UI layer **********/


fun Events.login() = screenCoroutine {
  val clientAppDetails = this.dataRepository.mastadonApi.createClientApplication()
  stateManager.updateAppState {
    it.copy(
      authState = it.authState.copy(
        clientId = clientAppDetails.clientId,
        clientSecret = clientAppDetails.clientSecret,
        authStatus = AuthStatus.Authenticating
      )
    )
  }
}

fun Events.loginComplete() {
  stateManager.updateAppState {
    it.copy(authState = it.authState.copy(authStatus = AuthStatus.LoggedIn))
  }
}

fun Events.logout() {
  stateManager.updateAppState {
    it.copy(authState = it.authState.copy(authStatus = AuthStatus.NotLoggedIn))
  }
}