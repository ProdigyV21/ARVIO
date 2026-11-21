package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

/** Real file-backed DataStore: tests the migration/flush/profile glue, not just style arithmetic. */
// Android DataStore chooses its file replacement implementation by SDK_INT. Plain JVM's
// mockable android.jar reports zero, selecting legacy renameTo instead of API 26+ Files.move;
// replacing an existing file then fails on Windows. Keep real files and use a real SDK model.
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest = Config.NONE, application = android.app.Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class LegacySubtitleStoreIntegrationTest {
    @get:Rule val tmp = TemporaryFolder()
    private fun key(name: String) = stringPreferencesKey("profile_p1_$name")
    private fun intKey(name: String) = intPreferencesKey("profile_p1_$name")

    @Test fun `real datastore replaces an existing file and survives reopening`() = runBlocking {
        val file = tmp.root.resolve("replace.preferences_pb")
        val value = intPreferencesKey("value")
        val firstScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val first = PreferenceDataStoreFactory.create(scope = firstScope, produceFile = { file })
            first.edit { it[value] = 1 }
            first.edit { it[value] = 2 }
            assertEquals(2, first.data.first()[value])
        } finally { firstScope.coroutineContext[Job]!!.cancelAndJoin() }
        val secondScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val second = PreferenceDataStoreFactory.create(scope = secondScope, produceFile = { file })
            assertEquals(2, second.data.first()[value])
            second.edit { it[value] = 3 }
        } finally { secondScope.coroutineContext[Job]!!.cancelAndJoin() }
        val thirdScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val third = PreferenceDataStoreFactory.create(scope = thirdScope, produceFile = { file })
            assertEquals(3, third.data.first()[value])
        } finally { thirdScope.coroutineContext[Job]!!.cancelAndJoin() }
    }

    @Test fun `legacy-only appearance migrates once and survives restart without scaling again`() = runBlocking {
        val file = tmp.root.resolve("legacy.preferences_pb")
        val profile = MutableStateFlow("p1")
        val scope1 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val ds1 = PreferenceDataStoreFactory.create(scope = scope1, produceFile = { file })
        try {
            ds1.edit {
                it[intKey("subtitle_size_pct")] = 125
                it[key("subtitle_color")] = "Yellow"
                it[key("subtitle_font")] = "Lexend"
                it[intKey("subtitle_vertical_pct")] = 80
            }
            val store = BilingualSubtitleSettingsStore(ds1, profile, scope1)
            val migrated = withTimeout(5_000) { store.settings.first { it.legacyAppearance?.sizePercent == 125 } }
            assertEquals(30, migrated.original.sizeSp)
            assertEquals(30, migrated.translation.sizeSp)
            assertEquals("#FFFF00", migrated.translation.colorHex)
            assertEquals("Lexend", migrated.original.font)
            assertEquals(80, migrated.bottomPercent)
            store.flush()
        } finally { scope1.coroutineContext[Job]!!.cancelAndJoin() }
        val scope2 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val ds2 = PreferenceDataStoreFactory.create(scope = scope2, produceFile = { file })
            val store = BilingualSubtitleSettingsStore(ds2, profile, scope2)
            val restored = withTimeout(5_000) { store.settings.first { it.legacyAppearance != null } }
            assertEquals(30, restored.original.sizeSp)
            assertEquals(30, restored.translation.sizeSp)
            assertEquals(80, restored.bottomPercent)
        } finally { scope2.coroutineContext[Job]!!.cancelAndJoin() }
    }

    @Test fun `existing bilingual styles win and legacy change flush restart does not double scale`() = runBlocking {
        val file = tmp.root.resolve("bilingual.preferences_pb")
        val profile = MutableStateFlow("p1")
        val scope1 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val ds1 = PreferenceDataStoreFactory.create(scope = scope1, produceFile = { file })
        try {
            val saved = BilingualSubtitleSettings.DEFAULT.copy(
                original = BilingualLineStyle(sizeSp = 16, colorHex = "#7FE3FF"),
                translation = BilingualLineStyle(sizeSp = 30, colorHex = "#9CF28B"),
            )
            ds1.edit {
                it[BilingualSubtitleSettingsStore.keyFor("p1")] = BilingualSettingsCodec.encode(saved)
                it[intKey("subtitle_size_pct")] = 200
            }
            val store = BilingualSubtitleSettingsStore(ds1, profile, scope1)
            val initial = withTimeout(5_000) { store.settings.first { it.legacyAppearance != null } }
            assertEquals(16, initial.original.sizeSp)
            assertEquals(30, initial.translation.sizeSp)
            assertEquals("#7FE3FF", initial.original.colorHex)
            ds1.edit { it[intKey("subtitle_size_pct")] = 250 }
            val changed = withTimeout(5_000) { store.settings.first { it.original.sizeSp == 20 } }
            assertEquals(38, changed.translation.sizeSp)
            store.flush()
        } finally { scope1.coroutineContext[Job]!!.cancelAndJoin() }
        val scope2 = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val ds2 = PreferenceDataStoreFactory.create(scope = scope2, produceFile = { file })
            val store = BilingualSubtitleSettingsStore(ds2, profile, scope2)
            val restored = withTimeout(5_000) { store.settings.first { it.legacyAppearance != null } }
            assertEquals(20, restored.original.sizeSp)
            assertEquals(38, restored.translation.sizeSp)
            store.update { it.copy(original = it.original.copy(sizeSp = 25)) }
            store.flush()
            // A bilingual edit re-anchors against 250%, rather than multiplying 25 again.
            assertEquals(25, store.settings.value.original.sizeSp)
            ds2.edit { it[intKey("subtitle_size_pct")] = 200 }
            val changed = withTimeout(5_000) { store.settings.first { it.original.sizeSp == 20 } }
            assertEquals(30, changed.translation.sizeSp)
        } finally { scope2.coroutineContext[Job]!!.cancelAndJoin() }
    }

    @Test fun `pristine profile captures a baseline before the first legacy slider adjustment`() = runBlocking {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val ds = PreferenceDataStoreFactory.create(scope = scope, produceFile = { tmp.root.resolve("new.preferences_pb") })
            val store = BilingualSubtitleSettingsStore(ds, MutableStateFlow("p1"), scope)
            // Verify that the store loaded and published its resolved legacy baseline. In DataStore 1.1.x,
            // an in-flight first write may cause an independent, concurrently attached dataStore.data flow
            // to observe an empty state associated with the in-flight version; waiting on the store's own
            // loaded emission ensures the store's collector completed its migration write.
            withTimeout(15_000) { store.settings.first { it.legacyAppearance != null } }
            withTimeout(15_000) { ds.data.first { it[BilingualSubtitleSettingsStore.keyFor("p1")] != null } }
            ds.edit { it[intKey("subtitle_size_pct")] = 150 }
            val adjusted = withTimeout(15_000) { store.settings.first { it.original.sizeSp == 26 } }
            assertEquals(32, adjusted.translation.sizeSp)
            assertNotEquals(adjusted.original.sizeSp, adjusted.translation.sizeSp)
        } finally { scope.coroutineContext[Job]!!.cancelAndJoin() }
    }

    @Test fun `an edit before first emission preserves saved merge languages and independent styles`() = runBlocking {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val gate = CompletableDeferred<Unit>()
        try {
            val ds = PreferenceDataStoreFactory.create(scope = scope, produceFile = { tmp.root.resolve("early.preferences_pb") })
            val saved = BilingualSubtitleSettings.DEFAULT.copy(
                generationMode = BilingualGenerationMode.MERGE, primaryLanguage = "ja", secondaryLanguage = "zh-Hant",
                original = BilingualLineStyle(sizeSp = 16, colorHex = "#7FE3FF"),
            )
            ds.edit { it[BilingualSubtitleSettingsStore.keyFor("p1")] = BilingualSettingsCodec.encode(saved) }
            val delayed = object : DataStore<Preferences> {
                override val data = flow { gate.await(); emitAll(ds.data) }
                override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = ds.updateData(transform)
            }
            val store = BilingualSubtitleSettingsStore(delayed, MutableStateFlow("p1"), scope)
            assertFalse(store.isLoadedForCurrentProfile)
            store.update { it.copy(smartEnabled = true) }
            gate.complete(Unit)
            withTimeout(5_000) { store.loadedProfileId.first { it == "p1" } }
            store.flush()
            val restored = store.readPersisted()
            assertTrue(restored.smartEnabled)
            assertEquals(BilingualGenerationMode.MERGE, restored.generationMode)
            assertEquals("ja", restored.primaryLanguage)
            assertEquals("zh-Hant", restored.secondaryLanguage)
            assertEquals(saved.original, restored.original)
        } finally { scope.coroutineContext[Job]!!.cancelAndJoin() }
    }

    @Test fun `an early toggle still migrates the actual legacy-only style`() = runBlocking {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val gate = CompletableDeferred<Unit>()
        try {
            val ds = PreferenceDataStoreFactory.create(scope = scope, produceFile = { tmp.root.resolve("early-legacy.preferences_pb") })
            ds.edit { it[intKey("subtitle_size_pct")] = 200 }
            val delayed = object : DataStore<Preferences> {
                override val data = flow { gate.await(); emitAll(ds.data) }
                override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = ds.updateData(transform)
            }
            val store = BilingualSubtitleSettingsStore(delayed, MutableStateFlow("p1"), scope)
            store.update { it.copy(smartEnabled = true) }
            gate.complete(Unit)
            withTimeout(5_000) { store.loadedProfileId.first { it == "p1" } }
            store.flush()
            assertTrue(store.settings.value.smartEnabled)
            assertEquals(48, store.settings.value.original.sizeSp)
            assertEquals(48, store.settings.value.translation.sizeSp)
        } finally { scope.coroutineContext[Job]!!.cancelAndJoin() }
    }

    @Test fun `pending edits and legacy preferences stay isolated across profile changes`() = runBlocking {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        try {
            val ds = PreferenceDataStoreFactory.create(scope = scope, produceFile = { tmp.root.resolve("profiles.preferences_pb") })
            ds.edit { it[intKey("subtitle_size_pct")] = 150 }
            val profile = MutableStateFlow("p1")
            val store = BilingualSubtitleSettingsStore(ds, profile, scope, persistDebounceMs = 10_000)
            withTimeout(5_000) { store.settings.first { it.legacyAppearance?.sizePercent == 150 } }
            store.update { it.copy(gapDp = 17) }
            profile.value = "p2"
            val second = withTimeout(5_000) { store.settings.first { it.legacyAppearance != null && it.legacyAppearance.sizePercent == null && it.gapDp == 4 } }
            assertEquals(17, second.original.sizeSp)
            store.update { it.copy(gapDp = 9) }
            profile.value = "p1"
            withTimeout(5_000) { store.settings.first { it.gapDp == 17 } }
            store.flush()
            val prefs = ds.data.first()
            assertEquals(17, BilingualSettingsCodec.decode(prefs[BilingualSubtitleSettingsStore.keyFor("p1")]).gapDp)
            assertEquals(9, BilingualSettingsCodec.decode(prefs[BilingualSubtitleSettingsStore.keyFor("p2")]).gapDp)
            assertEquals(36, store.settings.value.original.sizeSp)
        } finally { scope.coroutineContext[Job]!!.cancelAndJoin() }
    }
}
