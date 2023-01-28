package com.matrix159.mastadonclone

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.matrix159.mastadonclone.presentation.ui.MastadonApp
import com.matrix159.mastadonclone.shared.viewmodel.AuthState.*
import com.matrix159.mastadonclone.shared.viewmodel.AuthStatus
import com.matrix159.mastadonclone.shared.viewmodel.DKMPViewModel
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.logout
import net.openid.appauth.*
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.login
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.matrix159.mastadonclone.shared.viewmodel.screens.Screen
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.loginComplete
import kotlinx.coroutines.flow.distinctUntilChanged

class MainActivity : ComponentActivity() {
  private lateinit var model: DKMPViewModel

  private lateinit var authService: AuthorizationService
  private var clientId: String? = null
  private var clientSecret: String? = null
  private val serviceConfig = AuthorizationServiceConfiguration(
    Uri.parse("https://androiddev.social/oauth/authorize"),  // authorization endpoint
    Uri.parse("https://androiddev.social/oauth/token"), // token endpoint
    //Uri.parse("https://androiddev.social/api/v1/apps") // client registration endpoint
  )


  private val authState = AuthState(serviceConfig)

  private val authorizationResultLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
      val data = activityResult.data
      val response = data?.let { AuthorizationResponse.fromIntent(data) }
      val exception = AuthorizationException.fromIntent(data)
      // TODO: Check for exceptions during authorization?
      authState.update(response, exception)

      response?.also {
        authService.performTokenRequest(
          response.createTokenExchangeRequest(),
          // TODO: DON'T THE SECRET
          ClientSecretPost(clientSecret ?: "")
        ) { resp, ex ->
          authState.update(resp, ex)
          if (ex != null) {
            model.navigation.events.logout()
          } else {
            model.navigation.events.loginComplete()
          }

        }
        // ... process the response or exception ...
      }
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    authService = AuthorizationService(this)

    model = (application as MastadonApplication).model
    model.stateFlow
      .distinctUntilChanged { old, new ->
        old.authState == new.authState
      }
      .onEach {
        when (it.authState.authStatus) {
          AuthStatus.Authenticating -> {

            // TODO Check for if clientId or clientSecret wasn't passed through
            val authRequest = AuthorizationRequest.Builder(
              serviceConfig,  // the authorization service configuration
              it.authState.clientId ?: "",  // the client ID, typically pre-registered and static
              ResponseTypeValues.CODE,  // the response_type value: we want a code
              Uri.parse("com.example.mastadonclone://callback"/*"urn:ietf:wg:oauth:2.0:oob"*/)
            )
              .setScope("read write push")
              .build()

            // TODO: Store the redirect uri and scope above into app state
            val authIntent = authService.getAuthorizationRequestIntent(authRequest)
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
