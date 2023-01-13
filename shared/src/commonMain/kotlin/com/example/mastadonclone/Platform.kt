package com.example.mastadonclone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform