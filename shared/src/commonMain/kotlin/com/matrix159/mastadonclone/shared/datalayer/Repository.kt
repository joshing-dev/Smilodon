package com.matrix159.mastadonclone.shared.datalayer

import com.matrix159.mastadonclone.shared.datalayer.sources.runtimecache.CacheObjects
import com.matrix159.mastadonclone.shared.datalayer.sources.webservices.ApiClient
import kotlinx.coroutines.*

class Repository(/*val sqlDriver: SqlDriver, val settings: Settings = Settings(),*/ val useDefaultDispatcher: Boolean = true) {

    internal val webservices by lazy { ApiClient() }
    //internal val localDb by lazy { LocalDb(sqlDriver) }
    //internal val localSettings by lazy { MySettings(settings) }
    internal val runtimeCache get() = CacheObjects

    // we run each repository function on a Dispatchers.Default coroutine
    // we pass useDefaultDispatcher=false just for the TestRepository instance
    suspend fun <T> withRepoContext (block: suspend () -> T) : T {
        return if (useDefaultDispatcher) {
            withContext(Dispatchers.Default) {
                block()
            }
        } else {
            block()
        }
    }

}