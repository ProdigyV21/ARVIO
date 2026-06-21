package com.arflix.tv.util

object RuntimeApiKeys {
    const val TMDB_API_KEY_PREF_KEY = "runtime_tmdb_api_key"
    const val TMDB_API_KEYSTORE_ALIAS = "majo_stream_tmdb_api_key"
    const val WATCHMODE_API_KEY_PREF_KEY = "runtime_watchmode_api_key"
    const val WATCHMODE_API_KEYSTORE_ALIAS = "majo_stream_watchmode_api_key"

    @Volatile
    private var manualTmdbApiKey: String = ""
    @Volatile
    private var manualWatchmodeApiKey: String = ""

    fun setTmdbApiKey(value: String?) {
        manualTmdbApiKey = sanitize(value)
    }

    fun clearTmdbApiKey() {
        manualTmdbApiKey = ""
    }

    fun setWatchmodeApiKey(value: String?) {
        manualWatchmodeApiKey = sanitize(value)
    }

    fun clearWatchmodeApiKey() {
        manualWatchmodeApiKey = ""
    }

    fun resolveTmdbApiKey(buildTimeValue: String): String {
        return manualTmdbApiKey.ifBlank { sanitize(buildTimeValue) }
    }

    fun hasTmdbApiKey(buildTimeValue: String): Boolean {
        return resolveTmdbApiKey(buildTimeValue).isNotBlank()
    }

    fun resolveWatchmodeApiKey(buildTimeValue: String): String {
        return manualWatchmodeApiKey.ifBlank { sanitize(buildTimeValue) }
    }

    fun hasWatchmodeApiKey(buildTimeValue: String): Boolean {
        return resolveWatchmodeApiKey(buildTimeValue).isNotBlank()
    }

    fun sanitize(value: String?): String {
        val candidate = value?.trim().orEmpty()
        return candidate.takeUnless {
            it.isBlank() || it.startsWith("your-", ignoreCase = true)
        }.orEmpty()
    }
}
