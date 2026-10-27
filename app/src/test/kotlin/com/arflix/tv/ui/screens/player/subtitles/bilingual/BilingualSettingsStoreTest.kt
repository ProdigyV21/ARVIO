package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/** Persistence through a real (file-backed) Preferences DataStore, like the app's settings_prefs. */
class BilingualSettingsStoreTest {

    @get:Rule val tmp = TemporaryFolder()

    @Test fun `settings survive an app restart and stay per profile`() = runBlocking {
        val file = tmp.newFile("settings_prefs.preferences_pb").also { it.delete() }
        val scope1 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val ds1 = PreferenceDataStoreFactory.create(scope = scope1, produceFile = { file })
        val profile = MutableStateFlow("p1")
        val store1 = BilingualSubtitleSettingsStore(ds1, profile, scope1, persistDebounceMs = 10)

        store1.update { it.copy(order = BilingualOrder.TRANSLATION_FIRST, gapDp = 20, bottomPercent = 15) }
        store1.update { it.copy(translation = it.translation.copy(colorHex = "#FFE600", offsetDp = 12)) }
        // Generation mode, display mode and both languages are independent and all persist.
        store1.update {
            it.copy(
                smartEnabled = true,
                generationMode = BilingualGenerationMode.MERGE,
                mode = BilingualDisplayMode.TRANSLATION_ONLY,
                primaryLanguage = "ja",
                secondaryLanguage = "zh-Hant",
            )
        }
        // Applied in memory immediately (live preview while dragging).
        assertEquals(20, store1.settings.value.gapDp)
        store1.flush()
        // Fully release the first DataStore before opening the file again (DataStore forbids
        // two active instances on one file).
        scope1.coroutineContext[Job]!!.cancelAndJoin()

        // "Restart": a brand-new DataStore + store over the same file.
        val scope2 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val ds2 = PreferenceDataStoreFactory.create(scope = scope2, produceFile = { file })
        val store2 = BilingualSubtitleSettingsStore(ds2, MutableStateFlow("p1"), scope2)
        val restored = withTimeout(5_000) { store2.settings.first { it.gapDp == 20 } }
        assertEquals(BilingualOrder.TRANSLATION_FIRST, restored.order)
        assertEquals(15, restored.bottomPercent)
        assertEquals("#FFE600", restored.translation.colorHex)
        assertEquals(12, restored.translation.offsetDp)
        assertEquals("#FFFFFF", restored.original.colorHex)
        assertEquals(true, restored.smartEnabled)
        assertEquals(BilingualGenerationMode.MERGE, restored.generationMode)
        assertEquals(BilingualDisplayMode.TRANSLATION_ONLY, restored.mode)
        assertEquals("ja", restored.primaryLanguage)
        assertEquals("zh-Hant", restored.secondaryLanguage)
        // Resetting the style keeps the generation/display modes and languages.
        store2.resetToDefaults()
        assertEquals(BilingualGenerationMode.MERGE, store2.settings.value.generationMode)
        assertEquals("ja", store2.settings.value.primaryLanguage)
        assertEquals(BilingualSubtitleSettings.DEFAULT.translation, store2.settings.value.translation)

        // Another profile has its own (default) settings.
        val store3 = BilingualSubtitleSettingsStore(ds2, MutableStateFlow("p2"), scope2)
        assertEquals(BilingualSubtitleSettings.DEFAULT, store3.readPersisted())
        scope2.cancel()
    }
}
