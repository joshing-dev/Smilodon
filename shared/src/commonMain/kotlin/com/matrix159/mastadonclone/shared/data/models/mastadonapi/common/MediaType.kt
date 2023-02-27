package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common

import kotlinx.serialization.SerialName

enum class MediaType {
  @SerialName("unknown")
  Unknown,
  @SerialName("image")
  Image,
  @SerialName("gifv")
  Gifv,
  @SerialName("video")
  Video,
  @SerialName("audio")
  Audio
}
