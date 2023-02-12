package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import com.matrix159.mastadonclone.shared.data.models.mastadonapi.common.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
  @SerialName("account")
  val account: Account,
  @SerialName("email")
  val email: String,
)
