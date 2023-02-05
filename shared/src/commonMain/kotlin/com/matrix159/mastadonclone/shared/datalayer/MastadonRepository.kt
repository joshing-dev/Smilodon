package com.matrix159.mastadonclone.shared.datalayer

import com.matrix159.mastadonclone.shared.datalayer.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.datalayer.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.datalayer.sources.localsettings.MastadonSettings
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.MastadonApiRemoteDataSource
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.MastadonRemoteDataSource
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
class MastadonRepository(val settings: Settings = Settings()): Repository {

  internal val mastadonSettings: MastadonSettings by lazy { MastadonSettings(settings) }
  private val mastadonApi: MastadonRemoteDataSource by lazy {
    MastadonApiRemoteDataSource(
      mastadonSettings
    )
  }

  // TODO: Create domain layer data models so we aren't using the JSON models directly for below functions
  override suspend fun getClientApplication(serverUrl: String): MastadonApiApplication =
    withContext(Dispatchers.Default) {
//      if (
//        mastadonSettings.appState.userServerUrl != null &&
//        mastadonSettings.appState.clientId != null &&
//        mastadonSettings.appState.clientSecret != null &&
//        mastadonSettings.appState.redirectUri != null
//
//        ) {
//        MastadonApiApplication(
//          serverUrl = mastadonSettings.appState.userServerUrl!!,
//          clientId = mastadonSettings.appState.clientId!!,
//          clientSecret = mastadonSettings.appState.clientSecret!!,
//          redirectUri = mastadonSettings.appState.redirectUri!!,
//        )
//      } else {
      // If values aren't stored locally, retrieve and store them
//        mastadonSettings.appState = mastadonSettings.appState.copy(
//          userServerUrl = serverUrl
//        )
      val apiResponse = mastadonApi.createClientApplication(serverUrl)
//        mastadonSettings.appState = mastadonSettings.appState.copy(
//          clientId = apiResponse.clientId,
//          clientSecret = apiResponse.clientSecret,
//          redirectUri = apiResponse.redirectUris,
//        )
      MastadonApiApplication(
        serverUrl = serverUrl,
        clientId = apiResponse.clientId,
        clientSecret = apiResponse.clientSecret,
        redirectUri = apiResponse.redirectUris,
      )
//      }
    }

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    return mastadonApi.getInstance(serverUrl)
  }

}