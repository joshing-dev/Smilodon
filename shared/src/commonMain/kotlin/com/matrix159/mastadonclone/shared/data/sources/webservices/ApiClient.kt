package com.matrix159.mastadonclone.shared.data.sources.webservices

import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Creates a Ktor HttpClient that is preconfigured with an access token.
 * ContentNegotiation, Logging, ResponseObserver, and DefaultRequest are installed.
 *
 * @param accessToken A bearer token to inject into requests via Authorization header.
 * @return [HttpClient]
 */
fun createApiClient(accessToken: String?): HttpClient = HttpClient {
  install(ContentNegotiation) {
    json(Json {
      ignoreUnknownKeys = true
      //explicitNulls = false
    })
  }

  install(Logging) {
    logger = object : Logger {
      override fun log(message: String) {
        co.touchlab.kermit.Logger.v("Logger Ktor => $message")
      }

    }
    level = LogLevel.INFO
  }

  install(ResponseObserver) {
    onResponse { response ->
      co.touchlab.kermit.Logger.d("HTTP status: ${response.status.value}")
    }
  }



  install(DefaultRequest) {
    header(HttpHeaders.ContentType, ContentType.Application.Json)
    if (accessToken != null) {
      header(HttpHeaders.Authorization, "Bearer $accessToken")
    }
  }
}
