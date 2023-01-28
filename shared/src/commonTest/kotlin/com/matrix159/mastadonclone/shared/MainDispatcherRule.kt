//package com.matrix159.mastadonclone.shared
//
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.test.TestDispatcher
//import kotlinx.coroutines.test.UnconfinedTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//import org.junit.rules.TestWatcher
//
//// Reusable JUnit4 TestRule to override the Main dispatcher
//class MainDispatcherRule(
//  val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
//) : TestWatcher() {
//  override fun starting(description: Description) {
//    Dispatchers.setMain(testDispatcher)
//  }
//
//  override fun finished(description: Description) {
//    Dispatchers.resetMain()
//  }
//}
