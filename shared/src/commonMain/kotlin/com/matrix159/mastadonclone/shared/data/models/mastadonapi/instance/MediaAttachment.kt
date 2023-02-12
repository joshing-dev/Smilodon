package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaAttachment(
  @SerialName("blurhash")
  val blurhash: String = "",
  @SerialName("description")
  val description: String = "",
  @SerialName("id")
  val id: String = "",
  @SerialName("meta")
  val meta: Meta = Meta(),
  @SerialName("preview_url")
  val previewUrl: String = "",
  @SerialName("remote_url")
  val remoteUrl: String? = null,
  @SerialName("text_url")
  val textUrl: String = "",
  @SerialName("type")
  val type: String = "",
  @SerialName("url")
  val url: String = ""
)
