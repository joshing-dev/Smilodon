package com.matrix159.mastadonclone.shared

expect class DebugLogger (tagString : String) {
  val tag : String
  fun log(message: String)
}