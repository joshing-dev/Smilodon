package com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Accounts(
  @SerialName("max_featured_tags")
  val maxFeaturedTags: Int = 0
)