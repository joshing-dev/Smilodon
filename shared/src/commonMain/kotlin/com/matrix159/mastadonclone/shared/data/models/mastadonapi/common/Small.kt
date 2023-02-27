package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Small(
  @SerialName("width")
  val width: Int,
  @SerialName("height")
  val height: Int,
  @SerialName("size")
  val size: String,
  @SerialName("aspect")
  val aspect: Double
)
