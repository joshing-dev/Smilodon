package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common

import kotlinx.serialization.SerialName

enum class Visibility {
  @SerialName("public")
  Public,
  @SerialName("unlisted")
  Unlisted,
  @SerialName("private")
  Private,
  @SerialName("direct")
  Direct
}
