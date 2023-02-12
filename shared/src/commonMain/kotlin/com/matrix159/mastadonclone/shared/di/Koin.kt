package com.matrix159.mastadonclone.shared.di

import com.matrix159.mastadonclone.shared.data.MastadonRepository
import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.data.sources.localsettings.LocalMastadonSettings
import com.matrix159.mastadonclone.shared.data.sources.localsettings.MastadonSettings
import com.matrix159.mastadonclone.shared.data.sources.webservices.MastadonApiRemoteDataSource
import com.matrix159.mastadonclone.shared.data.sources.webservices.MastadonRemoteDataSource
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginStore
import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

internal expect fun platformModule(): Module

internal fun commonModule() = module {
  single { AppStore(repository = get()) }
  single { LoginStore(appStore = get(), repository = get()) }
  single { HomeFeedStore(repository = get()) }
  factory<MastadonSettings> { LocalMastadonSettings(Settings()) }
  factory<MastadonRemoteDataSource> { MastadonApiRemoteDataSource(get()) }
  factory<Repository> { MastadonRepository(get(), get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
  startKoin {
    appDeclaration()
    modules(
      commonModule(),
      platformModule()
    )
  }

// iOS
fun initKoin() {
  startKoin {
    modules(platformModule() + commonModule())
  }
}
