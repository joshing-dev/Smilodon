package com.matrix159.mastadonclone.shared.fakes

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRepository: Repository {

  var appState: MutableStateFlow<SettingsAppState> = MutableStateFlow(SettingsAppState())

  override suspend fun getClientApplication(serverUrl: String): MastadonApiApplication {
    return MastadonApiApplication(
      serverUrl = serverUrl,
      clientId = "clientId",
      clientSecret = "clientSecret",
      redirectUri = "redirectUri"
    )
  }

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    // TODO
    return null
  }

  override fun saveAppState(appState: SettingsAppState) {
    this.appState.value = appState
  }

  override fun getSavedAppState(): SettingsAppState {
    return appState.value
  }
}

