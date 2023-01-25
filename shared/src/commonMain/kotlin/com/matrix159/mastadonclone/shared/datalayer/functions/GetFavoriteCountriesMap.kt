package com.matrix159.mastadonclone.shared.datalayer.functions

import com.matrix159.mastadonclone.shared.datalayer.Repository

suspend fun Repository.getFavoriteCountriesMap(alsoToggleCountry : String? = null): Map<String,Boolean> = withRepoContext {

    return@withRepoContext emptyMap()
//    // LOCAL DB operation, to toggle a country as favorite
//    if (alsoToggleCountry != null) {
//        localDb.toggleFavoriteCountry(alsoToggleCountry)
//    }
//
//    // RETURN a "trueMap" (i.e. a map whose values are always TRUE boolean):
//    // where the keys are the names of the favorite countries
//    localDb.getFavoriteCountriesMap()
}