package com.matrix159.mastadonclone.shared.fakes

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState

class FakeRepository: Repository {
  override suspend fun getClientApplication(serverUrl: String): MastadonApiApplication {
    TODO("Not yet implemented")
  }

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    TODO("Not yet implemented")
  }

  override fun saveAppState(appState: SettingsAppState) {
    TODO("Not yet implemented")
  }

  override fun getSavedAppState(): SettingsAppState {
    TODO("Not yet implemented")
  }
}