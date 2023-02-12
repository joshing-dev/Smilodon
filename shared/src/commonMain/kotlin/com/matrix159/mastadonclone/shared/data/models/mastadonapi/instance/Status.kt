package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO rename this for just display purposes or something and cache the rest of the actually used
// Status properties.
@Serializable
data class Status(
  @SerialName("account")
  val account: Account,

  @SerialName("content")
  val content: String,

  @SerialName("created_at")
  val createdAt: String,
)
