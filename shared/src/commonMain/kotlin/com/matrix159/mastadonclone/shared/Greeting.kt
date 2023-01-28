package com.matrix159.mastadonclone.shared

class Greeting {
  private val platform: Platform = getPlatform()

  fun greet(): String {
    return "Hello, ${platform.name}!"
  }
}