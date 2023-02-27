package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Original(
  @SerialName("width")
  val width: Int? = null,
  @SerialName("height")
  val height: Int? = null,
  @SerialName("size")
  val size: String? = null,
  @SerialName("aspect")
  val aspect: Double? = null,
)
