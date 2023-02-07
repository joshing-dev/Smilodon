package com.matrix159.mastadonclone.shared.mvi.screens.login


sealed interface LoginScreenAction {
  data class ServerUrlUpdated(val serverUrl: String): LoginScreenAction
  data class ServerFound(val serverTitle: String, val serverDescription: String): LoginScreenAction
  object StartLoading: LoginScreenAction
  object StopLoading: LoginScreenAction
}



