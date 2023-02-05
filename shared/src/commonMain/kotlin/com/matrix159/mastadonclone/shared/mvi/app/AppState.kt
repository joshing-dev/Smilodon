package com.matrix159.mastadonclone.shared.mvi.app

sealed interface AppState {
  object NotLoggedIn: AppState
  data class LoggedIn(
    val accessToken: String,
  ): AppState
  data class Authenticating(
    val userServerUrl: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
  ): AppState
}
