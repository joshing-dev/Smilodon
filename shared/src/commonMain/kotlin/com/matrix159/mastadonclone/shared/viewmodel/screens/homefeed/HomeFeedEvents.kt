package com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed

import com.matrix159.mastadonclone.shared.viewmodel.Events

/********** EVENT functions, called directly by the UI layer **********/

fun Events.loadHomeFeed() = screenCoroutine {
  val posts = listOf(Post("New author", "New post"))
  //val favorites = dataRepository.getFavoriteCountriesMap(alsoToggleCountry = countryName)
  // update state with new favorites map, after toggling the value for the specified country
  stateManager.updateScreen<HomeFeedState> {
    it.copy(posts = posts)
  }
}