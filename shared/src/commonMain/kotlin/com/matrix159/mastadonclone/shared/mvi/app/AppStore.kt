package com.matrix159.mastadonclone.shared.mvi.app

import com.matrix159.mastadonclone.shared.data.MastadonRepository
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState
import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.Store

private val actionReducer: ActionReducer<AppState, AppAction> = { state, action ->
  when (action) {
    is AppAction.StartAuthentication -> {
      AppState.Authenticating(
        userServerUrl = action.userServerUrl,
        clientId = action.clientId,
        clientSecret = action.clientSecret,
        redirectUri = action.redirectUri
      )
    }
    is AppAction.LoginSuccess -> AppState.LoggedIn(
      accessToken = action.accessToken
    )
    AppAction.Logout -> AppState.NotLoggedIn
  }
}

private val effectHandler: EffectHandler<AppState, AppAction, AppEffect> =
  { state, effect, dispatcher ->
    when (effect) {
      AppEffect.SaveAppState -> {
        val repository = MastadonRepository()
        when (state) {
          is AppState.LoggedIn -> {
            repository.mastadonSettings.appState = repository.mastadonSettings.appState.copy(
              accessToken = state.accessToken,
            )
          }
          is AppState.Authenticating -> {
            repository.mastadonSettings.appState = repository.mastadonSettings.appState.copy(
              userServerUrl = state.userServerUrl,
              clientId = state.clientId,
              clientSecret = state.clientSecret,
              redirectUri = state.redirectUri,
            )
          }
          AppState.NotLoggedIn -> {
            repository.mastadonSettings.appState = SettingsAppState()
          }
        }
      }
      AppEffect.Startup -> {
        val repository = MastadonRepository()
        val settingsState = repository.mastadonSettings.appState
        if (settingsState.accessToken != null && settingsState.userServerUrl != null) {
          dispatcher.dispatchEffect(AppEffect.Login(settingsState.accessToken))
        } else {
          dispatcher.dispatchEffect(AppEffect.Logout)
        }
      }
      is AppEffect.StartAuthentication -> {
        dispatcher.dispatchAction(AppAction.StartAuthentication(
          userServerUrl = effect.userServerUrl,
          clientId = effect.clientId,
          clientSecret = effect.clientSecret,
          redirectUri = effect.redirectUri
        ))
        dispatcher.dispatchEffect(AppEffect.SaveAppState)
      }
      is AppEffect.Login -> {
        dispatcher.dispatchAction(AppAction.LoginSuccess(effect.accessToken))
        dispatcher.dispatchEffect(AppEffect.SaveAppState)
      }
      AppEffect.Logout ->  {
        dispatcher.dispatchAction(AppAction.Logout)
        dispatcher.dispatchEffect(AppEffect.SaveAppState)
      }
    }
  }

val appStore by lazy { Store(AppState.NotLoggedIn, actionReducer, effectHandler) }