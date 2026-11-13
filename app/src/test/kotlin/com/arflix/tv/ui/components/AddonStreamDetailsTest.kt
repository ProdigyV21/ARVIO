package com.arflix.tv.ui.components

import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.mutablePreferencesOf
import com.arflix.tv.data.repository.CloudSyncRepository.CloudProfileSettings
import com.arflix.tv.util.addonStreamDetailsKey
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Test

class AddonStreamDetailsTest {
    @Test fun expandedLinesRespectTheSpaceLeftByTheFilenameAndBadges() {
        assertEquals(12, addonDescriptionMaxLines(500f, 16f))
        assertEquals(6, addonDescriptionMaxLines(100f, 16f))
        assertEquals(0, addonDescriptionMaxLines(0f, 16f))
        assertEquals(0, addonDescriptionMaxLines(-100f, 16f))
    }

    @Test fun detailsAreOffByDefaultAndScopedToTheProfile() {
        val first = addonStreamDetailsKey("first")
        val second = addonStreamDetailsKey("second")
        assertFalse(emptyPreferences()[first] ?: false)
        val preferences = mutablePreferencesOf(first to true, second to false)
        assertTrue(preferences[first]!!)
        assertFalse(preferences[second]!!)
    }

    @Test fun preservesCustomFormatterLinesIncludingSizeAndProvider() {
        val description = "Resolution: 2160p\n\nHDR: DV HDR10+\nAudio: TrueHD 7.1\n12.5 GB\n[RD+]\nGroup: Example"
        assertEquals(description, tvAddonStreamDescription(description, true))
        assertNull(tvAddonStreamDescription(description, false))
        assertNull(tvAddonStreamDescription(null, true))
        assertNull(tvAddonStreamDescription(" \n ", true))
    }

    @Test fun missingCloudFieldDoesNotResetExistingPreference() {
        val gson = Gson()
        assertNull(CloudProfileSettings().showAddonStreamDetails)
        assertNull(gson.fromJson("{}", CloudProfileSettings::class.java).showAddonStreamDetails)
        for (enabled in listOf(true, false)) {
            val original = CloudProfileSettings(showAddonStreamDetails = enabled)
            val restored = gson.fromJson(gson.toJson(original), CloudProfileSettings::class.java)
            assertEquals(enabled, restored.showAddonStreamDetails)
        }
    }
}
