package com.matrix159.mastadonclone.shared.datalayer.sources.webservices

import com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.datalayer.sources.localsettings.MastadonSettings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal interface MastadonRemoteDataSource {
  /**
   * Creates a client application to use with OAuth. This includes the client_id, client_secret
   * and additional information.
   */
  suspend fun createClientApplication(): CreateApplicationResponseJson

  /**
   * Searches for an instance via a server URL ex: androiddev.social
   * @return The server information if found, null if not.
   */
  suspend fun getInstance(serverUrl: String): InstanceResponseJson?
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

internal class MastadonApiRemoteDataSource(private val settings: MastadonSettings) : MastadonRemoteDataSource {
  private val baseUrl = "https://androiddev.social/api/v1/apps"
  private val client: HttpClient
    get() = createApiClient(settings.authState.accessToken)

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

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    return try {
      client.get("https://$serverUrl/api/v2/instance").body()
    } catch (ex: Exception) {
      null
    }
  }

}
