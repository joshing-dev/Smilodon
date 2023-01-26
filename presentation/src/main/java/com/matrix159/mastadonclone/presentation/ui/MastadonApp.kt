package com.matrix159.mastadonclone.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matrix159.mastadonclone.presentation.ui.components.LoginScreen
import com.matrix159.mastadonclone.presentation.ui.theme.MastadonTheme
import com.matrix159.mastadonclone.shared.viewmodel.DKMPViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.matrix159.mastadonclone.presentation.ui.components.ScreenPicker
import timber.log.Timber

@Composable
fun MastadonApp(model: DKMPViewModel) {
  val appState by model.stateFlow.collectAsStateWithLifecycle()
  val navigation = remember { model.navigation }
  Timber.d("D-KMP-SAMPLE: recomposition Index: "+appState.recompositionIndex.toString())
  Timber.d("Test")
  MastadonTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.surface
    ) {
      ScreenPicker(navigation = navigation)
      //LoginScreen(modifier = Modifier.fillMaxHeight())
      //HomeFeed()
    }
  }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMastadonApp() {
  //MastadonApp(DKMPViewModel(Repository()))
}