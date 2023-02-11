package com.matrix159.mastadonclone

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.repeatOnLifecycle
import com.matrix159.mastadonclone.shared.di.initKoin
import com.matrix159.mastadonclone.shared.mvi.app.AppEffect
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber

class MastadonApplication : Application() {

  private val appStore: AppStore by inject()
  override fun onCreate() {
    super.onCreate()

    // Setup dependency injection
    initKoin {
      androidContext(this@MastadonApplication)
      androidLogger()
      //modules(presentationKoinModule())
    }

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
