package com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usage(
  @SerialName("users")
  val users: Users = Users()
)