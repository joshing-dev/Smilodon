package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Account(
  @SerialName("acct")
  val acct: String,
  @SerialName("avatar")
  val avatar: String,
  @SerialName("avatar_static")
  val avatarStatic: String,
  @SerialName("bot")
  val bot: Boolean = false,
  @SerialName("created_at")
  val createdAt: String ,
  @SerialName("discoverable")
  val discoverable: Boolean = false,
  @SerialName("display_name")
  val displayName: String ,
  @SerialName("emojis")
  val emojis: List<CustomEmoji>,
  @SerialName("fields")
  val fields: List<Field>,
  @SerialName("followers_count")
  val followersCount: Int,
  @SerialName("following_count")
  val followingCount: Int,
  @SerialName("group")
  val group: Boolean = false,
  @SerialName("header")
  val header: String,
  @SerialName("header_static")
  val headerStatic: String,
  @SerialName("id")
  val id: String,
  @SerialName("last_status_at")
  val lastStatusAt: String?,
  @SerialName("locked")
  val locked: Boolean = false,
  @SerialName("limited")
  val limited: Boolean = false,
  @SerialName("moved")
  val moved: Boolean = false,
  @SerialName("noindex")
  val noindex: Boolean = false,
  @SerialName("note")
  val note: String,
  @SerialName("statuses_count")
  val statusesCount: Int,
  @SerialName("suspended")
  val suspended: Boolean = false,
  @SerialName("url")
  val url: String,
  @SerialName("username")
  val username: String
)

fun getMockAccount() = Account(
  acct = "acct test",
  avatar = "avatar test",
  avatarStatic = "avatarStatic test",
  bot = true,
  createdAt = "createdAt test",
  discoverable = true,
  displayName = "displayName test",
  emojis = listOf(getMockCustomEmoji()),
  fields = listOf(getMockField()),
  followersCount = 1,
  followingCount = 1,
  group = true,
  header = "header test",
  headerStatic = "headerStatic test",
  id = "id test",
  lastStatusAt = "lastStatusAt test",
  locked = true,
  limited = true,
  moved = true,
  noindex = true,
  note = "note test",
  statusesCount = 1,
  suspended = true,
  url = "url test",
  username = "username test"
)

fun getMockField() = Field(
  name = "name test",
  value = "value test",
  verifiedAt = "verifiedAt test"
)

fun getMockCustomEmoji() = CustomEmoji(
  category = "category test",
  shortcode = "shortcode test",
  staticUrl = "staticUrl test",
  url = "url test",
  visibleInPicker = true
)
