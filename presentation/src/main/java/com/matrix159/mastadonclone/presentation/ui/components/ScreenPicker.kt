package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.loadHomeFeed

@Composable
fun ScreenPicker(
  navigation: Navigation,
) {
  val stateProvider = remember { navigation.stateProvider }
  val events = remember { navigation.events }
  val currentScreenIdentifier = remember(navigation.currentScreenIdentifier) { navigation.currentScreenIdentifier }
  when (currentScreenIdentifier.screen) {
    Screen.HomeFeed ->
      HomeFeedScreen(
        stateProvider.get(currentScreenIdentifier),
        loadNew = { events.loadHomeFeed() }
      )
//      CountriesListScreen(
//        countriesListState = stateProvider.get(screenIdentifier),
//        onListItemClick = { navigate(CountryDetail, CountryDetailParams(countryName = it)) },
//        onFavoriteIconClick = { navigation.events.selectFavorite(countryName = it) },
//      )
//    CountryDetail ->
//      CountryDetailScreen(
//        countryDetailState = stateProvider.get(screenIdentifier)
//      )
    else -> { Text("No screen found")}
  }

}