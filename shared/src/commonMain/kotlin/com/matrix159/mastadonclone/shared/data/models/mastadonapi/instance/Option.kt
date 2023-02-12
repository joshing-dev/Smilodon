package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
  @SerialName("title")
  val title: String = "",
  @SerialName("votes_count")
  val votesCount: Int = 0
)
