package com.matrix159.mastadonclone.shared.mvi.app

sealed interface AppEffect {
  object Startup: AppEffect
  data class StartAuthentication(
    val userServerUrl: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
  ): AppEffect
  object SaveAppState: AppEffect
  data class Login(val accessToken: String): AppEffect
  object Logout: AppEffect
}