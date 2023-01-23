package com.example.presentation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.ui.components.HomeFeed
import com.example.presentation.ui.theme.MastadonTheme
import net.openid.appauth.*


class MainActivity : ComponentActivity() {

  private val rcAuthCode = 50

  private lateinit var authService: AuthorizationService
  private val serviceConfig = AuthorizationServiceConfiguration(
    Uri.parse("https://androiddev.social/oauth/authorize"),  // authorization endpoint
    Uri.parse("https://androiddev.social/oauth/token"), // token endpoint
    //Uri.parse("https://androiddev.social/api/v1/apps") // client registration endpoint
  )

  private val authRequest = AuthorizationRequest.Builder(
    serviceConfig,  // the authorization service configuration
    "PEzo9syVSIc8-fhHiKtVf5dg47bF1DQHf5lX3t1lXDA",  // the client ID, typically pre-registered and static
    ResponseTypeValues.CODE,  // the response_type value: we want a code
    Uri.parse("com.example.mastadonclone://callback"/*"urn:ietf:wg:oauth:2.0:oob"*/)
  )
    .setScope("read write push")
    .build()


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
          ClientSecretPost("SECRET HERE")
        ) { resp, ex ->
          authState.update(resp, ex)
        }
        // ... process the response or exception ...
      }
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    authService = AuthorizationService(this)
    setContent {
      MastadonTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.surface
        ) {
          HomeFeed()
          //GreetingView(Greeting().greet())
        }
      }
    }

    val authIntent = authService.getAuthorizationRequestIntent(authRequest)
    authorizationResultLauncher.launch(authIntent)
  }
}


@Composable
fun GreetingView(text: String) {
  Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
  MastadonTheme {
    GreetingView("Hello, Android!")
  }
}
