package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Polls(
  @SerialName("max_characters_per_option")
  val maxCharactersPerOption: Int,
  @SerialName("max_expiration")
  val maxExpiration: Int,
  @SerialName("max_options")
  val maxOptions: Int,
  @SerialName("min_expiration")
  val minExpiration: Int
)
