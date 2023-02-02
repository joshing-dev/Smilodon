package com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Polls(
  @SerialName("max_characters_per_option")
  val maxCharactersPerOption: Int = 0,
  @SerialName("max_expiration")
  val maxExpiration: Int = 0,
  @SerialName("max_options")
  val maxOptions: Int = 0,
  @SerialName("min_expiration")
  val minExpiration: Int = 0
)