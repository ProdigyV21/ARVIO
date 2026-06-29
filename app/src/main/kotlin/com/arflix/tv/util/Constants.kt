package com.arflix.tv.util

import com.arflix.tv.BuildConfig

/**
 * Application constants.
 *
 * API keys come from ignored local secrets or CI environment values, not from
 * committed source.
 */
object Constants {
    // Supabase - keys from BuildConfig (secrets.properties).
    val SUPABASE_URL: String get() = BuildConfig.SUPABASE_URL
    val SUPABASE_ANON_KEY: String get() = BuildConfig.SUPABASE_ANON_KEY
    val APP_ANON_KEY: String get() = BuildConfig.APP_ANON_KEY
    val NETLIFY_BACKEND_URL: String
        get() = BuildConfig.NETLIFY_BACKEND_URL.trim().trimEnd('/')
    val USE_NETLIFY_CLOUD_SYNC: Boolean
        get() = BuildConfig.ENABLE_NETLIFY_CLOUD_SYNC && NETLIFY_BACKEND_URL.startsWith("https://")

    // Edge Function proxy URLs used by backend/proxy-capable flows.
    val TMDB_PROXY_URL: String get() = "$NETLIFY_BACKEND_URL/tmdb-proxy"
    val TV_AUTH_START_URL: String get() = "$NETLIFY_BACKEND_URL/tv-auth-start"
    val TV_AUTH_STATUS_URL: String get() = "$NETLIFY_BACKEND_URL/tv-auth-status"
    val TV_AUTH_POLL_URL: String get() = "$NETLIFY_BACKEND_URL/tv-auth-poll"
    val TV_AUTH_COMPLETE_URL: String get() = "$NETLIFY_BACKEND_URL/tv-auth-complete"
    val AUTH_LOGIN_URL: String get() = "$NETLIFY_BACKEND_URL/auth-login"
    val AUTH_REFRESH_URL: String get() = "$NETLIFY_BACKEND_URL/auth-refresh"
    val AUTH_PASSWORD_START_URL: String get() = "$NETLIFY_BACKEND_URL/auth-password-start"
    val CLOUD_AUTH_EMAIL_URL: String get() = "$NETLIFY_BACKEND_URL/cloud-auth-email"
    val NETLIFY_ACCOUNT_SYNC_PULL_URL: String get() = "$NETLIFY_BACKEND_URL/account-sync-pull"
    val NETLIFY_ACCOUNT_SYNC_PUSH_URL: String get() = "$NETLIFY_BACKEND_URL/account-sync-push"
    val NETLIFY_ACCOUNT_SYNC_CURSOR_URL: String get() = "$NETLIFY_BACKEND_URL/account-sync-cursor"
    val NETLIFY_ACCOUNT_SYNC_DELTA_URL: String get() = "$NETLIFY_BACKEND_URL/account-sync-delta"
    val APP_USAGE_EVENT_URL: String get() = "$NETLIFY_BACKEND_URL/app-usage-event"

    // API base URLs.
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val TRAKT_API_URL = "https://api.trakt.tv/"

    private fun usableSecret(value: String): String =
        value.takeUnless { candidate ->
            candidate.isBlank() || candidate.startsWith("your-", ignoreCase = true)
        } ?: ""

    // Optional local direct-call credentials. Release builds should use the Edge
    // Function proxies so these values do not have to be shipped in the client.
    val TMDB_API_KEY: String get() = usableSecret(BuildConfig.TMDB_API_KEY)
    val TRAKT_CLIENT_ID: String get() = usableSecret(BuildConfig.TRAKT_CLIENT_ID)
    val TRAKT_CLIENT_SECRET: String
        get() = usableSecret(BuildConfig.TRAKT_CLIENT_SECRET)

    // Image URLs - tuned for TV quality with smooth scrolling/perf.
    const val IMAGE_BASE = "https://image.tmdb.org/t/p/w780"
    const val IMAGE_BASE_LARGE = "https://image.tmdb.org/t/p/w1280"
    const val BACKDROP_BASE = "https://image.tmdb.org/t/p/w1280"
    // Full quality for hero and detail backdrops; loading speed is handled by
    // preloading and disk caching instead of resolution downgrade.
    const val BACKDROP_BASE_LARGE = "https://image.tmdb.org/t/p/original"
    const val LOGO_BASE = "https://image.tmdb.org/t/p/w500"
    const val LOGO_BASE_LARGE = "https://image.tmdb.org/t/p/original"

    // Google Sign-In - key from BuildConfig (secrets.properties).
    val GOOGLE_WEB_CLIENT_ID: String get() = BuildConfig.GOOGLE_WEB_CLIENT_ID

    // Progress thresholds.
    const val WATCHED_THRESHOLD = 90
    const val MIN_PROGRESS_THRESHOLD = 3
    const val MAX_PROGRESS_ENTRIES = 50
    const val MAX_CONTINUE_WATCHING = 50

    // Preferences keys.
    const val PREFS_NAME = "arflix_prefs"
    const val PREF_DEFAULT_SUBTITLE = "default_subtitle"
    const val PREF_AUTO_PLAY_NEXT = "auto_play_next"
    const val PREF_TRAKT_TOKEN = "trakt_token"
}

/**
 * Language code mappings.
 */
object LanguageMap {
    private val ISO_LANG_MAP = mapOf(
        "en" to "English", "eng" to "English",
        "fr" to "French", "fre" to "French", "fra" to "French",
        "es" to "Spanish", "spa" to "Spanish",
        "de" to "German", "ger" to "German", "deu" to "German",
        "it" to "Italian", "ita" to "Italian",
        "pt" to "Portuguese", "por" to "Portuguese",
        "nl" to "Dutch", "nld" to "Dutch", "dut" to "Dutch",
        "ru" to "Russian", "rus" to "Russian",
        "zh" to "Chinese", "chi" to "Chinese", "zho" to "Chinese",
        "ja" to "Japanese", "jpn" to "Japanese",
        "ko" to "Korean", "kor" to "Korean",
        "ar" to "Arabic", "ara" to "Arabic",
        "hi" to "Hindi", "hin" to "Hindi"
    )

    fun getLanguageName(code: String): String {
        return ISO_LANG_MAP[code.lowercase()] ?: code.uppercase()
    }
}
