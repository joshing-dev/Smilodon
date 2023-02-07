package com.matrix159.mastadonclone

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.repeatOnLifecycle
import com.matrix159.mastadonclone.shared.mvi.app.AppEffect
import com.matrix159.mastadonclone.shared.mvi.app.appStore
import kotlinx.coroutines.launch
import timber.log.Timber

class MastadonApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      //plant(CrashReportingTree())
    }

    val lifecycle = ProcessLifecycleOwner.get().lifecycle
    val lifecycleScope = ProcessLifecycleOwner.get().lifecycleScope
    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
        appStore.dispatchEffect(AppEffect.Startup)
      }
    }
  }
}