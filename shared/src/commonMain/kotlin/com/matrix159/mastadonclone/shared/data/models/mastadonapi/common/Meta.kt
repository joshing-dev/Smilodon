package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
  @SerialName("original")
  val original: Original? = null,
  @SerialName("small")
  val small: Small? = null,
  @SerialName("focus")
  val focus: Focus? = null,
)
