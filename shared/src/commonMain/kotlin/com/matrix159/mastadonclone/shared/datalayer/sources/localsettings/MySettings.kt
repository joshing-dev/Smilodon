package com.matrix159.mastadonclone.shared.datalayer.sources.localsettings

import com.matrix159.mastadonclone.shared.viewmodel.screens.Level1Navigation
import com.russhwolf.settings.Settings
import com.russhwolf.settings.long
import com.russhwolf.settings.string


class MastadonSettings(s: Settings) {
  // here we define all our local settings properties,
  // by using the MultiplatformSettings library delegated properties
  var clientId by s.string(defaultValue = "")
  var clientSecret by s.string(defaultValue = "")
  var redirectUri by s.string(defaultValue = "")
  var listCacheTimestamp by s.long(defaultValue = 0)
  var savedLevel1URI by s.string(defaultValue = Level1Navigation.LoginScreen.screenIdentifier.URI)
}
