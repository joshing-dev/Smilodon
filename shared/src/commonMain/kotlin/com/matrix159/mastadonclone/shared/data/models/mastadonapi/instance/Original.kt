package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Original(
  @SerialName("aspect")
  val aspect: Double = 0.0,
  @SerialName("height")
  val height: Int = 0,
  @SerialName("size")
  val size: String = "",
  @SerialName("width")
  val width: Int = 0
)