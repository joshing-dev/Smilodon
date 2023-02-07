package com.matrix159.mastadonclone.shared.mvi.screens.homefeed


data class HomeFeedState(
  val isLoading: Boolean = false,
  val error: String? = null,
  val homeFeedPosts: List<HomeFeedPost> = emptyList(),
)

data class HomeFeedPost(
  val author: String,
  val content: String,
  val avatar: String
)
