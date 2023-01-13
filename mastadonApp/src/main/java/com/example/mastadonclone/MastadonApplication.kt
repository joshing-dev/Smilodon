package com.example.mastadonclone

import android.app.Application
import timber.log.Timber

class MastadonApplication: Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    } else {
      //plant(CrashReportingTree())
    }
  }
}