package com.matrix159.mastadonclone.shared.mvi.screens.homefeed

import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.Status


data class HomeFeedState(
  val isLoading: Boolean = false,
  val error: String? = null,
  val homeFeedPosts: List<HomeFeedPost> = emptyList(),
)

data class HomeFeedPost(
  val status: Status,
)
