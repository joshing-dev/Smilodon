package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
  @SerialName("blurhash")
  val blurhash: String = "",
  @SerialName("url")
  val url: String = "",
  @SerialName("versions")
  val versions: Versions = Versions()
)