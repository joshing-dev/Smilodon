package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Registrations(
  @SerialName("approval_required")
  val approvalRequired: Boolean = false,
  @SerialName("enabled")
  val enabled: Boolean = false,
  @SerialName("message")
  val message: String? = null
)