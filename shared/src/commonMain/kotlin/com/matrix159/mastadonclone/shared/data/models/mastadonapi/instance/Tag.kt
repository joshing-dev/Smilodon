package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
