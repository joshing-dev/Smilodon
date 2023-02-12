package com.matrix159.mastadonclone.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matrix159.mastadonclone.presentation.ui.components.Router
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme

@Composable
fun MastadonApp() {
  MastadonTheme {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding(), // Handle safe drawing insets at the root level
      color = MaterialTheme.colorScheme.surface
    ) {
      Router()
    }
  }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMastadonApp() {
  MastadonApp()
}
