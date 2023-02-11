package com.matrix159.mastadonclone.shared.mvi.app

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState
import com.matrix159.mastadonclone.shared.mvi.ActionReducer
import com.matrix159.mastadonclone.shared.mvi.EffectHandler
import com.matrix159.mastadonclone.shared.mvi.StoreImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AppStore/*<
  S : LoginScreenState,
  A : LoginScreenAction,
  E: LoginScreenEffect
>*/(
  repository: Repository,
  initialState: AppState = AppState.NotLoggedIn,
  actionHandler: ActionReducer<AppState, AppAction> = { _, action ->
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
  },
  effectHandler: EffectHandler<AppState, AppAction, AppEffect> = { state, effect, dispatcher ->
    when (effect) {
      AppEffect.SaveAppState -> {
        val savedAppState = repository.getSavedAppState()
        when (state) {
          is AppState.LoggedIn -> {
            repository.saveAppState(
              savedAppState.copy(
                accessToken = state.accessToken,
              )
            )
          }
          is AppState.Authenticating -> {
            repository.saveAppState(
              SettingsAppState(
                userServerUrl = state.userServerUrl,
                clientId = state.clientId,
                clientSecret = state.clientSecret,
                redirectUri = state.redirectUri,
              )
            )
          }
          AppState.NotLoggedIn -> {
            repository.saveAppState(SettingsAppState())
          }
        }
      }
      AppEffect.Startup -> {
        val settingsState = repository.getSavedAppState()
        if (settingsState.accessToken != null && settingsState.userServerUrl != null) {
          dispatcher.dispatchEffect(AppEffect.Login(settingsState.accessToken))
        } else {
          dispatcher.dispatchEffect(AppEffect.Logout)
        }
      }
      is AppEffect.StartAuthentication -> {
        dispatcher.dispatchAction(
          AppAction.StartAuthentication(
            userServerUrl = effect.userServerUrl,
            clientId = effect.clientId,
            clientSecret = effect.clientSecret,
            redirectUri = effect.redirectUri
          )
        )
        dispatcher.dispatchEffect(AppEffect.SaveAppState)
      }
      is AppEffect.Login -> {
        dispatcher.dispatchAction(AppAction.LoginSuccess(effect.accessToken))
        dispatcher.dispatchEffect(AppEffect.SaveAppState)
      }
      AppEffect.Logout -> {
        dispatcher.dispatchAction(AppAction.Logout)
        dispatcher.dispatchEffect(AppEffect.SaveAppState)
      }
    }
  }
) : StoreImpl<AppState, AppAction, AppEffect>(initialState, actionHandler, effectHandler), KoinComponent
