package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Focus(
  @SerialName("x")
  val x: Double = 0.0,
  @SerialName("y")
  val y: Double = 0.0
)
