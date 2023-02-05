package com.matrix159.mastadonclone.shared.datalayer.sources.webservices

import co.touchlab.kermit.Logger
import com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.datalayer.sources.localsettings.MastadonSettings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Remote data source that pulls information from the Mastadon API. It relies on a custom server URL
 * provided by the user to make the requests one authenticated.
 */
internal interface MastadonRemoteDataSource {
  /**
   * Creates a client application to use with OAuth. This includes the client_id, client_secret
   * and additional information.
   *
   * @return [CreateApplicationResponseJson]
   */
  suspend fun createClientApplication(serverUrl: String): CreateApplicationResponseJson

  /**
   * Searches for an instance via a server URL ex: androiddev.social
   *
   * @param serverUrl The custom server URL that the user is logged into.
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

internal class MastadonApiRemoteDataSource(private val settings: MastadonSettings) :
  MastadonRemoteDataSource {
  private val baseUrl: String
    get() = "https://${settings.appState.userServerUrl}/api/v1/apps"
  private val client: HttpClient
    get() = createApiClient(settings.appState.accessToken)

  override suspend fun createClientApplication(serverUrl: String): CreateApplicationResponseJson =
    client.post("https://${serverUrl}/api/v1/apps") {
      contentType(ContentType.Application.Json)
      setBody(
        CreateApplicationRequestJson(
          "Mastadon Clone App",
          "com.example.mastadonclone://callback",
          "read write push",
          "https://${settings.appState.userServerUrl}"
        )
      )
    }.body()

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    return try {
      client.get("https://$serverUrl/api/v2/instance").body()
    } catch (ex: Exception) {
      Logger.e("Api Exception", ex)
      null
    }
  }

}
