package com.camilo.demo.coin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform