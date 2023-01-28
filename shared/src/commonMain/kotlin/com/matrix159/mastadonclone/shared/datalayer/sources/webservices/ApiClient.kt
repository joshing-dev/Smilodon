package com.matrix159.mastadonclone.shared.datalayer.sources.webservices

import com.matrix159.mastadonclone.shared.viewmodel.debugLogger
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun createApiClient(): HttpClient = HttpClient {
  install(ContentNegotiation) {
    json(Json {
      ignoreUnknownKeys = true
      //explicitNulls = false
    })
  }
  /* Ktor specific logging: reenable if needed to debug requests
  install(Logging) {
      logger = Logger.DEFAULT
      level = LogLevel.INFO
  }
  */

  install(Logging) {
    logger = object : Logger {
      override fun log(message: String) {
        //co.touchlab.kermit.Logger.v("Logger Ktor => $message")
      }

    }
    level = LogLevel.INFO
  }

  install(ResponseObserver) {
    onResponse { _ ->
      //co.touchlab.kermit.Logger.d("HTTP status: ${response.status.value}")
    }
  }

  install(DefaultRequest) {
    header(HttpHeaders.ContentType, ContentType.Application.Json)
  }
}


//  suspend inline fun <reified T : Any> getResponse(endpoint: String): T? {
//    val url = baseUrl + endpoint
//    try {
//      // please notice, Ktor Client is switching to a background thread under the hood
//      // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
//      val resp: T = client.get(url).body()
//      debugLogger.log("$url API SUCCESS")
//      return resp
//    } catch (e: Exception) {
//      debugLogger.log("$url API FAILED: " + e.message)
//    }
//    return null
//  }


