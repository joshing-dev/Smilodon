package com.matrix159.mastadonclone

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.matrix159.mastadonclone.presentation.ui.MastadonApp
import com.matrix159.mastadonclone.shared.viewmodel.AuthStatus
import com.matrix159.mastadonclone.shared.viewmodel.DKMPViewModel
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.logout
import net.openid.appauth.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.loginComplete
import kotlinx.coroutines.flow.distinctUntilChanged
import timber.log.Timber

class MainActivity : ComponentActivity() {
  private lateinit var model: DKMPViewModel

  private lateinit var authService: AuthorizationService
  private var clientId: String? = null
  private var clientSecret: String? = null


  private lateinit var authState: AuthState

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
            model.navigation.events.logout()
          } else {
            val accessToken = authState.accessToken
            if (accessToken != null) {
              model.navigation.events.loginComplete(accessToken)
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

    model = (application as MastadonApplication).model
    model.stateFlow
      .distinctUntilChanged { old, new ->
        old.authState.authStatus == new.authState.authStatus
      }
      .onEach {
        when (it.authState.authStatus) {
          AuthStatus.Authenticating -> {
            val serviceConfig = AuthorizationServiceConfiguration(
              Uri.parse("https://${it.authState.userServerUrl}/oauth/authorize"),  // authorization endpoint
              Uri.parse("https://${it.authState.userServerUrl}/oauth/token"), // token endpoint
            )

            authState = AuthState(serviceConfig)

            val authRequest = AuthorizationRequest.Builder(
              serviceConfig,  // the authorization service configuration
              it.authState.clientId ?: "",  // the client ID, typically pre-registered and static
              ResponseTypeValues.CODE,  // the response_type value: we want a code
              Uri.parse(it.authState.redirectUri)
            )
              .setScope("read write push")
              .build()

            val authIntent = authService.getAuthorizationRequestIntent(authRequest)
            // Store into variables to use when we launch back into the app
            clientId = it.authState.clientId
            clientSecret = it.authState.clientSecret
            authorizationResultLauncher.launch(authIntent)
          }
          AuthStatus.LoggedIn -> {
            model.navigation.navigate(Screen.HomeFeed)
          }
          AuthStatus.NotLoggedIn -> {
            model.navigation.navigate(Screen.LoginScreen)
          }
          else -> {}
        }
      }
      .launchIn(lifecycleScope)

    setContent {
      MastadonApp(model)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    authService.dispose()
  }
}
