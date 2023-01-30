package com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Versions(
  @SerialName("@1x")
  val normalResolution: String = "",
  @SerialName("@2x")
  val doubleResolution: String = ""
)