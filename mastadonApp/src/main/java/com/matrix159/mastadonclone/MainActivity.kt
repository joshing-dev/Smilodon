package com.matrix159.mastadonclone

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.matrix159.mastadonclone.presentation.ui.MastadonApp
import com.matrix159.mastadonclone.shared.mvi.app.AppEffect
import com.matrix159.mastadonclone.shared.mvi.app.AppState
import com.matrix159.mastadonclone.shared.mvi.app.appStore
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretPost
import net.openid.appauth.ResponseTypeValues
import timber.log.Timber

class MainActivity : ComponentActivity() {
  private lateinit var authService: AuthorizationService
  private lateinit var authState: AuthState
  private var clientId: String? = null
  private var clientSecret: String? = null

  private val appStore: AppStore by inject()
  private val authorizationResultLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
      val data = activityResult.data
      val response = data?.let { AuthorizationResponse.fromIntent(data) }
      val authorizationException = AuthorizationException.fromIntent(data)
      if (authorizationException != null) {
        Timber.d("There was an exception during authorization.", authorizationException)
        return@registerForActivityResult
      }
      authState.update(response, authorizationException)

      response?.also {
        authService.performTokenRequest(
          response.createTokenExchangeRequest(),
          ClientSecretPost(clientSecret ?: "")
        ) { resp, ex ->
          authState.update(resp, ex)
          Timber.d("Client secret: ${authState.accessToken}")
          if (ex != null) {
            Timber.e("There was an exception during authentication.", ex)
            lifecycleScope.launch {
              appStore.dispatchEffect(AppEffect.Logout)
            }
          } else {
            val accessToken = authState.accessToken
            if (accessToken != null) {
              lifecycleScope.launch {
                appStore.dispatchEffect(AppEffect.Login(accessToken))
              }
            } else {
              Timber.e("No access token was available during authentication.")
            }
          }
        }
      }
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)
    authService = AuthorizationService(this)
    reactToAppState()

    setContent {
      MastadonApp()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    authService.dispose()
  }

  private fun reactToAppState() {
    appStore.state
      .distinctUntilChanged { old, new ->
        old == new
      }
      .onEach { appState ->
        when (appState) {
          is AppState.Authenticating -> {
            val serviceConfig = AuthorizationServiceConfiguration(
              Uri.parse("https://${appState.userServerUrl}/oauth/authorize"),  // authorization endpoint
              Uri.parse("https://${appState.userServerUrl}/oauth/token"), // token endpoint
            )

            authState = AuthState(serviceConfig)

            val authRequest = AuthorizationRequest.Builder(
              serviceConfig,  // the authorization service configuration
              appState.clientId,  // the client ID, typically pre-registered and static
              ResponseTypeValues.CODE,  // the response_type value: we want a code
              Uri.parse(appState.redirectUri)
            )
              .setScope("read write push")
              .build()

            val authIntent = authService.getAuthorizationRequestIntent(authRequest)
            // Store into variables to use when we launch back into the app
            clientId = appState.clientId
            clientSecret = appState.clientSecret
            authorizationResultLauncher.launch(authIntent)
          }
          is AppState.LoggedIn -> {
            //model.navigation.navigate(Screen.HomeFeed)
          }
          is AppState.NotLoggedIn -> {
            //model.navigation.navigate(Screen.LoginScreen)
          }
          else -> {}
        }
      }
      .launchIn(lifecycleScope)
  }
}
