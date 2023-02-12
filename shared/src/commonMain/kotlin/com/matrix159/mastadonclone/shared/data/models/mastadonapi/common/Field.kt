package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Field(
  @SerialName("name")
  val name: String,
  @SerialName("value")
  val value: String,
  @SerialName("verified_at")
  val verifiedAt: String? = null
)
