package com.matrix159.mastadonclone.shared.mvitests.screens.homefeed

import com.matrix159.mastadonclone.shared.data.Repository
import com.matrix159.mastadonclone.shared.fakes.FakeRepository
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedState
import com.matrix159.mastadonclone.shared.mvi.screens.homefeed.HomeFeedStore
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
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeFeedStoreTests : KoinTest {

  private val homeFeedStore: HomeFeedStore by inject()
  private val repository: Repository by inject()

  @BeforeTest
  fun before() {
    startKoin {
      //modules
      modules(
        module {
          single { HomeFeedStore() }
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

  // ------ ACTIONS ------
  @Test
  fun testStartLoading() = runTest {
    assertEquals(HomeFeedState(isLoading = true), homeFeedStore.state.value)
  }
}
