package com.matrix159.mastadonclone.shared.fakes

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.models.MastadonApiApplication
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.common.Account
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.instance.InstanceResponseJson
import com.matrix159.mastadonclone.shared.data.models.mastadonapi.timelines.Status
import com.matrix159.mastadonclone.shared.data.sources.localsettings.SettingsAppState

class FakeRepository: Repository {

  private var appState = SettingsAppState()
  private val server = "androiddev.social"

  override suspend fun getClientApplication(serverUrl: String): MastadonApiApplication {
    return MastadonApiApplication(
      serverUrl = serverUrl,
      clientId = "clientId",
      clientSecret = "clientSecret",
      redirectUri = "redirectUri"
    )
  }

  override suspend fun getInstance(serverUrl: String): InstanceResponseJson? {
    return if (serverUrl.equals(this.server, ignoreCase = true)) {
      InstanceResponseJson(title = "Android Dev", description = "Android Dev Description")
    } else {
      null
    }
  }

  override fun saveAppState(appState: SettingsAppState) {
    this.appState = appState
  }

  override fun getSavedAppState(): SettingsAppState {
    return appState
  }

  override suspend fun getHomeTimelines(): List<Status> {
    return listOf(
      Status(account = Account(), content = "asdads", createdAt = "timehere"),
      Status(account = Account(), content = "zxczxc", createdAt = "anothertimehere")
    )
  }
}

