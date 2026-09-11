package com.arflix.tv.data.telegram

import com.arflix.tv.BuildConfig

object TelegramConfig {
    val API_ID: Int = sanitizeTelegramApiId(BuildConfig.TELEGRAM_API_ID)
    val API_HASH: String = sanitizeTelegramApiHash(BuildConfig.TELEGRAM_API_HASH)

    val isConfigured: Boolean
        get() = isTelegramCredentialsConfigured(API_ID, API_HASH)
}

internal fun sanitizeTelegramApiId(raw: String?): Int {
    if (raw.isNullOrBlank()) return 0
    val trimmed = raw.trim()
    if (trimmed.startsWith("your-", ignoreCase = true) || trimmed.equals("disabled", ignoreCase = true)) {
        return 0
    }
    return trimmed.toIntOrNull()?.takeIf { it > 0 } ?: 0
}

internal fun sanitizeTelegramApiHash(raw: String?): String {
    if (raw.isNullOrBlank()) return ""
    val trimmed = raw.trim()
    if (trimmed.startsWith("your-", ignoreCase = true) || trimmed.equals("disabled", ignoreCase = true)) {
        return ""
    }
    return trimmed
}

internal fun isTelegramCredentialsConfigured(apiId: Int, apiHash: String): Boolean {
    return apiId > 0 && apiHash.isNotBlank()
}
