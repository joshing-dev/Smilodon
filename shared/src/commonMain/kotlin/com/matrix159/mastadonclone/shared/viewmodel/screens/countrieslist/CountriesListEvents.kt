package com.matrix159.mastadonclone.shared.viewmodel.screens.countrieslist

import com.matrix159.mastadonclone.shared.datalayer.functions.getFavoriteCountriesMap
import com.matrix159.mastadonclone.shared.viewmodel.Events


/********** EVENT functions, called directly by the UI layer **********/

fun Events.selectFavorite(countryName: String) = screenCoroutine {
    val favorites = dataRepository.getFavoriteCountriesMap(alsoToggleCountry = countryName)
    // update state with new favorites map, after toggling the value for the specified country
    stateManager.updateScreen<CountriesListState> {
        it.copy(favoriteCountries = favorites)
    }
}
