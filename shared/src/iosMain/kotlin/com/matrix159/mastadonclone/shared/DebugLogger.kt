package com.matrix159.mastadonclone.shared

actual class DebugLogger actual constructor(tagString: String) {
  actual val tag: String
    get() = tagString

  actual fun log(message: String) {
  }
}