package com.matrix159.mastadonclone.shared.datalayer.sources.localsettings

import com.matrix159.mastadonclone.shared.viewmodel.AuthState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.string
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class MastadonSettings(s: Settings) {
  var authState: AuthState
    get() =
      Json.decodeFromString(_authState)
    set(value) {
      _authState = Json.encodeToString(value)
    }
  private var _authState: String by s.string("authState", Json.encodeToString(AuthState()))

  //var savedLevel1URI by s.string(defaultValue = Level1Navigation.LoginScreen.screenIdentifier.URI)
}
