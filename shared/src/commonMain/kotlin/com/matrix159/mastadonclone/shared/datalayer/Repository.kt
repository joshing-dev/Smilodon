package com.matrix159.mastadonclone.shared.datalayer

import com.matrix159.mastadonclone.shared.datalayer.sources.runtimecache.CacheObjects
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.MastadonApiRemoteDataSource
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.MastadonRemoteDataSource
import com.matrix159.mastadonclone.shared.viewmodel.debugLogger
import kotlinx.coroutines.*

class Repository(/*val sqlDriver: SqlDriver, val settings: Settings = Settings(),*/ val useDefaultDispatcher: Boolean = true) {

  internal val mastadonApi: MastadonRemoteDataSource by lazy { MastadonApiRemoteDataSource() }

  //internal val localDb by lazy { LocalDb(sqlDriver) }
  //internal val localSettings by lazy { MySettings(settings) }
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

  suspend fun createClientApplication() = withContext(Dispatchers.Default) {
    val response = mastadonApi.createClientApplication()
    println(response.toString())
  }

}