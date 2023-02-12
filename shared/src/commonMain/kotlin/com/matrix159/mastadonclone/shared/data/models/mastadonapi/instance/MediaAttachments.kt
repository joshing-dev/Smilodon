package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaAttachments(
  @SerialName("image_matrix_limit")
  val imageMatrixLimit: Int = 0,
  @SerialName("image_size_limit")
  val imageSizeLimit: Int = 0,
  @SerialName("supported_mime_types")
  val supportedMimeTypes: List<String> = listOf(),
  @SerialName("video_frame_rate_limit")
  val videoFrameRateLimit: Int = 0,
  @SerialName("video_matrix_limit")
  val videoMatrixLimit: Int = 0,
  @SerialName("video_size_limit")
  val videoSizeLimit: Int = 0
)
