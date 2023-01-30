package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme
import com.matrix159.mastadonclone.shared.viewmodel.screens.login.LoginScreenState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun LoginScreen(
    state: LoginScreenState,
    serverTextChange: (String) -> Unit,
    login: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Mastadon Clone",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Log in with the server where you created your account.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        val serverFlow: MutableStateFlow<String> = remember { MutableStateFlow("") }
        LaunchedEffect(true) {
            serverFlow
                .debounce(400)
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
                Icon(Icons.Default.Search, contentDescription = "Search for server")
            },
            label = {
                Text(text = "Server URL")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(state.serverList) { serverName ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = serverName,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        )

        Spacer(
            modifier = Modifier
              .padding(vertical = 16.dp)
              .weight(1f)
        )

        FilledTonalButton(
            onClick = login,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Next"
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    MastadonTheme {
        Surface {
            LoginScreen(
                state = LoginScreenState(serverList = listOf("test", "another test")),
                serverTextChange = {},
                login = {}
            )
        }
    }
}