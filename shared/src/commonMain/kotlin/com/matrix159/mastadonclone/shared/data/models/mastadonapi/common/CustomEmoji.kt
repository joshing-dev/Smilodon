package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomEmoji(
  // API Docs say this is required, but it is missing in some requests
  @SerialName("category")
  val category: String? = null,
  @SerialName("shortcode")
  val shortcode: String,
  @SerialName("static_url")
  val staticUrl: String,
  @SerialName("url")
  val url: String,
  @SerialName("visible_in_picker")
  val visibleInPicker: Boolean = false
)
