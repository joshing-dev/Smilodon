package com.matrix159.mastadonclone.shared.viewmodel

import co.touchlab.kermit.Logger

class Events (val navigation: Navigation, val stateManager : StateManager) {

  val dataRepository
    get() = stateManager.dataRepository

  // we run each event function on a Dispatchers.Main coroutine
  fun screenCoroutine (block: suspend () -> Unit) {
    Logger.d("/"+stateManager.currentScreenIdentifier.URI+": an Event is called")
    stateManager.runInScreenScope { block() }
  }

}