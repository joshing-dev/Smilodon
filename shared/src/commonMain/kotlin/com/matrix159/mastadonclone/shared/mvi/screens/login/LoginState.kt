package com.matrix159.mastadonclone.shared.mvi.screens.login


sealed interface LoginScreenState {
  //object Loading: LoginScreenState
  object Error: LoginScreenState
  data class BaseState(
    val serverUrl: String = "",
    val serverTitle: String? = null,
    val serverDescription: String? = null,
    val loadingIndicatorShown: Boolean = false
  ): LoginScreenState
}
