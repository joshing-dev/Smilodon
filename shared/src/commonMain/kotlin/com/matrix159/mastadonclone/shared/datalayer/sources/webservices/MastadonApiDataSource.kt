package com.matrix159.mastadonclone.shared.datalayer.sources.webservices

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal interface MastadonRemoteDataSource {
  suspend fun createClientApplication(): CreateApplicationResponseJson
}

@Serializable
data class CreateApplicationRequestJson(
  @SerialName("client_name")
  val clientName: String,
  @SerialName("redirect_uris")
  val redirectUris: String,
  val scopes: String,
  val website: String,
)

@Serializable
data class CreateApplicationResponseJson(
  val id: String,
  val name: String,
  val website: String,
  @SerialName("redirect_uri")
  val redirectUris: String,
  @SerialName("client_id")
  val clientId: String,
  @SerialName("client_secret")
  val clientSecret: String,
  @SerialName("vapid_key")
  val vapidKey: String,
)

internal class MastadonApiRemoteDataSource : MastadonRemoteDataSource {
  private val baseUrl = "https://androiddev.social/api/v1/apps"
  private val client: HttpClient by lazy { createApiClient() }

  override suspend fun createClientApplication(): CreateApplicationResponseJson =
    client.post(baseUrl) {
      contentType(ContentType.Application.Json)
      setBody(
        CreateApplicationRequestJson(
          "Mastadon Clone App",
          "com.example.mastadonclone://callback",
          "read write push",
          "https://androiddev.social"
        )
      )
    }.body()
}