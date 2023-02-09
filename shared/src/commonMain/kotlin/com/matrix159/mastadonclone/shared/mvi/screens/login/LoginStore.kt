package com.matrix159.mastadonclone.shared.mvi.screens.login

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.di.RepositoryComponent
import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.Store
import com.matrix159.mastadonclone.shared.mvi.app.AppEffect
import com.matrix159.mastadonclone.shared.mvi.app.appStore

private val actionReducer: ActionReducer<LoginScreenState, LoginScreenAction> = { state, action ->
  when (state) {
    LoginScreenState.Error -> state
    is LoginScreenState.BaseState -> {
      when (action) {
        is LoginScreenAction.ServerUrlUpdated -> state.copy(
          serverUrl = action.serverUrl,
          serverTitle = null,
          serverDescription = null
        )
        LoginScreenAction.StartLoading -> state.copy(loadingIndicatorShown = true)
        LoginScreenAction.StopLoading -> state.copy(loadingIndicatorShown = false)
        is LoginScreenAction.ServerFound -> state.copy(
          serverTitle = action.serverTitle,
          serverDescription = action.serverDescription
        )
      }
    }
  }
}

private val effectHandler: EffectHandler<LoginScreenState, LoginScreenAction, LoginScreenEffect> =
  { _, effect, dispatcher ->
    when (effect) {
      is LoginScreenEffect.Login -> {
        val repository: Repository = RepositoryComponent.repository
        val clientAppDetails: MastadonApiApplication =
          repository.getClientApplication(effect.serverUrl)
        appStore.dispatchEffect(
          AppEffect.StartAuthentication(
            userServerUrl = clientAppDetails.serverUrl,
            clientId = clientAppDetails.clientId,
            clientSecret = clientAppDetails.clientSecret,
            redirectUri = clientAppDetails.redirectUri,
          )
        )
      }
      is LoginScreenEffect.SearchForServer -> {
        dispatcher.dispatchAction(LoginScreenAction.StartLoading)
        val repository: Repository = RepositoryComponent.repository
        val response: InstanceResponseJson? = repository.getInstance(effect.serverUrl)
        if (response != null) {
          dispatcher.dispatchAction(
            LoginScreenAction.ServerFound(
              serverTitle = response.title,
              serverDescription = response.description
            )
          )
        }
        dispatcher.dispatchAction(LoginScreenAction.StopLoading)
      }
    }
  }

val loginStore by lazy { Store(LoginScreenState.BaseState(), actionReducer, effectHandler) }
