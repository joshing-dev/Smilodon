package com.matrix159.mastadonclone.shared.data.models

data class MastadonApiApplication(
  val serverUrl: String,
  val clientId: String,
  val clientSecret: String,
  val redirectUri: String,
)
