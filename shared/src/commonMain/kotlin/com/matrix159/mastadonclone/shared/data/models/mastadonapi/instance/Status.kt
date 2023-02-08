package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO rename this for just display purposes or something and cache the rest of the actually used
// Status properties.
@Serializable
data class Status(

    @SerialName("account")
    val account: Account,

    @SerialName("content")
    val content: String,

    @SerialName("created_at")
    val createdAt: String,

)

//@Serializable
//data class Status(
//    @SerialName("account")
//    val account: Account,
//    @SerialName("application")
//    val application: Application,
//    @SerialName("bookmarked")
//    val bookmarked: Boolean,
//    @SerialName("card")
//    val card: PreviewCard,
//    @SerialName("content")
//    val content: String,
//    @SerialName("created_at")
//    val createdAt: String,
//    @SerialName("emojis")
//    val emojis: List<CustomEmoji>,
//    @SerialName("favourited")
//    val favourited: Boolean,
//    @SerialName("favourites_count")
//    val favouritesCount: Int,
//    @SerialName("id")
//    val id: String,
//    @SerialName("in_reply_to_account_id")
//    val inReplyToAccountId: String?,
//    @SerialName("in_reply_to_id")
//    val inReplyToId: String?,
//    @SerialName("language")
//    val language: String? = null,
//    @SerialName("media_attachments")
//    val mediaAttachments: List<MediaAttachment>,
//    @SerialName("mentions")
//    val mentions: List<Mention>,
//    @SerialName("muted")
//    val muted: Boolean,
//    @SerialName("poll")
//    val poll: Poll?,
//    @SerialName("reblog")
//    val reblog: Status?,
//    @SerialName("reblogged")
//    val reblogged: Boolean,
//    @SerialName("reblogs_count")
//    val reblogsCount: Int,
//    @SerialName("replies_count")
//    val repliesCount: Int,
//    @SerialName("sensitive")
//    val sensitive: Boolean,
//    @SerialName("spoiler_text")
//    val spoilerText: String,
//    @SerialName("tags")
//    val tags: List<Tag>,
//    @SerialName("uri")
//    val uri: String,
//    @SerialName("url")
//    val url: String,
//    @SerialName("visibility")
//    val visibility: String
//)