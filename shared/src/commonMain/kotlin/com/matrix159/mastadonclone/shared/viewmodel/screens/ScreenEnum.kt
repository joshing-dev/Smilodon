package com.matrix159.mastadonclone.shared.viewmodel.screens

import com.matrix159.mastadonclone.shared.viewmodel.Navigation
import com.matrix159.mastadonclone.shared.viewmodel.ScreenIdentifier
import com.matrix159.mastadonclone.shared.viewmodel.screens.countrieslist.initCountriesList
import com.matrix159.mastadonclone.shared.viewmodel.screens.countrydetail.initCountryDetail

// DEFINITION OF ALL SCREENS IN THE APP

enum class Screen(
  val asString: String,
  val navigationLevel : Int = 1,
  val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
  val stackableInstances : Boolean = false,
) {
  CountriesList("countrieslist", 1, { initCountriesList(it.params()) }, true),
  CountryDetail("country", 2, { initCountryDetail(it.params()) }),
}