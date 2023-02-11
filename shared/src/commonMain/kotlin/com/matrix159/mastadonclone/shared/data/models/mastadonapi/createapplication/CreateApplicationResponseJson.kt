package com.matrix159.mastadonclone.shared.data.models.mastadonapi.createapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateApplicationResponseJson(
  val id: String,
  val name: String,
  val website: String,
  @SerialName("redirect_uri")
  val redirectUris: String,
  @SerialName("client_id")
  val clientId: String,
  @SerialName("client_secret")
  val clientSecret: String,
  @SerialName("vapid_key")
  val vapidKey: String,
)
