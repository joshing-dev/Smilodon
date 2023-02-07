package com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstanceResponseJson(
  @SerialName("configuration")
  val configuration: Configuration = Configuration(),
  @SerialName("contact")
  val contact: Contact = Contact(),
  @SerialName("description")
  val description: String = "",
  @SerialName("domain")
  val domain: String = "",
  @SerialName("languages")
  val languages: List<String> = listOf(),
  @SerialName("registrations")
  val registrations: Registrations = Registrations(),
  @SerialName("rules")
  val rules: List<Rule> = listOf(),
  @SerialName("source_url")
  val sourceUrl: String = "",
  @SerialName("thumbnail")
  val thumbnail: Thumbnail = Thumbnail(),
  @SerialName("title")
  val title: String = "",
  @SerialName("usage")
  val usage: Usage = Usage(),
  @SerialName("version")
  val version: String = ""
)