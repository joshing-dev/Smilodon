package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomEmoji(
  @SerialName("category")
  val category: String = "",
  @SerialName("shortcode")
  val shortcode: String = "",
  @SerialName("static_url")
  val staticUrl: String = "",
  @SerialName("url")
  val url: String = "",
  @SerialName("visible_in_picker")
  val visibleInPicker: Boolean = false
)
