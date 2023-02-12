package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rule(
  @SerialName("id")
  val id: String,
  @SerialName("text")
  val text: String
)
