package com.matrix159.mastadonclone.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matrix159.mastadonclone.presentation.ui.components.HomeFeed
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme

@Composable
fun MastadonApp() {
  MastadonTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.surface
    ) {
      HomeFeed()
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMastadonApp() {
  MastadonApp()
}