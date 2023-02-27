package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
  @SerialName("accounts")
  val accounts: Accounts,
  @SerialName("media_attachments")
  val mediaAttachments: ConfigurationMediaAttachments,
  @SerialName("polls")
  val polls: Polls,
  @SerialName("statuses")
  val statuses: Statuses,
  @SerialName("translation")
  val translation: Translation,
  @SerialName("urls")
  val urls: Urls
)
