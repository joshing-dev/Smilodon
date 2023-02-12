package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mention(
  @SerialName("id")
  val id: String,
  @SerialName("username")
  val username: String,
  @SerialName("url")
  val url: String,
  @SerialName("acct")
  val acct: String,
)
