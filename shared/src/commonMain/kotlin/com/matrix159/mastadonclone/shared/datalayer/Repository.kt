package com.matrix159.mastadonclone.shared.datalayer

import com.matrix159.mastadonclone.shared.datalayer.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.datalayer.sources.localsettings.MastadonSettings
import com.matrix159.mastadonclone.shared.datalayer.sources.runtimecache.CacheObjects
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.MastadonApiRemoteDataSource
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.MastadonRemoteDataSource
import com.russhwolf.settings.Settings
import kotlinx.coroutines.*

class Repository(/*val sqlDriver: SqlDriver*,*/
  val settings: Settings = Settings(),
  val useDefaultDispatcher: Boolean = true
) {

  internal val mastadonApi: MastadonRemoteDataSource by lazy { MastadonApiRemoteDataSource() }
  internal val mastadonSettings: MastadonSettings by lazy { MastadonSettings(settings) }

  //internal val localDb by lazy { LocalDb(sqlDriver) }
  internal val runtimeCache get() = CacheObjects

  // we run each repository function on a Dispatchers.Default coroutine
  // we pass useDefaultDispatcher=false just for the TestRepository instance
  suspend fun <T> withRepoContext(block: suspend () -> T): T {
    return if (useDefaultDispatcher) {
      withContext(Dispatchers.Default) {
        block()
      }
    } else {
      block()
    }
  }

  suspend fun getClientApplication(): MastadonApiApplication = withContext(Dispatchers.Default) {
    if (
      mastadonSettings.clientId.isNotEmpty() &&
      mastadonSettings.clientSecret.isNotEmpty() &&
      mastadonSettings.redirectUri.isNotEmpty()
    ) {
      MastadonApiApplication(
        clientId = mastadonSettings.clientId,
        clientSecret = mastadonSettings.clientSecret,
        redirectUri = mastadonSettings.redirectUri,
      )
    } else {
      // If values aren't stored locally, retrieve and store them
      val apiResponse = mastadonApi.createClientApplication()
      mastadonSettings.clientId = apiResponse.clientId
      mastadonSettings.clientSecret = apiResponse.clientSecret
      mastadonSettings.redirectUri = apiResponse.redirectUris
      MastadonApiApplication(
        clientId = apiResponse.clientId,
        clientSecret = apiResponse.clientSecret,
        redirectUri = apiResponse.redirectUris,
      )
    }
  }

}