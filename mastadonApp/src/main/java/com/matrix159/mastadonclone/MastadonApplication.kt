package com.matrix159.mastadonclone

import android.app.Application
import androidx.lifecycle.*
import com.matrix159.mastadonclone.shared.viewmodel.DKMPViewModel
import com.matrix159.mastadonclone.shared.viewmodel.getAndroidInstance
import timber.log.Timber

class MastadonApplication: Application() {

  lateinit var model: DKMPViewModel

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      //plant(CrashReportingTree())
    }

    model = DKMPViewModel.Factory.getAndroidInstance(this)

    val appLifecycleObserver = AppLifecycleObserver(model)
    ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
  }
}

class AppLifecycleObserver(private val viewModel: DKMPViewModel) : LifecycleEventObserver {

  override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
    when (event) {
      Lifecycle.Event.ON_START -> {
        // Avoid calling at app startup
        if (viewModel.stateFlow.value.recompositionIndex > 0) {
          viewModel.navigation.onReEnterForeground()
        }
      }
      Lifecycle.Event.ON_STOP -> {
        viewModel.navigation.onEnterBackground()
      }
      else -> {
        // Don't care
      }
    }
  }
}