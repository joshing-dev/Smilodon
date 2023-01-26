package com.matrix159.mastadonclone.shared.viewmodel.screens

import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.ScreenIdentifier
import com.matrix159.mastadonclone.shared.viewmodel.screens.countrieslist.initCountriesList
import com.matrix159.mastadonclone.shared.viewmodel.screens.countrydetail.initCountryDetail
import com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed.initHomeFeed

// DEFINITION OF ALL SCREENS IN THE APP

enum class Screen(
  val asString: String,
  val navigationLevel : Int = 1,
  val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
  val stackableInstances : Boolean = false,
) {
  CountriesList("countrieslist", 1, { screenIdentifier -> initCountriesList(screenIdentifier.params()) }, true),
  CountryDetail("country", 2, { screenIdentifier -> initCountryDetail(screenIdentifier.params()) }),
  HomeFeed("homefeed", 1, { screenIdentifier -> initHomeFeed(screenIdentifier.params()) })
}