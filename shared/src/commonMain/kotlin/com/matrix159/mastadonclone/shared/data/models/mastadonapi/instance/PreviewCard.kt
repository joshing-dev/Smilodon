package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviewCard(
  @SerialName("author_name")
  val authorName: String = "",
  @SerialName("author_url")
  val authorUrl: String = "",
  @SerialName("blurhash")
  val blurhash: String = "",
  @SerialName("description")
  val description: String = "",
  @SerialName("embed_url")
  val embedUrl: String = "",
  @SerialName("height")
  val height: Int = 0,
  @SerialName("html")
  val html: String = "",
  @SerialName("image")
  val image: String = "",
  @SerialName("provider_name")
  val providerName: String = "",
  @SerialName("provider_url")
  val providerUrl: String = "",
  @SerialName("title")
  val title: String = "",
  @SerialName("type")
  val type: String = "",
  @SerialName("url")
  val url: String = "",
  @SerialName("width")
  val width: Int = 0
)