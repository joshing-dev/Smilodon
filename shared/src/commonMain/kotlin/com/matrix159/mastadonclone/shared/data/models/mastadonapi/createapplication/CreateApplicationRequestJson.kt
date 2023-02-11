package com.matrix159.mastadonclone.shared.data.models.mastadonapi.createapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateApplicationRequestJson(
  @SerialName("client_name")
  val clientName: String,
  @SerialName("redirect_uris")
  val redirectUris: String,
  val scopes: String,
  val website: String,
)


