package com.matrix159.mastadonclone.shared.mvi.screens.homefeed

import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.Store

private val actionReducer: ActionReducer<HomeFeedState, HomeFeedActions> = { state, action ->
  when (action) {
    is HomeFeedActions.StartLoading -> {
      state.copy(isLoading = true)
    }
    is HomeFeedActions.StopLoading -> {
      state.copy(isLoading = false)
    }
    is HomeFeedActions.ShowError -> state.copy(error = action.errorMessage)
    is HomeFeedActions.UpdatePosts -> state.copy(homeFeedPosts = action.homeFeedPosts, error = null)
  }
}

private val effectHandler: EffectHandler<HomeFeedState, HomeFeedActions, HomeFeedEffects> =
  { _, effect, dispatcher ->
    when (effect) {
      HomeFeedEffects.LoadPosts -> {
        dispatcher.dispatchAction(
          HomeFeedActions.UpdatePosts(
            listOf(HomeFeedPost("Josh Eldridge", "antherha"))
          )
        )
      }
      HomeFeedEffects.Init -> {
        dispatcher.dispatchAction(
          HomeFeedActions.UpdatePosts(
            listOf(
              HomeFeedPost("Someone here", "antherha"),
              HomeFeedPost("Another person", "asdalsdkaldsk")
            )
          )
        )
      }
    }
  }

val homeFeedStore by lazy { Store(HomeFeedState(isLoading = true), actionReducer, effectHandler) }
