package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstanceResponseJson(
  @SerialName("configuration")
  val configuration: Configuration,
  @SerialName("contact")
  val contact: Contact,
  @SerialName("description")
  val description: String,
  @SerialName("domain")
  val domain: String,
  @SerialName("languages")
  val languages: List<String>,
  @SerialName("registrations")
  val registrations: Registrations,
  @SerialName("rules")
  val rules: List<Rule>,
  @SerialName("source_url")
  val sourceUrl: String,
  @SerialName("thumbnail")
  val thumbnail: Thumbnail,
  @SerialName("title")
  val title: String,
  @SerialName("usage")
  val usage: Usage,
  @SerialName("version")
  val version: String
)
