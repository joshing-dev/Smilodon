package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
  @SerialName("focus")
  val focus: Focus = Focus(),
  @SerialName("original")
  val original: Original = Original(),
  @SerialName("small")
  val small: Small = Small()
)