package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Poll(
  @SerialName("emojis")
  val emojis: List<CustomEmoji> = listOf(),
  @SerialName("expired")
  val expired: Boolean = false,
  @SerialName("expires_at")
  val expiresAt: String = "",
  @SerialName("id")
  val id: String = "",
  @SerialName("multiple")
  val multiple: Boolean = false,
  @SerialName("options")
  val options: List<Option> = listOf(),
  @SerialName("own_votes")
  val ownVotes: List<Int> = listOf(),
  @SerialName("voted")
  val voted: Boolean = false,
  @SerialName("voters_count")
  val votersCount: Int? = null,
  @SerialName("votes_count")
  val votesCount: Int = 0
)