package com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
  @SerialName("accounts")
  val accounts: Accounts = Accounts(),
  @SerialName("media_attachments")
  val mediaAttachments: MediaAttachments = MediaAttachments(),
  @SerialName("polls")
  val polls: Polls = Polls(),
  @SerialName("statuses")
  val statuses: Statuses = Statuses(),
  @SerialName("translation")
  val translation: Translation = Translation(),
  @SerialName("urls")
  val urls: Urls = Urls()
)