package com.matrix159.mastadonclone.shared.data.models.mastadonapi.timelines


import com.matrix159.mastadonclone.shared.data.models.mastadonapi.common.Account
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.common.MediaAttachment
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.common.Visibility
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.ConfigurationMediaAttachments
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO rename this for just display purposes or something and cache the rest of the actually used
// Status properties.
@Serializable
data class Status(
  @SerialName("id")
  val id: String,
  @SerialName("uri")
  val uri: String,
  @SerialName("created_at")
  val createdAt: String,
  @SerialName("account")
  val account: Account,
  @SerialName("content")
  val content: String,
  @SerialName("visibility")
  val visiblity: Visibility,
  @SerialName("sensitive")
  val sensitive: Boolean = false,
  @SerialName("spoiler_text")
  val spoilerText: String,
  @SerialName("media_attachments")
  val mediaAttachments: List<MediaAttachment>,
  @SerialName("reblog")
  val reblog: Status?,
  // TODO: Add more fields for status
)
