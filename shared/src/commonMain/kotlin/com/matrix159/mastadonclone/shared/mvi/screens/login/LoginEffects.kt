package com.matrix159.mastadonclone.shared.mvi.screens.login

sealed interface LoginScreenEffect {
  data class Login(val serverUrl: String): LoginScreenEffect

  data class SearchForServer(val serverUrl: String): LoginScreenEffect
}