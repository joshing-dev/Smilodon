package com.matrix159.mastadonclone

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.matrix159.mastadonclone.presentation.ui.MastadonApp
import com.matrix159.mastadonclone.shared.viewmodel.DKMPViewModel
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.logout
import net.openid.appauth.*
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.login
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.logout

class MainActivity : ComponentActivity() {
    private lateinit var model: DKMPViewModel

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
                    if (ex != null) {
                        model.navigation.events.logout()
                    } else {
                        model.navigation.events.login()
                    }

                }
                // ... process the response or exception ...
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (application as MastadonApplication).model

        authService = AuthorizationService(this)
        setContent {
           MastadonApp(model)
        }

        val authIntent = authService.getAuthorizationRequestIntent(authRequest)
        authorizationResultLauncher.launch(authIntent)
    }
}
