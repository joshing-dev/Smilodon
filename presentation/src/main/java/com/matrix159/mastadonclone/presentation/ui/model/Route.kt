package com.matrix159.mastadonclone.presentation.ui.model

sealed class Route(val routeName: String) {
  object LoginScreen : Route(routeName = "loginScreen")
  object HomeFeed : Route(routeName = "homeFeed")
}
