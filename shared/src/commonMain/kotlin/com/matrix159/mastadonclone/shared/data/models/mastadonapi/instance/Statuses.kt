package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statuses(
  @SerialName("characters_reserved_per_url")
  val charactersReservedPerUrl: Int = 0,
  @SerialName("max_characters")
  val maxCharacters: Int = 0,
  @SerialName("max_media_attachments")
  val maxMediaAttachments: Int = 0
)
