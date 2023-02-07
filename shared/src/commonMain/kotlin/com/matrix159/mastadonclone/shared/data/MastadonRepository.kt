package com.matrix159.mastadonclone.shared.data

import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.data.sources.localsettings.MastadonSettings
import com.matrix159.mastadonclone.shared.data.sources.webservices.MastadonApiRemoteDataSource
import com.matrix159.mastadonclone.shared.data.sources.webservices.MastadonRemoteDataSource
import com.russhwolf.settings.Settings
import kotlinx.coroutines.*

/**
 * Main entry point into the data layer from the viewmodel layer.
 */
interface Repository {

  /**
   * Retrieves the OAuth client application data from Mastadon in order to then authenticate the user.
   * @param serverUrl Server URL that the user is logging into.
   */
  suspend fun getClientApplication(serverUrl: String): MastadonApiApplication

  /**
   * Checks for the presence of a Mastadon instance at the provided URL.
   * @param serverUrl Server URL that the user is logging into.
   */
  suspend fun getInstance(serverUrl: String): InstanceResponseJson?
}

/**
 * The current main repository used to retrieve and store information in the data layer.
 * @param settings A [Settings] class provided by the multiplatform-settings library.
 */
class MastadonRepository(val settings: Settings = Settings()) : Repository {

  internal val mastadonSettings: MastadonSettings by lazy { MastadonSettings(settings) }
  private val mastadonApi: MastadonRemoteDataSource by lazy {
    MastadonApiRemoteDataSource(
      mastadonSettings
    )
  }

  // TODO: Create domain layer data models so we aren't using the JSON models directly for below functions
  override suspend fun getClientApplication(serverUrl: String): MastadonApiApplication =
    withContext(Dispatchers.Default) {
      val apiResponse = mastadonApi.createClientApplication(serverUrl)
      MastadonApiApplication(
        serverUrl = serverUrl,
        clientId = apiResponse.clientId,
        clientSecret = apiResponse.clientSecret,
        redirectUri = apiResponse.redirectUris,
      )
    }

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    return mastadonApi.getInstance(serverUrl)
  }

}