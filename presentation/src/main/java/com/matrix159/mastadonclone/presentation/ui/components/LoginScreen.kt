package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix159.mastadonclone.presentation.R
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.LoginScreenState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
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

    // Loading indicator logic derived from login screen state
    var showServerListLoadingIndicator by remember { mutableStateOf(false) }
    LaunchedEffect(state.isLoadingServerList) {
      if (!state.isLoadingServerList) {
        showServerListLoadingIndicator = false
      }
    }

    val serverFlow: MutableStateFlow<String> = remember { MutableStateFlow("") }
    LaunchedEffect(true) {
      serverFlow
        .filter { it.isNotEmpty() }
        .onEach { showServerListLoadingIndicator = true }
        .debounce(300)
        .onEach { serverTextChange(it) }
        .launchIn(this)
    }

    var serverUrl by remember { mutableStateOf("") }
    TextField(
      value = serverUrl,
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        autoCorrect = false
      ),
      onValueChange = { value: String ->
        serverUrl = value
        serverFlow.value = value
      },
      singleLine = true,
      leadingIcon = {
        Icon(
          Icons.Default.Search,
          contentDescription = stringResource(R.string.login_content_search_for_server)
        )
      },
      trailingIcon = {
        if (showServerListLoadingIndicator) {
          CircularProgressIndicator(modifier = Modifier.size(24.dp))
        }
      },
      label = {
        Text(text = stringResource(R.string.login_server_url))
      },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.padding(vertical = 4.dp))


    if (serverUrl.isNotEmpty()) {
      ElevatedCard(
        modifier = Modifier.fillMaxWidth()
      ) {
        Column {
          Text(
            text = state.serverName ?: serverUrl,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
          )
          Text(
            text = state.serverDescription ?: stringResource(R.string.login_no_server_info_found),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
          )

          if (state.serverName != null) {
            FilledTonalButton(
              onClick = { login(serverUrl) },
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

@Preview
@Composable
fun LoginScreenPreview() {
  MastadonTheme {
    Surface {
      LoginScreen(
        state = LoginScreenState(
          serverName = "androiddev.social",
          serverDescription = "This is a test description"
        ),
        serverTextChange = {},
        login = {}
      )
    }
  }
}