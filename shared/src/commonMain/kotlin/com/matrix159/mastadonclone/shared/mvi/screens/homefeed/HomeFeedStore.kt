package com.matrix159.mastadonclone.shared.mvi.screens.homefeed

import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.StoreImpl


class HomeFeedStore(
  initialState: HomeFeedState = HomeFeedState(isLoading = true),
  actionHandler: ActionReducer<HomeFeedState, HomeFeedActions> = { state, action ->
    when (action) {
      is HomeFeedActions.StartLoading -> {
        state.copy(isLoading = true)
      }
      is HomeFeedActions.StopLoading -> {
        state.copy(isLoading = false)
      }
      is HomeFeedActions.ShowError -> state.copy(isLoading = false, error = action.errorMessage)
      is HomeFeedActions.UpdatePosts -> state.copy(
        isLoading = false,
        homeFeedPosts = action.homeFeedPosts,
        error = null
      )
    }
  },
  effectHandler: EffectHandler<HomeFeedState, HomeFeedActions, HomeFeedEffects> = { _, effect, dispatcher ->
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
  },
) : StoreImpl<HomeFeedState, HomeFeedActions, HomeFeedEffects>(
  initialState,
  actionHandler,
  effectHandler
)
