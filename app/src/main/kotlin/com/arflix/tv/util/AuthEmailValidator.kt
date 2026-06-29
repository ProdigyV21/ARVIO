package com.arflix.tv.util

import androidx.annotation.StringRes
import com.arflix.tv.R

object AuthEmailValidator {
    private val emailRegex = Regex(
        pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,63}$",
        option = RegexOption.IGNORE_CASE
    )

    private val blockedDomains = setOf(
        "example.com",
        "example.net",
        "example.org",
        "invalid",
        "localhost",
        "mailinator.com",
        "guerrillamail.com",
        "guerrillamail.net",
        "10minutemail.com",
        "tempmail.com",
        "temp-mail.org",
        "yopmail.com"
    )

    fun normalize(email: String): String = email.trim().lowercase()

    @StringRes
    fun validate(email: String, rejectDisposable: Boolean = true): Int? {
        val normalized = normalize(email)
        if (normalized.isBlank()) return R.string.auth_email_required
        if (normalized.length > 254 || !emailRegex.matches(normalized)) {
            return R.string.auth_email_invalid
        }

        val localPart = normalized.substringBefore("@")
        val domain = normalized.substringAfter("@", missingDelimiterValue = "")
        if (localPart.isBlank() || domain.isBlank()) {
            return R.string.auth_email_real_required
        }
        if (rejectDisposable && domain in blockedDomains) {
            return R.string.auth_email_real_required
        }
        if (rejectDisposable && (domain.endsWith(".invalid") || domain.endsWith(".test") || domain.endsWith(".local"))) {
            return R.string.auth_email_real_required
        }
        if (domain.split(".").any { it.isBlank() }) {
            return R.string.auth_email_invalid
        }
        return null
    }
}
