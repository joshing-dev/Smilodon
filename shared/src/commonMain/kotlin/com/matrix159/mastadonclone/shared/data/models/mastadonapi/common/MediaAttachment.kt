package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaAttachment(
  @SerialName("id")
  val id: String,
  @SerialName("type")
  val type: MediaType,
  @SerialName("url")
  val url: String,
  @SerialName("preview_url")
  val previewUrl: String,
  @SerialName("remote_url")
  val remoteUrl: String?,
  @SerialName("meta")
  val meta: Meta,
  @SerialName("description")
  val description: String? = null,
  @SerialName("blurhash")
  val blurhash: String
)
