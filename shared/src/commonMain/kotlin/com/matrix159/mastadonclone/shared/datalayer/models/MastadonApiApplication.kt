package com.matrix159.mastadonclone.shared.datalayer.models

data class MastadonApiApplication(
  val clientId: String,
  val clientSecret: String,
  val redirectUri: String,
)
