package com.matrix159.mastadonclone

import com.matrix159.mastadonclone.shared.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

  @Test
  fun testExample() {
    assertTrue("Check Android is mentioned", Greeting().greet().contains("Android"))
  }
}