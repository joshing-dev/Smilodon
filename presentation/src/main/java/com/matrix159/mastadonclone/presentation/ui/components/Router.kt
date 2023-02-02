package com.matrix159.mastadonclone.presentation.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.matrix159.mastadonclone.shared.viewmodel.DKMPViewModel

@Composable
fun Router(model: DKMPViewModel) {
  val navigation = remember { model.navigation }
  ScreenPicker(navigation = navigation)

  if (!model.navigation.only1ScreenInBackstack) {
    BackHandler { // catching the back button to update the DKMPViewModel
      model.navigation.exitScreen()
    }
  }
}