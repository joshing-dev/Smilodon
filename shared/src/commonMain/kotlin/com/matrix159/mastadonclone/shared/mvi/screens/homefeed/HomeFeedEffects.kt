package com.matrix159.mastadonclone.shared.mvi.screens.homefeed

sealed interface HomeFeedEffects {
  object Init : HomeFeedEffects
  object LoadPosts : HomeFeedEffects
}
