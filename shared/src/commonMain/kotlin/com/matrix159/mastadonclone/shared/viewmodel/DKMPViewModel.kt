package com.matrix159.mastadonclone.shared.viewmodel

import com.matrix159.mastadonclone.shared.DebugLogger
import com.matrix159.mastadonclone.shared.datalayer.Repository
import kotlinx.coroutines.flow.StateFlow
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val debugLogger by lazy { DebugLogger("D-KMP SAMPLE") }


class DKMPViewModel (repo: Repository) {

  companion object Factory {
    // factory methods are defined in the platform-specific shared code (androidMain and iosMain)
  }

  val stateFlow: StateFlow<AppState>
    get() = stateManager.mutableStateFlow

  private val stateManager by lazy { StateManager(repo) }
  val navigation by lazy { Navigation(stateManager) }

}