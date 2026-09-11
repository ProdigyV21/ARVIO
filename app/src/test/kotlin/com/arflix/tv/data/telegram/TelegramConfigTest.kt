package com.arflix.tv.data.telegram

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TelegramConfigTest {

    @Test
    fun missingOrBlankValuesSanitizeToZeroAndEmpty() {
        assertThat(sanitizeTelegramApiId(null)).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("   ")).isEqualTo(0)

        assertThat(sanitizeTelegramApiHash(null)).isEmpty()
        assertThat(sanitizeTelegramApiHash("")).isEmpty()
        assertThat(sanitizeTelegramApiHash("   ")).isEmpty()
    }

    @Test
    fun placeholderTemplateValuesSanitizeToZeroAndEmpty() {
        assertThat(sanitizeTelegramApiId("your-telegram-api-id")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("YOUR-API-KEY")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("disabled")).isEqualTo(0)

        assertThat(sanitizeTelegramApiHash("your-telegram-api-hash")).isEmpty()
        assertThat(sanitizeTelegramApiHash("YOUR-HASH")).isEmpty()
        assertThat(sanitizeTelegramApiHash("disabled")).isEmpty()
    }

    @Test
    fun malformedApiIdValuesSanitizeToZero() {
        assertThat(sanitizeTelegramApiId("not-a-number")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("-500")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("0")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("12.34")).isEqualTo(0)
        assertThat(sanitizeTelegramApiId("1234abc")).isEqualTo(0)
    }

    @Test
    fun validCredentialsSanitizeCorrectly() {
        assertThat(sanitizeTelegramApiId("12345678")).isEqualTo(12345678)
        assertThat(sanitizeTelegramApiId("  998877  ")).isEqualTo(998877)

        assertThat(sanitizeTelegramApiHash("  valid_hex_hash_32chars  ")).isEqualTo("valid_hex_hash_32chars")
    }

    @Test
    fun isTelegramCredentialsConfiguredRequiresBothPositiveIdAndNonBlankHash() {
        assertThat(isTelegramCredentialsConfigured(apiId = 0, apiHash = "")).isFalse()
        assertThat(isTelegramCredentialsConfigured(apiId = 0, apiHash = "some_hash")).isFalse()
        assertThat(isTelegramCredentialsConfigured(apiId = 123456, apiHash = "")).isFalse()
        assertThat(isTelegramCredentialsConfigured(apiId = 123456, apiHash = "   ")).isFalse()
        assertThat(isTelegramCredentialsConfigured(apiId = -1, apiHash = "some_hash")).isFalse()
        assertThat(isTelegramCredentialsConfigured(apiId = 123456, apiHash = "valid_hash")).isTrue()
    }
}
