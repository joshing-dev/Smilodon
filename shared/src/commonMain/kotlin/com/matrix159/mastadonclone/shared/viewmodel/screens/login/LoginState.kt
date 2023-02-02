package com.matrix159.mastadonclone.shared.viewmodel.screens.login

import com.matrix159.mastadonclone.shared.viewmodel.ScreenState

data class LoginScreenState (
  val isScreenLoading: Boolean = false,
  val isLoadingServerList: Boolean = false,
  val serverName: String? = null,
  val serverDescription: String? = null,
): ScreenState

/********** property classes **********/
