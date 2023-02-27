package com.matrix159.mastadonclone.shared.data.models.mastadonapi.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Focus(
  @SerialName("x")
  val x: Double,
  @SerialName("y")
  val y: Double
)
