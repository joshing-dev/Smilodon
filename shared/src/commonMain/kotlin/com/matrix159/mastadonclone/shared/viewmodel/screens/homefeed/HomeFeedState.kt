package com.matrix159.mastadonclone.shared.viewmodel.screens.homefeed

import com.matrix159.mastadonclone.shared.viewmodel.ScreenState

data class HomeFeedState (
  val isLoading : Boolean = false,
  val posts : List<Post> = emptyList(),
): ScreenState

/********** property classes **********/

data class Post(
  val author: String,
  val content: String
)