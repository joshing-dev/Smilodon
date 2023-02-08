package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix159.mastadonclone.presentation.R
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
  state: LoginScreenState,
  serverTextChange: (String) -> Unit,
  login: (serverUrl: String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .padding(16.dp)
      .imePadding()
  ) {
    Text(
      text = stringResource(R.string.app_name),
      style = MaterialTheme.typography.headlineLarge
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = stringResource(R.string.login_intro_text),
      style = MaterialTheme.typography.bodyLarge
    )

    Spacer(modifier = Modifier.height(16.dp))

    when (state) {
      LoginScreenState.Error -> {
        Text("An error occurred")
      }
      is LoginScreenState.BaseState -> {
        TextField(
          value = state.serverUrl,
          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = false
          ),
          onValueChange = { value: String ->
            serverTextChange(value)
          },
          singleLine = true,
          leadingIcon = {
            Icon(
              Icons.Default.Search,
              contentDescription = stringResource(R.string.login_content_search_for_server)
            )
          },
          trailingIcon = {
            if (state.loadingIndicatorShown) {
              CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
          },
          label = {
            Text(text = stringResource(R.string.login_server_url))
          },
          modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(vertical = 4.dp))


        if (state.serverUrl.isNotEmpty()) {
          ElevatedCard(
            modifier = Modifier.fillMaxWidth()
          ) {
            Column {
              Text(
                text = state.serverTitle ?: state.serverUrl,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
              )
              Text(
                text = state.serverDescription
                  ?: stringResource(R.string.login_no_server_info_found),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
              )

              if (state.serverTitle != null) {
                FilledTonalButton(
                  onClick = { login(state.serverUrl) },
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ) {
                  Text(
                    text = stringResource(R.string.login)
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun LoginScreenPreview() {
  MastadonTheme {
    Surface {
      LoginScreen(
        state = LoginScreenState.BaseState(
          serverUrl = "androiddev.social",
          serverTitle = "androiddev.social",
          serverDescription = "This is a test description"
        ),
        serverTextChange = {},
        login = {}
      )
    }
  }
}
