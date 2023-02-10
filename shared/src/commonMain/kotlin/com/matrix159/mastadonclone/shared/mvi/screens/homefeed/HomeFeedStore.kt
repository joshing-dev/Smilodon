package com.matrix159.mastadonclone.shared.mvi.screens.homefeed

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.di.RepositoryComponent
import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.Store
import com.matrix159.mastadonclone.shared.mvi.StoreImpl
import com.matrix159.mastadonclone.shared.mvi.app.AppEffect
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenAction
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenEffect
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenState

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
      is HomeFeedActions.ShowError -> state.copy(error = action.errorMessage)
      is HomeFeedActions.UpdatePosts -> state.copy(
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

//val homeFeedStore by lazy { Store(HomeFeedState(isLoading = true), actionReducer, effectHandler) }
