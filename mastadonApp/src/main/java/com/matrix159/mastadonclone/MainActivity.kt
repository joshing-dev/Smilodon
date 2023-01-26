package com.matrix159.mastadonclone

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Modifier
import com.matrix159.mastadonclone.presentation.ui.MastadonApp
import net.openid.appauth.*

class MainActivity : ComponentActivity() {

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
        val model = (application as MastadonApplication).model
        setContent {
           MastadonApp(model)
        }

        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        authorizationResultLauncher.launch(authIntent)
    }
}
