package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statuses(
  @SerialName("characters_reserved_per_url")
  val charactersReservedPerUrl: Int,
  @SerialName("max_characters")
  val maxCharacters: Int,
  @SerialName("max_media_attachments")
  val maxMediaAttachments: Int
)
