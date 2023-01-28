package com.matrix159.mastadonclone.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
    .padding(16.dp)
    .verticalScroll(rememberScrollState())
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

    var serverUrl by remember { mutableStateOf("") }
    TextField(
      value = serverUrl,
      onValueChange = { value: String -> serverUrl = value},
      singleLine = true,
      leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = "Search for server")
      },
      label = {
        Text(text = "Server URL")
      },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.padding(vertical = 16.dp).weight(1f))

    FilledTonalButton(
      onClick = { /* TODO */ },
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
      LoginScreen()
    }
  }
}