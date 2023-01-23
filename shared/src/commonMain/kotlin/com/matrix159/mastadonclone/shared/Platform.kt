package com.matrix159.mastadonclone.shared

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform