package com.matrix159.mastadonclone.shared.data.sources.localsettings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.string
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface MastadonSettings {
  var appState: SettingsAppState
}
class LocalMastadonSettings(s: Settings): MastadonSettings {
  override var appState: SettingsAppState
    get() =
      Json.decodeFromString(_appState)
    set(value) {
      _appState = Json.encodeToString(value)
    }
  private var _appState: String by s.string("authState", Json.encodeToString(SettingsAppState()))
}

@Serializable
data class SettingsAppState(
  val accessToken: String? = null,
  val userServerUrl: String? = null,
  val clientId: String? = null,
  val clientSecret: String? = null,
  val redirectUri: String? = null,
)
