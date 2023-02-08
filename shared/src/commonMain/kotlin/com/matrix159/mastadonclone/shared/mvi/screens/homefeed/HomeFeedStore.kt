package com.matrix159.mastadonclone.shared.mvi.screens.homefeed

import com.matrix159.mastadonclone.shared.data.MastadonRepository
import com.matrix159.mastadonclone.shared.data.Repository
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
                // TODO DIO
                dispatcher.dispatchAction(
                    HomeFeedActions.UpdatePosts(
                        MastadonRepository().getHomeTimelines().map { status ->
                            //TODO change this to just take the Account object
                            HomeFeedPost(
                                status = status
                            )
                        }
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

