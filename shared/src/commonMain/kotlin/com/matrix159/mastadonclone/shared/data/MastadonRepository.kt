package com.matrix159.mastadonclone.shared.data

import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.Status
import com.matrix159.mastadonclone.shared.data.sources.localsettings.MastadonSettings
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState
import com.matrix159.mastadonclone.shared.data.sources.webservices.MastadonRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

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
   * Checks for the presence of a Mastodon instance at the provided URL.
   * @param serverUrl Server URL that the user is logging into.
   */
  suspend fun getInstance(serverUrl: String): InstanceResponseJson?

  /**
   * Saves the app state to local storage
   */
  fun saveAppState(appState: SettingsAppState)

  /**
   * Retrieves the app state from local storage
   */
  fun getSavedAppState(): SettingsAppState

  /**
   *  GET /api/v1/timelines/home HTTP/1.1
   */
  suspend fun getHomeTimelines() : List<Status>
}

/**
 * The current main repository used to retrieve and store information in the data layer.
 * @param mastadonSettings The local settings interface.
 * @param mastadonApi The interface to communicate with the Mastadon API.
 */
internal class MastadonRepository(
  val mastadonSettings: MastadonSettings,
  val mastadonApi: MastadonRemoteDataSource
) : Repository, KoinComponent {

  override fun saveAppState(appState: SettingsAppState) {
    mastadonSettings.appState = appState
  }

  override fun getSavedAppState() = mastadonSettings.appState

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

  override suspend fun getHomeTimelines(): List<Status> {
    return mastadonApi.getHomeTimelines()
  }

}
