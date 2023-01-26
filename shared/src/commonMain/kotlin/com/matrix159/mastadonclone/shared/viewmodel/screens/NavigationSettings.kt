package com.matrix159.mastadonclone.shared.viewmodel.screens

import com.matrix159.mastadonclone.shared.viewmodel.ScreenIdentifier
import com.matrix159.mastadonclone.shared.viewmodel.screens.countrieslist.CountriesListParams
import com.matrix159.mastadonclone.shared.viewmodel.screens.countrieslist.CountriesListType
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.HomeFeedParams

// CONFIGURATION SETTINGS

object NavigationSettings {
  val homeScreen = Level1Navigation.HomeFeed // the start screen should be specified here
  val saveLastLevel1Screen = true
  val alwaysQuitOnHomeScreen = true
}


// LEVEL 1 NAVIGATION OF THE APP

enum class Level1Navigation(val screenIdentifier: ScreenIdentifier, val rememberVerticalStack: Boolean = false) {
  AllCountries(ScreenIdentifier.get(Screen.CountriesList, CountriesListParams(listType = CountriesListType.ALL)), true),
  FavoriteCountries(ScreenIdentifier.get(Screen.CountriesList, CountriesListParams(listType = CountriesListType.FAVORITES)), true),
  HomeFeed(ScreenIdentifier.get(Screen.HomeFeed, HomeFeedParams()), rememberVerticalStack = true)
}