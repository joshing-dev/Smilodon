package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account(
  @SerialName("acct")
  val acct: String = "",
  @SerialName("avatar")
  val avatar: String = "",
  @SerialName("avatar_static")
  val avatarStatic: String = "",
  @SerialName("bot")
  val bot: Boolean = false,
  @SerialName("created_at")
  val createdAt: String = "",
  @SerialName("discoverable")
  val discoverable: Boolean? = false,
  @SerialName("display_name")
  val displayName: String = "",
  @SerialName("emojis")
  val emojis: List<CustomEmoji> = listOf(),
  @SerialName("fields")
  val fields: List<Field> = listOf(),
  @SerialName("followers_count")
  val followersCount: Int = 0,
  @SerialName("following_count")
  val followingCount: Int = 0,
  @SerialName("group")
  val group: Boolean = false,
  @SerialName("header")
  val header: String = "",
  @SerialName("header_static")
  val headerStatic: String = "",
  @SerialName("id")
  val id: String = "",
  @SerialName("last_status_at")
  val lastStatusAt: String = "",
  @SerialName("locked")
  val locked: Boolean = false,
  @SerialName("noindex")
  val noindex: Boolean = false,
  @SerialName("note")
  val note: String = "",
  @SerialName("statuses_count")
  val statusesCount: Int = 0,
  @SerialName("url")
  val url: String = "",
  @SerialName("username")
  val username: String = ""
)