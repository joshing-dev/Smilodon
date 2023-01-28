package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.viewmodel.ScreenState

data class LoginScreenState (
  val isLoading: Boolean = false,
  val serverName: String = "",
  val serverList: List<String> = emptyList()
): ScreenState

/********** property classes **********/
