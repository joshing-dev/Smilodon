package com.matrix159.mastadonclone.shared.mvi.screens.homefeed


sealed interface HomeFeedActions {
  object StartLoading: HomeFeedActions
  object StopLoading: HomeFeedActions
  data class ShowError(val errorMessage: String): HomeFeedActions
  data class UpdatePosts(val homeFeedPosts: List<HomeFeedPost>): HomeFeedActions
}



