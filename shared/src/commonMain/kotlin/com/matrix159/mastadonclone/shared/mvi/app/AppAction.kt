package com.matrix159.mastadonclone.shared.mvi.app


sealed interface AppAction {
  data class StartAuthentication(
    val userServerUrl: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
  ) : AppAction

  data class LoginSuccess(
    val accessToken: String
  ) : AppAction

  object Logout : AppAction
}



