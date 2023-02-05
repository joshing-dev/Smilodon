package com.matrix159.mastadonclone.shared.datalayer.sources.localsettings

import com.matrix159.mastadonclone.shared.mvi.app.AppState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.string
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class MastadonSettings(s: Settings) {
  var appState: SettingsAppState
    get() =
      Json.decodeFromString(_appState)
    set(value) {
      _appState = Json.encodeToString(value)
    }
  private var _appState: String by s.string("authState", Json.encodeToString(SettingsAppState()))

  //var savedLevel1URI by s.string(defaultValue = Level1Navigation.LoginScreen.screenIdentifier.URI)
}

@Serializable
data class SettingsAppState(
  val accessToken: String? = null,
  val userServerUrl: String? = null,
  val clientId: String? = null,
  val clientSecret: String? = null,
  val redirectUri: String? = null,
)
