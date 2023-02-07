package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
  @SerialName("account")
  val account: Account = Account(),
  @SerialName("email")
  val email: String = ""
)