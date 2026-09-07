package com.arflix.tv.data.telegram

import com.arflix.tv.BuildConfig

object TelegramConfig {
    val API_ID: Int = BuildConfig.TELEGRAM_API_ID.toIntOrNull() ?: 23905496
    val API_HASH: String = BuildConfig.TELEGRAM_API_HASH.ifBlank { "1e48b355edfe55f9a4fbf8d3c2324628" }
}
