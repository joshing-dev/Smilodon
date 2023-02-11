package com.matrix159.mastadonclone.shared.mvitests.app

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.fakes.FakeRepository
import com.matrix159.mastadonclone.shared.mvi.app.AppStore
import com.matrix159.mastadonclone.shared.mvi.screens.login.LoginStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class AppStoreTests : KoinTest {

  private val appStore: AppStore by inject()
  private val repository: Repository by inject()

  @BeforeTest
  fun before() {
    startKoin {
      //modules
      modules(
        module {
          single { AppStore() }
          // Repository is single for tests so that multiple instances aren't created between the test
          // and the store's usage
          single<Repository> { FakeRepository() }
        }
      )
    }
  }

  @AfterTest
  fun after() {
    stopKoin()
  }

  // ----- ACTIONS -----
  @Test
  fun testStartAuthentication() = runTest {

  }
}
